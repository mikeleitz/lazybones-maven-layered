package ${base_package}.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import ${base_package}.dao.repository.AppUserRepository;
import ${base_package}.dao.domain.AppUserDomain;
import ${base_package}.config.${project_class_name}PersistenceConfig;

@Import(${project_class_name}PersistenceConfig.class)
@ComponentScan({"${base_package}"})
@EntityScan("${base_package}.dao.domain")
@SpringBootApplication
public class ${project_class_name}Application {

    private static final Logger _logger = LoggerFactory.getLogger(${project_class_name}Application.class);

    public static void main(String[] args) {
        SpringApplication.run(${project_class_name}Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

    @Bean
    public CommandLineRunner demo(AppUserRepository repository) {
        return (args) -> {
            repository.save(new AppUserDomain(null, "hello 1"));
            repository.save(new AppUserDomain(null, "hello 2"));

            _logger.info("AppUsers found with findAll():");
            _logger.info("-------------------------------");

            for (AppUserDomain user : repository.findAll()) {
                _logger.info(user.toString());
            }

            _logger.info("");
        };
    }
}