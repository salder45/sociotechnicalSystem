/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.config;

import java.util.EnumSet;
import java.util.Set;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author laboratoriointerface
 */
public class StartUpConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(ComponentConfig.class, DataConfig.class, MailConfig.class, WebConfig.class);
        context.setDisplayName(Constants.APP_NAME);
        //
        FilterRegistration.Dynamic sitemeshFilter = sc.addFilter("sitemeshFilter", new ConfigurableSiteMeshFilter());
        sitemeshFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        FilterRegistration.Dynamic characterEncodingFilter = sc.addFilter("characterEncodingFilter", new CharacterEncodingFilter());
        characterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        characterEncodingFilter.setInitParameter("encoding", Constants.DEFAULT_ENCODING);
        characterEncodingFilter.setInitParameter("forceEncoding", "true");
        //
        sc.addListener(new ContextLoaderListener(context));
        //
        DispatcherServlet servlet = new DispatcherServlet();

        servlet.setContextConfigLocation("");
        //
        ServletRegistration.Dynamic appServlet = sc.addServlet("appServlet", servlet);
        appServlet.setLoadOnStartup(1);
        appServlet.setAsyncSupported(true);

        Set<String> mappingConflicts = appServlet.addMapping("/");
        if (!mappingConflicts.isEmpty()) {
            throw new IllegalStateException("'appServlet' cannot be mapped to '/' under Tomcat versions <= 7.0.14");
        }

    }

}
