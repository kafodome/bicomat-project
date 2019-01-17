package com.c353.projet.bicomat.resource;

import com.c353.projet.bicomat.data.Compte;
import com.c353.projet.bicomat.data.CompteCheque;
import com.c353.projet.bicomat.data.CompteEpargne;
import com.c353.projet.bicomat.ejb.CompteBean;
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
@Path("/compte")
public class CompteService {

    public static final Logger logger
            = Logger.getLogger(CompteService.class.getCanonicalName());
    @PersistenceContext
    private EntityManager em;
    private CriteriaBuilder cb;

    @Inject
    CompteBean compteBean;

    @PostConstruct
    private void init() {
        cb = em.getCriteriaBuilder();
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Compte> getAllComptes() {
        List<Compte> comptes = null;
        try {
            comptes = this.compteBean.findAllComptes();
            if (comptes == null) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (WebApplicationException ex) {
            logger.log(Level.SEVERE,
                    "Error calling findAllComptes()",
                    new Object[]{ex.getMessage()});
        }
        return comptes;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Compte getCompte(@PathParam("id") String numCompte) {
        Compte compte = null;

        try {
            compte = this.compteBean.findById(numCompte);
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling findCompte() for numCompte {0}. {1}",
                    new Object[]{numCompte, ex.getMessage()});
        }
        return compte;
    }

    @POST
    @Path("compte_cheque")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createCompteCheque(@Valid CompteCheque compte) {
        try {
            this.compteBean.persist(compte);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error creating compte for numCompte {0}. {1}",
                    new Object[]{compte.getNumCompte(), e.getMessage()});
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    @POST
    @Path("compte_epargne")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createCopmteEpargne(CompteEpargne compte) {

        logger.log(Level.INFO, compte.toString());
        try {
            this.compteBean.persist(compte);
        } catch (Exception e) {
            logger.log(Level.SEVERE,
                    "Error creating client for numCompte {0}. {1}",
                    new Object[]{compte.getNumCompte(), e.getMessage()});
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateCompte(@PathParam("id") String numCompte,
            Compte compte) {

        try {
            Compte oldCompte = this.compteBean.findById(numCompte);

            if (oldCompte == null) {
                // return a not found in http/web format
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            } else {
                this.compteBean.merge(compte);
                return Response.ok().status(303).build(); //return a seeOther code
            }
        } catch (WebApplicationException e) {
            throw new WebApplicationException(e,
                    Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("{id}")
    public void deleteCompte(@PathParam("id") String numCompte) {
        try {
            if (!this.compteBean.remove(numCompte)) {
                throw new WebApplicationException(Response.Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE,
                    "Error calling deleteCompte() for numCompte {0}. {1}",
                    new Object[]{numCompte, ex.getMessage()});
        }
    }

}
