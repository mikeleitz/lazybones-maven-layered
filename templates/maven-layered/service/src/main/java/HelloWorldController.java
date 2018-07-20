package ${base_package}.controller;

import ${base_package}.domain.HelloWorld;
import ${base_package}.service.HelloWorldService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class HelloWorldController {
    @Autowired private HelloWorldService _helloWorldService;

    @RequestMapping("/helloWorld")
    public HelloWorld greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return _helloWorldService.getHelloWorld(name);
    }
}