package com.c353.projet.bicomat.ejb;

import com.c353.projet.bicomat.data.Client;
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
public class ClientBean {

    @PersistenceContext
    EntityManager em;
    private static final Logger logger = Logger.getLogger("com.c353.projet.bicomat.ejb.ClientBean");

    public void persist(Client client) {
        try {
            em.persist(client);
        } catch (Exception e) {
            logger.warning("Something went wrong when persisting the client");
            logger.warning(e.getMessage());
        }
    }

    public void merge(Client client) {
        try {
            em.merge(client);
        } catch (Exception e) {
            logger.warning("Something went wrong when merging the client");
            logger.warning(e.getMessage());
        }    
    }
    
    
    public Client findById(Long clientId) {
        Client client = null;
        try {
            client = em.find(Client.class, clientId);
            return client;
        } catch (Exception e) {
            logger.log(Level.WARNING,
                    "Could not find client with ID of {0}", clientId);
        }
        return client;
    }

    public List<Client> findAllClients() {
        List<Client> clients = new ArrayList<>();
        try {
            clients = (List<Client>) em.createNamedQuery("findAllClients").getResultList();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error when finding all clients");
        }
        return clients;
    }

    public boolean remove(Long clientId) {
        Client client;
        try {
            client = em.find(Client.class, clientId);
            em.remove(client);
            return true;
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Couldn't remove client with ID {0}", clientId);
            return false;
        }
    }

}
