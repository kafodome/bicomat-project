package com.c353.projet.bicomat.ejb;

import com.c353.projet.bicomat.data.Conseiller;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AFK
 */
@Stateless
public class ConseillerBean {

    @PersistenceContext
    EntityManager em;
    private static final Logger logger = Logger.getLogger("com.c353.projet.bicomat.ejb.ConseillerBean");

    public void persist(Conseiller conseiller) {
        try {
            em.persist(conseiller);
        } catch (Exception e) {
            logger.warning("Une erreur s'est produite pendant l'enregistrement du Conseiller");
            logger.warning(e.getMessage());
        }
    }

    public void merge(Conseiller conseiller) {
        try {
            em.merge(conseiller);
        } catch (Exception e) {
            logger.warning("Une erreur s'est produite pendant la mise à jour du Conseiller");
            logger.warning(e.getMessage());
        }    
    }
    
    
    public Conseiller findById(Long conseillerId) {
        Conseiller conseiller = null;
        try {
            conseiller = em.find(Conseiller.class, conseillerId);
            return conseiller;
        } catch (Exception e) {
            logger.log(Level.WARNING,
                    "Impossible de retrouver le conseiller ayant l'ID {0}", conseillerId);
        }
        return conseiller;
    }

    public List<Conseiller> findAllConseillers() {
        List<Conseiller> conseillers = new ArrayList<>();
        try {
            conseillers = (List<Conseiller>) em.createNamedQuery("findAllConseillers").getResultList();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Une erreur s'est produite lors de la "
                    + "récupération des conseillers");
        }
        return conseillers;
    }

    public boolean remove(Long conseillerId) {
        Conseiller conseiller;
        try {
            conseiller = em.find(Conseiller.class, conseillerId);
            em.remove(conseiller);
            return true;
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Impossible de supprimer le "
                    + "conseiller ayant l'ID {0}", conseillerId);
            return false;
        }
    }

}
