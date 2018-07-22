package $ import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

{base_package}.config;

@EnableJpaRepositories("${base_package}.dao.repository")
@Configuration
public class ${project_class_name}PersistenceConfig {

}