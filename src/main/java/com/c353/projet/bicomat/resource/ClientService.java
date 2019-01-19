package com.c353.projet.bicomat.resource;

import com.c353.projet.bicomat.data.Client;
import com.c353.projet.bicomat.data.ClientInterne;
import com.c353.projet.bicomat.data.ClientTiers;
import com.c353.projet.bicomat.data.Conseiller;
import com.c353.projet.bicomat.ejb.ClientBean;
import com.c353.projet.bicomat.ejb.ConseillerBean;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author AFK
 */
@Stateless
@Path("/client")
public class ClientService {

    public static final Logger logger
            = Logger.getLogger(ClientService.class.getCanonicalName());
    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder cb;

    @Inject
    ClientBean clientBean;

    @Inject
    ConseillerBean conseillerBean;

    @PostConstruct
    private void init() {
        cb = em.getCriteriaBuilder();
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Client> getAllClients() {
        logger.log(Level.INFO, "OK");
        List<Client> clients = null;
        try {
            clients = this.clientBean.findAllClients();
            if (clients == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (WebApplicationException ex) {
            logger.log(Level.SEVERE,
                    "Error calling findAllClients()",
                    new Object[]{ex.getMessage()});
        }
        return clients;
    }

    @GET
    @Path("conseiller/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Client> getAllClientsByConseiller(@PathParam("id") Long conseillerId) {
        List<Client> clients = null;
        Conseiller conseiller = null;
        try {
            conseiller = this.conseillerBean.findById(conseillerId);

            if (conseiller == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }

            clients = this.clientBean.findByConseiller(conseiller);
            if (clients == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (WebApplicationException ex) {
            logger.log(Level.SEVERE,
                    "Error calling findById()",
                    new Object[]{ex.getMessage()});
        }
        return clients;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Client getClient(@PathParam("id") Long clientId) {
        Client client = null;
        try {
            client = this.clientBean.findById(clientId);
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling findClient() for clientId {0}. {1}",
                    new Object[]{clientId, ex.getMessage()});
        }
        return client;
    }

    @POST
    @Path("client_interne")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createClientInterne(@Valid ClientInterne client) {
        logger.log(Level.INFO, client.toString());
        try {
            this.clientBean.persist(client);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error creating client for clientId {0}. {1}",
                    new Object[]{client.getId(), e.getMessage()});
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @POST
    @Path("client_tiers")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createClientTiers(ClientTiers client) {

        logger.log(Level.INFO, client.toString());
        try {
            this.clientBean.persist(client);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error creating client for clientId {0}. {1}",
                    new Object[]{client.getId(), e.getMessage()});
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateClient(@PathParam("id") Long clientId,
            Client client) {

        try {
            Client oldClient = this.clientBean.findById(clientId);

            if (oldClient == null) {
                // return a not found in http/web format
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                this.clientBean.merge(client);
                return Response.ok().status(303).build(); //return a seeOther code
            }
        } catch (WebApplicationException e) {
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteClient(@PathParam("id") Long clientId) {
        logger.log(Level.INFO, clientId.toString());
        try {
            if (!this.clientBean.remove(clientId)) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling deleteClient() for clientId {0}. {1}",
                    new Object[]{clientId, ex.getMessage()});
        }
    }

}
