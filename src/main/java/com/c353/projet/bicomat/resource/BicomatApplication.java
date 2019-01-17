package com.c353.projet.bicomat.resource;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@ApplicationPath("/webapi")
public class BicomatApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(BanqueService.class);
        classes.add(ClientService.class);
        classes.add(CompteService.class);
        classes.add(ConseillerService.class);
        return classes;
    }

}
