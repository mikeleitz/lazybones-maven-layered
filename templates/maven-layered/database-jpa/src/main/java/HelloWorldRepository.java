package ${base_package}.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ${base_package}.dao.domain.HelloWorldDomain;

public interface HelloWorldRepository extends CrudRepository<HelloWorldDomain, Long> {

}