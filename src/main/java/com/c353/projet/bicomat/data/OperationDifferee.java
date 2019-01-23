package com.c353.projet.bicomat.data;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@DiscriminatorValue("2")
public class OperationDifferee extends Operation implements Serializable {

    private LocalDateTime echeance;

    public LocalDateTime getEcheance() {
        return echeance;
    }

    public void setEcheance(LocalDateTime echeance) {
        this.echeance = echeance;
    }

}
