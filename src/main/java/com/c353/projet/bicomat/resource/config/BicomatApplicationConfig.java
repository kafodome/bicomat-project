package com.c353.projet.bicomat.resource.config;

import com.c353.projet.bicomat.filters.AuthenticationFilter;
import com.c353.projet.bicomat.filters.CorsFilter;
import com.c353.projet.bicomat.filters.Secured;
import com.c353.projet.bicomat.resource.BanqueService;
import com.c353.projet.bicomat.resource.ClientService;
import com.c353.projet.bicomat.resource.CompteService;
import com.c353.projet.bicomat.resource.ConseillerService;
import com.c353.projet.bicomat.resource.OperationService;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@Secured
@ApplicationPath("/webapi")
public class BicomatApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(AuthenticationFilter.class);
        classes.add(CorsFilter.class);
        classes.add(BanqueService.class);
        classes.add(ClientService.class);
        classes.add(CompteService.class);
        classes.add(ConseillerService.class);
        classes.add(OperationService.class);
        return classes;
    }

}
