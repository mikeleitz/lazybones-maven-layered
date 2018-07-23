package ${base_package}.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ${base_package}.dao.domain.AppUserDomain;

@RepositoryRestResource(collectionResourceRel = "user", path = "users")
public interface AppUserRepository extends JpaRepository<AppUserDomain, Long> {

}