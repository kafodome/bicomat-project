package com.c353.projet.bicomat.resource;

import com.c353.projet.bicomat.data.Banque;
import com.c353.projet.bicomat.ejb.BanqueBean;
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
@Path("/banque")
public class BanqueService {

    public static final Logger logger
            = Logger.getLogger(BanqueService.class.getCanonicalName());
    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder cb;

    @Inject
    BanqueBean banqueBean;

    @PostConstruct
    private void init() {
        cb = em.getCriteriaBuilder();
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Banque> getAllBanques() {
        List<Banque> banques = null;
        try {
            banques = this.banqueBean.findAllBanques();
            if (banques == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (WebApplicationException ex) {
            logger.log(Level.SEVERE,
                    "Error calling findAllBanques()",
                    new Object[]{ex.getMessage()});
        }
        return banques;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Banque getBanque(@PathParam("id") Long banqueId) {
        Banque banque = null;

        try {
            banque = this.banqueBean.findById(banqueId);
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling findBanque() for banqueId {0}. {1}",
                    new Object[]{banqueId, ex.getMessage()});
        }
        return banque;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createBanque(Banque banque) {

        try {
            this.banqueBean.persist(banque);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error creating banque for banqueId {0}. {1}",
                    new Object[]{banque.getId(), e.getMessage()});
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateBanque(@PathParam("id") Long banqueId,
            Banque banque) {

        try {
            Banque oldBanque = this.banqueBean.findById(banqueId);

            if (oldBanque == null) {
                // return a not found in http/web format
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                this.banqueBean.merge(banque);
                return Response.ok().status(303).build(); //return a seeOther code
            }
        } catch (WebApplicationException e) {
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteBanque(@PathParam("id") Long banqueId) {
        logger.log(Level.INFO, banqueId.toString());
        try {
            if (!this.banqueBean.remove(banqueId)) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling deleteBanque() for banqueId {0}. {1}",
                    new Object[]{banqueId, ex.getMessage()});
        }
    }

}
