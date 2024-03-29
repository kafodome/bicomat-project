package com.c353.projet.bicomat.data;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author AFK
 */
@Entity
@DiscriminatorValue("02")
@XmlRootElement(name = "compte")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "02")
public class CompteEpargne extends Compte implements Serializable {

    @Column(name = "taux_remuneration")
    @XmlElement(name = "taux_remuneration")
    private double tauxRemuneration;

    public double getTauxRemuneration() {
        return tauxRemuneration;
    }

    public void setTauxRemuneration(double tauxRemuneration) {
        this.tauxRemuneration = tauxRemuneration;
    }

}
