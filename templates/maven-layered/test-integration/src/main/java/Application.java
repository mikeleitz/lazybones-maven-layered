package ${base_package}.test;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

import ${base_package}.service.AppUserRestService;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
@SpringBootApplication
@EnableFeignClients(basePackages="${base_package}.service")
public class ${project_class_name}TestApplication {

    private static final Logger _logger = LoggerFactory.getLogger(${project_class_name}TestApplication.class);

    @Autowired AppUserRestService _appUserRestService;

    public static void main(String[] args) {
        SpringApplication.run(${project_class_name}TestApplication.class, args);
    }

    // Because the service runs at 8080, and because Spring Boot seems to require a ServletContainer, the port is changed
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            container.setPort(8012);
        });
    }

    @Bean
    CommandLineRunner test(){
        return(args)->{
            // The result.
            System.out.println(_appUserRestService.getAppUsers().getContent());
            System.out.println(_appUserRestService.findById("1").getContent());

            System.out.println(_appUserRestService.getAppUsers());
            System.out.println(_appUserRestService.findById("1"));
        };
    }

}