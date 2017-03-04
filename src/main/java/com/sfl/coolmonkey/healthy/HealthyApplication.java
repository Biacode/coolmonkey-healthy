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
                healthCheckEndpointService.create("website", "http://website");
                healthCheckEndpointService.create("gateway", "http://gatewayms:8080/api/heartbeat");
                healthCheckEndpointService.create("company", "http://companyms:8081/heartbeat");
                healthCheckEndpointService.create("companyms-scheduler", "http://companyms-scheduler:9094");
                healthCheckEndpointService.create("address", "http://addressms:8082/heartbeat");
                healthCheckEndpointService.create("addressms-consumer", "http://addressms-consumer:9091");
                healthCheckEndpointService.create("addressms-scheduler", "http://addressms-scheduler:9097");
                healthCheckEndpointService.create("emailtemplate", "http://emailtemplatems:8088/heartbeat");
                healthCheckEndpointService.create("notifications", "http://notificationsms:8083/heartbeat");
                healthCheckEndpointService.create("notificationsms-consumer", "http://notificationsms-consumer:9092");
                healthCheckEndpointService.create("translations", "http://translationsms:8084/heartbeat");
                healthCheckEndpointService.create("coolfs", "http://coolfsms:8089/heartbeat");
                healthCheckEndpointService.create("paymentms", "http://paymentms:8085/rest/heartbeat");
                healthCheckEndpointService.create("paymentms-scheduler", "http://paymentms-scheduler:9093");
                healthCheckEndpointService.create("coolsearch", "http://coolsearchms:8086/heartbeat");
                healthCheckEndpointService.create("reporting", "http://reportingms:8091/heartbeat");
                healthCheckEndpointService.create("reportingms-consumer", "http://reportingms-consumer:9096");
                healthCheckEndpointService.create("reportingms-scheduler", "http://reportingms-scheduler:9095");
                healthCheckEndpointService.create("wkhtmltopdf-docker-http", "http://wkhtmltopdf-docker-http:8087");
            }
        }
    }
}
