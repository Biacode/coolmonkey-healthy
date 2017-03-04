package com.sfl.coolmonkey.healthy;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sfl.coolmonkey.healthy.service.endpoint.HealthCheckEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@SpringBootApplication
@ImportResource({"classpath:applicationContext-boot.xml"})
@PropertySource(value = "classpath:application.properties")
@PropertySource(value = "classpath:coolmonkey-healthyms.properties")
@PropertySource(value = "file:${user.home}/coolmonkey-healthyms.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file:${user.home}/coolmonkey/coolmonkey-healthyms.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file:${user.home}/coolmonkey-commons.properties", ignoreResourceNotFound = true)
@PropertySource(value = "file:${user.home}/coolmonkey/coolmonkey-commons.properties", ignoreResourceNotFound = true)
public class HealthyApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthyApplication.class, args);
    }

    @Bean
    public Client jerseyClient() {
        return ClientBuilder.newBuilder().register(JacksonJsonProvider.class).build();
    }

    @Component
    class Initializer implements CommandLineRunner {

        private final HealthCheckEndpointService healthCheckEndpointService;

        @Autowired
        public Initializer(final HealthCheckEndpointService healthCheckEndpointService) {
            this.healthCheckEndpointService = healthCheckEndpointService;
        }

        @Override
        public void run(final String... strings) throws Exception {
            if (strings.length > 0 && "init".equals(strings[0])) {
                // TODO: Add "environment" logic, so we can get all endpoints by environment setting
                // TODO: Add email credentials to "environment", so we will not hardcode the emails
                healthCheckEndpointService.create("my-microservice-name", "http://my-microservice-url.com");
            }
        }
    }
}
