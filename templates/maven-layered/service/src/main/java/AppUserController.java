package ${base_package}.controller;

import ${base_package}.model.AppUser;
import ${base_package}.service.AppUserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class AppUserController {
    @Autowired private AppUserService _appUserService;

    @RequestMapping("/appUser")
    public AppUser greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return _appUserService.getHelloWorld(name);
    }
}