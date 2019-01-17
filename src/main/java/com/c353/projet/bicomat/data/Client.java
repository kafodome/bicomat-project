/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c353.projet.bicomat.data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "type_client")
@Table(name = "client")
@NamedQuery(
        name = "findAllClients",
        query = "SELECT c FROM Client c "
        + "ORDER BY c.id"
)
@XmlRootElement(name = "client")
@XmlSeeAlso({ClientInterne.class, ClientTiers.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(required = true)
    protected Long id;

    @Column(nullable = false)
    protected String nom;

    @Column(nullable = false)
    protected String prenom;

    @Column(nullable = false)
    protected String profession;

    protected String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    @XmlElement(name = "carte_bancaires")
    protected List<CarteBancaire> carteBancaires;

    @ManyToMany(mappedBy = "clients", fetch = FetchType.EAGER)
    @XmlElement(name = "banques")
    protected List<Banque> banques;
    
    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    protected List<Compte> comptes;

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

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CarteBancaire> getCarteBancaires() {
        return carteBancaires;
    }

    public void setCarteBancaires(List<CarteBancaire> carteBancaires) {
        this.carteBancaires = carteBancaires;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.nom);
        hash = 67 * hash + Objects.hashCode(this.prenom);
        hash = 67 * hash + Objects.hashCode(this.email);
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

}
