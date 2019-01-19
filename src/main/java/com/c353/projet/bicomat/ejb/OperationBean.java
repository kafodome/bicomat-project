package com.c353.projet.bicomat.ejb;

import com.c353.projet.bicomat.data.Operation;
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
public class OperationBean {

    @PersistenceContext
    EntityManager em;
    private static final Logger logger = Logger.getLogger("com.c353.projet.bicomat.ejb.OperationBean");

    public void persist(Operation operation) {
        try {
            em.persist(operation);
        } catch (Exception e) {
            logger.warning("Une erreur s'est produite pendant l'enregistrement de l'opération");
            logger.warning(e.getMessage());
        }
    }

    public void merge(Operation operation) {
        try {
            em.merge(operation);
        } catch (Exception e) {
            logger.warning("Une erreur s'est produite pendant la mise à jour de l'opération");
            logger.warning(e.getMessage());
        }
    }

    public Operation findById(Long numOperation) {
        Operation operation = null;
        try {
            operation = em.find(Operation.class, numOperation);
            return operation;
        } catch (Exception e) {
            logger.log(Level.WARNING,
                    "Impossible de retrouver l'opération ayant le numéro {0}", numOperation);
        }
        return operation;
    }

    public List<Operation> findAllOperations() {
        List<Operation> operation = new ArrayList<>();
        try {
            operation = (List<Operation>) em.createNamedQuery("findAllOperations").getResultList();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Une erreur s'est produite lors de la "
                    + "récupération des opérations");
        }
        return operation;
    }

    public boolean remove(Long numOperation) {
        Operation operation;
        try {
            operation = em.find(Operation.class, numOperation);
            em.remove(operation);
            return true;
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Impossible de supprimer l'opération "
                    + "ayant le numéro {0}", numOperation);
            return false;
        }
    }

}
