package ${base_package}.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${base_package}.dao.domain.AppUserDomain;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserDomain, Long> {

}