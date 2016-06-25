package org.synapsis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackages = {"org.synapsis.repository"})
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }


}