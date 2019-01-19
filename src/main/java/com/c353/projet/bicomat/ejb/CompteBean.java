package com.c353.projet.bicomat.ejb;

import com.c353.projet.bicomat.data.Client;
import com.c353.projet.bicomat.data.Compte;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Stateless
public class CompteBean {

    @PersistenceContext
    EntityManager em;
    private static final Logger logger = Logger.getLogger("com.c353.projet.bicomat.ejb.CompteBean");

    public void persist(Compte compte) {
        try {
            em.persist(compte);
        } catch (Exception e) {
            logger.warning("Une erreur s'est produite pendant l'enregistrement du compte");
            logger.warning(e.getMessage());
        }
    }

    public void merge(Compte compte) {
        try {
            em.merge(compte);
        } catch (Exception e) {
            logger.warning("Une erreur s'est produite pendant la mise à jour du compte");
            logger.warning(e.getMessage());
        }
    }

    public Compte findById(String numCompte) {
        Compte compte = null;
        try {
            compte = em.find(Compte.class, numCompte);
            return compte;
        } catch (Exception e) {
            logger.log(Level.WARNING,
                    "Impossible de retrouver le compte ayant le numéro {0}", numCompte);
        }
        return compte;
    }

    public List<Compte> findByClient(Client client) {
        List<Compte> comptes = new ArrayList<>();
        try {
            comptes = (List<Compte>) em.createNamedQuery("findByClient").
                    setParameter("client", client).getResultList();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Une erreur s'est produite lors de "
                    + "la récupération des comptes du client");

        }
        return comptes;
    }

    public List<Compte> findAllComptes() {
        List<Compte> comptes = new ArrayList<>();
        try {
            comptes = (List<Compte>) em.createNamedQuery("findAllComptes").getResultList();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Une erreur s'est produite lors de "
                    + "la récupération des comptes");
        }
        return comptes;
    }

    public boolean remove(String numCompte) {
        Compte compte;
        try {
            compte = em.find(Compte.class, numCompte);
            em.remove(compte);
            return true;
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Impossible de supprimer le compte ayant "
                    + "le numéro {0}", numCompte);
            return false;
        }
    }

}
