package com.c353.projet.bicomat.data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@Table(name = "carte_bancaire")
@XmlRootElement(name = "carte_bancaire")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarteBancaire implements Serializable {

    @Id
    @Column(name = "num_carte")
    @XmlAttribute(name = "num_carte", required = true)
    private String numCarte;

    @Column(name = "type_carte")
    @XmlElement(name = "type_carte", required = true)
    private String typeCarte;

    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(required = true)
    private Date echeance;

    @Column(name = "code_crypto")
    @XmlElement(name = "code_crypto", required = true)
    private String codeCrypto;

    @ManyToOne
    @JoinColumn(name = "titulaire")
    @XmlElement(name = "titulaire")
    private Client client;

    public String getNumCarte() {
        return numCarte;
    }

    public void setNumCarte(String numCarte) {
        this.numCarte = numCarte;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public void setTypeCarte(String typeCarte) {
        this.typeCarte = typeCarte;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public String getCodeCrypto() {
        return codeCrypto;
    }

    public void setCodeCrypto(String codeCrypto) {
        this.codeCrypto = codeCrypto;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.numCarte);
        hash = 61 * hash + Objects.hashCode(this.typeCarte);
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
        final CarteBancaire other = (CarteBancaire) obj;
        if (!Objects.equals(this.numCarte, other.numCarte)) {
            return false;
        }
        return Objects.equals(this.typeCarte, other.typeCarte);
    }

    @Override
    public String toString() {
        return "CarteBancaire{" + "numCarte=" + numCarte
                + ", typeCarte=" + typeCarte + ", echeance=" + echeance
                + ", codeCrypto=" + codeCrypto + '}';
    }

}
