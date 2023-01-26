package com.dfg.icon.config;

import org.apache.catalina.connector.Connector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private static Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
    static String[] pattern = "/*.*,/static/**,/fonts/**".split(",");;

    @Autowired
    private WebMvcProperties webMvcProperties;
    @Autowired
    private ResourceProperties resourceProperties;

    private void addResourceHandler(ResourceHandlerRegistry registry, String pattern, String ... locations) {
        if (pattern.equals(webMvcProperties.getStaticPathPattern())) {
            String[] staticLocations = resourceProperties.getStaticLocations();
            String[] resolvedLocations = new String[locations.length + staticLocations.length];
            System.arraycopy(locations, 0, resolvedLocations, 0, locations.length);
            System.arraycopy(staticLocations, 0, resolvedLocations, locations.length, staticLocations.length);

            registry.addResourceHandler(pattern).addResourceLocations(resolvedLocations);
            logger.debug(String.format("addResourceHandler staticPattern:%s locations:%s",pattern, Arrays.toString(resolvedLocations)));
        } else {
            registry.addResourceHandler(pattern).addResourceLocations(locations);
            logger.debug(String.format("addResourceHandler pattern:%s locations:%s",pattern, Arrays.toString(locations)));
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uiResourceRoot = "classpath:/META-INF/resources/frontend";
        addResourceHandler(registry, "/**", uiResourceRoot);
        for (String staticPattern : this.pattern) {
            String uiResourceLocation = uiResourceRoot+Paths.get(staticPattern).getParent();
            if (!uiResourceLocation.endsWith("/")) {
                uiResourceLocation += "/";
            }
            addResourceHandler(registry, staticPattern, uiResourceLocation);
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/index.html");

        // Map "/word", "/word/word", and "/word/word/word" - except for anything starting with "/api/..." or ending with
        // a file extension like ".js" - to index.html. By doing this, the client receives and routes the url. It also
        // allows client-side URLs to be bookmarked.

        // Single directory level - no need to exclude "api"
        registry.addViewController("/{x:[\\w\\-]+}")
                .setViewName("forward:/index.html");
        // Multi-level directory path, need to exclude "api" on the first part of the path
        registry.addViewController("/{x:^(?!api$).*$}/**/{y:[\\w\\-]+}")
                .setViewName("forward:/index.html");
    }

//    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> errorPageCustomizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {

            private ErrorPage errorPage(HttpStatus httpStatus) {
                return new ErrorPage(httpStatus, String.format("%s/%d","/error",httpStatus.value()));
            }

            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                Set<ErrorPage> errorPages = new HashSet<>();
                errorPages.add(errorPage(HttpStatus.NOT_FOUND));
                errorPages.add(errorPage(HttpStatus.INTERNAL_SERVER_ERROR));
                factory.setErrorPages(errorPages);
            }
        };
    }

    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

            @Override
            public void customize(Connector connector) {
                connector.setProperty("relaxedQueryChars", "|{}[]");
            }
        });
        return factory;
    }

}
