package $ import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

{base_package}.dao.repository;
        {base_package}.dao.domain.AppUserDomain;

@Repository
public interface AppUserRepository extends CrudRepository<AppUserDomain, Long> {

}