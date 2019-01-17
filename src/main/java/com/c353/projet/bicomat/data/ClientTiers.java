package com.c353.projet.bicomat.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@DiscriminatorValue("1")
@XmlRootElement(name = "client")
@XmlType(name = "1")
public class ClientTiers extends Client {

}
