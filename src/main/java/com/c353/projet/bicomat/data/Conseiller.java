package com.c353.projet.bicomat.data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@Table(name = "conseiller")
@NamedQueries({
    @NamedQuery(
            name = "findAllConseillers",
            query = "SELECT c FROM Conseiller c ORDER BY c.id"
    )
    ,
    @NamedQuery(
            name = "authenticateConseiller",
            query = "SELECT c FROM Conseiller c "
            + "WHERE c.login = :login AND c.password = :password"
    )

})
@XmlRootElement(name = "conseiller")
@XmlAccessorType(XmlAccessType.FIELD)
public class Conseiller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(required = true)
    private Long id;

    @Column(nullable = false)
    @XmlElement(required = true)
    private String nom;

    @Column(nullable = false)
    @XmlElement(required = true)
    private String prenom;

    @XmlElement(required = true)
    private String login;

    private String password;

    private String token;

    @OneToMany(mappedBy = "conseiller",fetch = FetchType.LAZY)
    @XmlTransient
    private List<ClientInterne> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<ClientInterne> getClients() {
        return clients;
    }

    public void setClients(List<ClientInterne> clients) {
        this.clients = clients;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.prenom);
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
        final Conseiller other = (Conseiller) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return Objects.equals(this.prenom, other.prenom);
    }

    @Override
    public String toString() {
        return "Conseiller{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", login=" + login + '}';
    }

}
