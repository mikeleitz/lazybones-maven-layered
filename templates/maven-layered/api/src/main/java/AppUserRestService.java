package ${base_package}.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ${base_package}.model.AppUser;

@FeignClient(url = "http://localhost:8080", name="appUserRestClient")
public interface AppUserRestService {
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    Resources<AppUser> getAppUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    Resource<AppUser> findById(@RequestParam("id") String id);
}