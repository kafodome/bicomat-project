package com.c353.projet.bicomat.resource;

import com.c353.projet.bicomat.data.Conseiller;
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
@Path("/conseiller")
public class ConseillerService {

    public static final Logger logger
            = Logger.getLogger(ConseillerService.class.getCanonicalName());
    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder cb;

    @Inject
    ConseillerBean conseillerBean;

    @PostConstruct
    private void init() {
        cb = em.getCriteriaBuilder();
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Conseiller> getAllConseiller() {
        
        List<Conseiller> conseillers = null;
        try {            
            conseillers = this.conseillerBean.findAllConseillers();
            if (conseillers == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (WebApplicationException ex) {
            logger.log(Level.SEVERE,
                    "Error calling findAllConseillers()",
                    new Object[]{ex.getMessage()});
        }
        return conseillers;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Conseiller getConseiller(@PathParam("id") Long conseillerId) {
        Conseiller conseiller = null;

        try {
            conseiller = this.conseillerBean.findById(conseillerId);
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling findConseiller() for conseillerId {0}. {1}",
                    new Object[]{conseillerId, ex.getMessage()});
        }
        return conseiller;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createConseiller(Conseiller conseiller) {

        try {
            this.conseillerBean.persist(conseiller);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error creating conseiller for conseillerId {0}. {1}",
                    new Object[]{conseiller.getId(), e.getMessage()});
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateConseiller(@PathParam("id") Long conseillerId,
            Conseiller conseiller) {

        try {
            Conseiller oldConseiller = this.conseillerBean.findById(conseillerId);

            if (oldConseiller == null) {
                // return a not found in http/web format
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                this.conseillerBean.merge(conseiller);
                return Response.ok().status(303).build(); //return a seeOther code
            }
        } catch (WebApplicationException e) {
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteConseiller(@PathParam("id") Long conseillerId) {
        logger.log(Level.INFO, conseillerId.toString());
        try {
            if (!this.conseillerBean.remove(conseillerId)) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling deleteConseiller() for conseillerId {0}. {1}",
                    new Object[]{conseillerId, ex.getMessage()});
        }
    }

}
