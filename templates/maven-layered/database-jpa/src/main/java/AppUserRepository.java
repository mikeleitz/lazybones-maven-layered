package ${base_package}.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ${base_package}.dao.domain.AppUserDomain;

@Repository
public interface AppUserRepository extends CrudRepository<AppUserDomain, Long> {

}