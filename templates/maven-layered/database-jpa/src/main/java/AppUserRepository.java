package ${base_package}.dao.repository;

import org.springframework.data.repository.CrudRepository;
import ${base_package}.dao.domain.AppUserDomain;

public interface AppUserRepository extends CrudRepository<AppUserDomain, Long> {

}