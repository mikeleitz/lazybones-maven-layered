package $ import org.springframework.boot.autoconfigure.SpringBootApplication;

{base_package}.service;

@SpringBootApplication
public class ${project_class_name}Application {

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

}