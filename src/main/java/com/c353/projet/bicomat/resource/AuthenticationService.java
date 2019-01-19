package com.c353.projet.bicomat.resource;

import com.c353.projet.bicomat.data.AuthSession;
import com.c353.projet.bicomat.data.Client;
import com.c353.projet.bicomat.data.ClientInterne;
import com.c353.projet.bicomat.data.Conseiller;
import com.c353.projet.bicomat.data.Credentials;
import com.c353.projet.bicomat.ejb.AuthSessionBean;
import com.c353.projet.bicomat.ejb.ClientBean;
import com.c353.projet.bicomat.ejb.ConseillerBean;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Stateless
@Path("/")
public class AuthenticationService {

    public static final Logger logger
            = Logger.getLogger(ClientService.class.getCanonicalName());

    @Inject
    ClientBean clientBean;

    @Inject
    ConseillerBean conseillerBean;

    @Inject
    AuthSessionBean authSessionBean;

    @POST
    @Path("client")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response clientAuthentication(@Valid Credentials credentials) {
        String login = credentials.getLogin();
        String password = credentials.getPassword();
        ClientInterne client = null;

        try {
            // Authenticate the user using the credentials provided
            client = authenticateClient(login, password);
            // Issue a token for the user
            String token = issueToken(login);
            // Return the token on the response

            if (token != null && client != null) {
                client.setToken(token);
                clientBean.merge(client);
            }
            return Response.ok(client).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @POST
    @Path("conseiller")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response conseillerAuthentication(@Valid Credentials credentials) {
        String login = credentials.getLogin();
        String password = credentials.getPassword();
        Conseiller conseiller = null;

        try {
            // Authenticate the user using the credentials provided
            conseiller = authenticateConseiller(login, password);
            // Issue a token for the user
            String token = issueToken(login);
            // Return the token on the response

            if (token != null && conseiller != null) {
                conseiller.setToken(token);
                conseillerBean.merge(conseiller);
            }
            return Response.ok(conseiller).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private ClientInterne authenticateClient(String login, String password) throws Exception {
        ClientInterne client = null;
        try {
            client = clientBean.findByLoginAndPassword(login, password);
            if (client == null) {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error calling authenticateClient()");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        return client;
    }

    private Conseiller authenticateConseiller(String login, String password) {
        Conseiller conseiller = null;

        try {
            conseiller = conseillerBean.findByLoginAndPassword(login, password);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error calling authenticateConseiller()");
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        return conseiller;
    }

    private String issueToken(String login) {
        Random random = new SecureRandom();
        String token = new BigInteger(130, random).toString(32);

        AuthSession authSession = new AuthSession();
        authSession.setUsername(login);
        authSession.setToken(token);

        try {
            authSessionBean.persist(authSession);
        } catch (Exception e) {
            logger.warning("Something went wrong when persisting the AuthSession");
            logger.warning(e.getMessage());
        }
        return token;
    }
}
