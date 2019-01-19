package com.c353.projet.bicomat.ejb;

import com.c353.projet.bicomat.data.AuthSession;
import com.c353.projet.bicomat.data.Banque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AFK
 */
@Stateless
public class AuthSessionBean {

    @PersistenceContext
    EntityManager em;
    private static final Logger logger = Logger.
            getLogger("com.c353.projet.bicomat.ejb.AuthSessionBean");

    public void persist(AuthSession authSession) {
        try {
            em.persist(authSession);
        } catch (Exception e) {
            logger.warning("Something went wrong when persisting the authSession");
            logger.warning(e.getMessage());
        }
    }

    public void merge(AuthSession authSession) {
        try {
            em.merge(authSession);
        } catch (Exception e) {
            logger.warning("Something went wrong when merging the authSession");
            logger.warning(e.getMessage());
        }
    }

    public AuthSession findById(Long authSessionId) {
        AuthSession authSession = null;
        try {
            authSession = em.find(AuthSession.class, authSessionId);
            return authSession;
        } catch (Exception e) {
            logger.log(Level.WARNING,
                    "Could not find authSession with ID of {0}", authSessionId);
        }
        return authSession;
    }

    public AuthSession findByToken(String token) {
        AuthSession authSession = null;
        try {
            authSession = (AuthSession) em.createNamedQuery("findByToken").
                    setParameter("token", token).getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.WARNING, ex.getMessage());
        }
        return authSession;
    }

    public List<AuthSession> findAllAuthSessions() {
        List<AuthSession> authSessions = new ArrayList<>();
        try {
            authSessions = (List<AuthSession>) em.createNamedQuery("findAllAuthSessions").getResultList();
        } catch (Exception ex) {
            logger.log(Level.WARNING, "Error when finding all authSessions");
        }
        return authSessions;
    }

    public boolean remove(Long authSessionId) {
        AuthSession authSession;
        try {
            authSession = em.find(AuthSession.class, authSessionId);
            em.remove(authSession);
            return true;
        } catch (Exception ex) {
            logger.log(Level.WARNING,
                    "Couldn't remove AuthSession with ID {0}", authSessionId);
            return false;
        }
    }

    public Date getCurrentDate() {
        String jpql = "SELECT DATE_ADD( CURRENT_TIMESTAMP , INTERVAL '30' MINUTE)";
        Query q = em.createNativeQuery(jpql);
        return (Date) q.getSingleResult();
    }

    public Integer getSessionDuration() {
        String jpql = "SELECT DATE_ADD( CURRENT_TIMESTAMP , INTERVAL '30' MINUTE)";
        Query q = em.createNativeQuery(jpql);
        return (Integer) q.getSingleResult();
    }
}
