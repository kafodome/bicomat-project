/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.c353.projet.bicomat.resource.config;

import com.c353.projet.bicomat.filters.CorsFilter;
import com.c353.projet.bicomat.resource.AuthenticationService;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author Kwami Anukana AFODOME
 */
@ApplicationPath("/auth")
public class AuthenticationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(AuthenticationService.class);
        classes.add(CorsFilter.class);
        return classes;
    }
}
