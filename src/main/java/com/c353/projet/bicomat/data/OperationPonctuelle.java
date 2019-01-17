package com.c353.projet.bicomat.data;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@DiscriminatorValue("1")
public class OperationPonctuelle extends Operation implements Serializable {
    
}
