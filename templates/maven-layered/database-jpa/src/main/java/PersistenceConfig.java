package ${base_package}.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("${base_package}.dao.repository")
@Configuration
public class ${project_class_name}PersistenceConfig {

}