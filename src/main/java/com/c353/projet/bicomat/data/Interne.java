/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c353.projet.bicomat.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@DiscriminatorValue("2")
@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
public class Interne extends Client {

    private String loging;
    
    private String password;
    
    private String annee;
    
    @Column(name = "num_portable")
    private String numPortable;

    public String getLoging() {
        return loging;
    }

    public void setLoging(String loging) {
        this.loging = loging;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getNumPortable() {
        return numPortable;
    }

    public void setNumPortable(String numPortable) {
        this.numPortable = numPortable;
    }
}
