package com.c353.projet.bicomat.filters;

import com.c353.projet.bicomat.data.AuthSession;
import com.c353.projet.bicomat.ejb.AuthSessionBean;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.annotation.Priority;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
@Stateless
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    AuthSessionBean authSessionBean;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the HTTP Authorization header from the request
        String authorizationHeader
                = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Check if the HTTP Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }

        // Extract the token from the HTTP Authorization header
        String token = authorizationHeader.substring("Bearer".length()).trim();

        try {
            // Validate the token
            validateToken(token);
        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private void validateToken(String token) throws Exception {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid        
        AuthSession authSession = null;

        try {
            authSession = authSessionBean.findByToken(token);

            if (authSession == null) {
                throw new NotFoundException("Invalid token provided");
            } else {
                LocalDateTime currentDateTime = LocalDateTime.now();
                Duration duration = Duration.between(currentDateTime, authSession.getExpiryDate());

                if (duration.isNegative() || duration.isZero()) {
                    throw new ForbiddenException("Your session has expired");
                }
                authSession.setExpiryDate(LocalDateTime.now().plusMinutes(15L));
                authSessionBean.persist(authSession);
            }
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}
