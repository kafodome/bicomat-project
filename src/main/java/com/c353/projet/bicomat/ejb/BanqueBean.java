package com.c353.projet.bicomat.ejb;

import com.c353.projet.bicomat.data.Banque;
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
public class BanqueBean {

    @PersistenceContext
    EntityManager em;
    private static final Logger logger = Logger.getLogger("com.c353.projet.bicomat.ejb.BanqueBean");

    public void persist(Banque banque) {
        try {
            em.persist(banque);
        } catch (Exception e) {
            logger.warning("Something went wrong when persisting the banque");
            logger.warning(e.getMessage());
        }
    }

    public void merge(Banque banque) {
        try {
            em.merge(banque);
        } catch (Exception e) {
            logger.warning("Something went wrong when merging the banque");
            logger.warning(e.getMessage());
        }    
    }
    
    
    public Banque findById(Long banqueId) {
        Banque banque = null;
        try {
            banque = em.find(Banque.class, banqueId);
            return banque;
        } catch (Exception e) {
            logger.log(Level.WARNING,
                    "Could not find banque with ID of {0}", banqueId);
        }
        return banque;
    }

    public List<Banque> findAllBanques() {
        List<Banque> banques = new ArrayList<>();
        try {
            banques = (List<Banque>) em.createNamedQuery("findAllBanques").getResultList();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error when finding all banques");
        }
        return banques;
    }

    public boolean remove(Long banqueId) {
        Banque banque;
        try {
            banque = em.find(Banque.class, banqueId);
            em.remove(banque);
            return true;
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Couldn't remove banque with ID {0}", banqueId);
            return false;
        }
    }

}
