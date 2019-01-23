package com.c353.projet.bicomat.data;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author AFK
 */
@Entity
@DiscriminatorValue("01")
@XmlRootElement(name = "compte")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "01")
public class CompteCheque extends Compte implements Serializable {

    private boolean decouvert;

    public boolean isDecouvert() {
        return decouvert;
    }

    public void setDecouvert(boolean decouvert) {
        this.decouvert = decouvert;
    }
}
