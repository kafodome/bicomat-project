package com.c353.projet.bicomat.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * La classe AuthSession permet de sauvegarder et mettre à jour les sessions de
 * connexion des clients internes et des conseillers.
 *
 * @author Kwami Anukana AFODOME
 * @version 1.0
 */
@Entity
@Table(name = "auth_session")
@NamedQueries({
    @NamedQuery(name = "findAllAuthSessions",
            query = "SELECT a FROM AuthSession a ORDER BY a.id")
    ,
    @NamedQuery(name = "findByToken",
            query = "SELECT a FROM AuthSession a WHERE a.token = :token")
})
public class AuthSession implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identifiant de la session.

    @Column(nullable = false)
    private String username; // Nom utilisateur rattaché à la session.

    @Column(nullable = false)
    private String token; // Jeton créé pour la session.

    // Date de début de la session.
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate = LocalDateTime.now();

    // Date d'expiration de la session.
    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(15L);

    /**
     * Obtient l'identifiant de la session.
     *
     * @return le numéro identifiant la session.
     */
    public Long getId() {
        return id;
    }

    /**
     * Affecte un identifiant à la session.
     *
     * @param id l'identifiant à affecter à la session.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtient le nom utilisateur de la session.
     *
     * @return le nom utilisateur de la session.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Affecte un identifiant à la session.
     *
     * @param username nom utilisateur à affecter à la session.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Obtient le jeton de la session.
     *
     * @return le jeton de la session.
     */
    public String getToken() {
        return token;
    }

    /**
     * Affecte un jeton à la session.
     *
     * @param token le jeton à affecter à la session.
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Obtient la date de début de la session.
     *
     * @return la date de début de la session.
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Affecte une date à la session.
     *
     * @param startDate la date à affecter à la session.
     */
    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Obtient la date d'expiration de la session.
     *
     * @return la date d'expiration de la session.
     */    
    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    /**
     * Affecte une date à la session.
     *
     * @param expiryDate la date à affeter à la session.
     */    
    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.username);
        hash = 67 * hash + Objects.hashCode(this.token);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthSession other = (AuthSession) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.token, other.token);
    }

    @Override
    public String toString() {
        return "AuthSession{" + "id=" + id + ", username=" + username + ", token=" + token + ", startDate=" + startDate + ", expiryDate=" + expiryDate + '}';
    }

}
