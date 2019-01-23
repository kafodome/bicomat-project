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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
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
 * La classe représente la structure des données à enregistrer pour pour une banque.
 *
 * @author Kwami Anukana AFODOME
 * @version 1.0
 */
@Entity
@Table(name = "banque")
@NamedQuery(
        name = "findAllBanques",
        query = "SELECT b FROM Banque b ORDER BY b.id"
)
@XmlRootElement(name = "banque")
@XmlAccessorType(XmlAccessType.FIELD)
public class Banque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(required = true)
    private Long id; // Identifiant de la banque.

    @Column(nullable = false)
    @XmlElement(required = true)
    private String nom; // Nom de la banque.

    @Column(nullable = false)
    @XmlElement(required = true)
    private String adresse; // Adresse de la banque.

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "banque_client",
            joinColumns = @JoinColumn(name = "banque_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
    @XmlTransient
    private List<Client> clients; // Liste des clients de la banque.

    /**
     * Obtient l'identifiant de la banque.
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     *
     * @param adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     *
     * @return
     */
    public List<Client> getClients() {
        return clients;
    }

    /**
     *
     * @param clients
     */
    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.nom);
        hash = 19 * hash + Objects.hashCode(this.adresse);
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
        final Banque other = (Banque) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Banque{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + '}';
    }

}
