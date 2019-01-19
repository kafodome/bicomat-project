package com.c353.projet.bicomat.data;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Entity
@DiscriminatorValue("2")
@NamedQueries({
    @NamedQuery(
            name = "findByConseiller",
            query = "SELECT c FROM ClientInterne c "
            + "WHERE c.conseiller = :conseiller ORDER BY c.id"
    )
    ,    
    @NamedQuery(
            name = "authenticateClient",
            query = "SELECT c FROM ClientInterne c "
            + "WHERE c.login = :login AND c.password = :password"
    )
})
@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "2")
public class ClientInterne extends Client {

    private String login;

    private String password;

    private String annee;

    private String token;

    @Column(name = "num_portable")
    private String numPortable;

    @ManyToOne
    @XmlTransient
    private Conseiller conseiller;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Conseiller getConseiller() {
        return conseiller;
    }

    public List<Banque> getBanques() {
        return banques;
    }

    public void setBanques(List<Banque> banques) {
        this.banques = banques;
    }

    public List<Compte> getComptes() {
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public void setConseiller(Conseiller conseiller) {
        this.conseiller = conseiller;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.numPortable);
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
        final ClientInterne other = (ClientInterne) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return Objects.equals(this.numPortable, other.numPortable);
    }

}
