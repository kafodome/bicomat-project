package com.c353.projet.bicomat.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@Inheritance
@DiscriminatorColumn(name = "type_operation")
@Table(name = "operation")
@NamedQueries({
    @NamedQuery(
            name = "findAllOperations",
            query = "SELECT o FROM Operation o ORDER BY o.numOperation"
    )
})

@XmlRootElement(name = "operation")
@XmlSeeAlso({OperationDifferee.class, OperationPonctuelle.class})
@XmlAccessorType(XmlAccessType.FIELD)
public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_operation")
    @XmlAttribute(name = "num_operation", required = true)
    protected Long numOperation;

    @Column(name = "date_operation", nullable = false)
    @XmlElement(name = "date_operation", required = true)
    protected LocalDateTime dateOperation = LocalDateTime.now();

    @Column(name = "montant_operation", nullable = false)
    @XmlElement(name = "montant_operation", required = true)
    protected Double montantOperation;

    @Column(nullable = false)
    protected boolean signe;

    @ManyToOne
    @JoinColumn(name = "compte_a", nullable = false)
    @XmlElement(name = "compte_a", required = true)
    protected Compte compteA;

    @ManyToOne
    @JoinColumn(name = "compte_b", nullable = false)
    @XmlElement(name = "compte_b", required = true)
    protected Compte compteB;

    public Long getNumOperation() {
        return numOperation;
    }

    public void setNumOperation(Long numOperation) {
        this.numOperation = numOperation;
    }

    public LocalDateTime getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(LocalDateTime dateOperation) {
        this.dateOperation = dateOperation;
    }

    public Double getMontantOperation() {
        return montantOperation;
    }

    public void setMontantOperation(Double montantOperation) {
        this.montantOperation = montantOperation;
    }

    public boolean isSigne() {
        return signe;
    }

    public void setSigne(boolean signe) {
        this.signe = signe;
    }

    public Compte getCompteA() {
        return compteA;
    }

    public void setCompteA(Compte compteA) {
        this.compteA = compteA;
    }

    public Compte getCompteB() {
        return compteB;
    }

    public void setCompteB(Compte compteB) {
        this.compteB = compteB;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.numOperation);
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
        final Operation other = (Operation) obj;
        return Objects.equals(this.numOperation, other.numOperation);
    }

    @Override
    public String toString() {
        return "Operation{" + "numOperation=" + numOperation + ", dateOperation=" + dateOperation + ", montantOperation=" + montantOperation + ", signe=" + signe + ", compteA=" + compteA + ", compteB=" + compteB + '}';
    }

}
