package com.c353.projet.bicomat.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@DiscriminatorValue("2")
public class OperationDifferee extends Operation implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date echeance;

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

}
