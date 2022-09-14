package br.com.uniamerica.gajigo.config;

import br.com.uniamerica.gajigo.events.UserEventHandler;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public abstract class MvcConfigurer {

    @Bean
    WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> enableDefaultServlet() {
        return (factory) -> factory.setRegisterDefaultServlet(true);
    }

    @Bean
    UserEventHandler userEventHandler() {
        return new UserEventHandler();
    }

    public abstract void addResourceHandlers(ResourceHandlerRegistry registry);
}
