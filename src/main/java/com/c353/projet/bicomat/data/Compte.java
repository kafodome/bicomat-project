package com.c353.projet.bicomat.data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "type_compte")
@Table(name = "compte")
@NamedQueries({
    @NamedQuery(
            name = "findAllComptes",
            query = "SELECT c FROM Compte c ORDER BY c.numCompte"
    ), 
    @NamedQuery(name = "findByClient",
            query = "SELECT c FROM Compte c WHERE c.client = :client")})
@XmlRootElement(name = "compte")
@XmlSeeAlso({CompteCheque.class, CompteEpargne.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Compte implements Serializable {

    @Id
    @Column(name = "num_compte")
    @XmlElement(name = "num_compte", required = true)
    protected String numCompte;

    protected Double solde = 0.0;

    @ManyToOne
    @JoinColumn(name = "proprietaire", nullable = false)
    @XmlElement(name = "proprietaire", required = true)
    protected Client client;
    
    @OneToMany(mappedBy = "compteA")
    protected List<Operation> operations;

    public String getNumCompte() {
        return numCompte;
    }

    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.numCompte);
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
        final Compte other = (Compte) obj;
        return Objects.equals(this.numCompte, other.numCompte);
    }

}
