package ${base_package}.service.impl;

import ${base_package}.dao.domain.HelloWorldDomain;
import ${base_package}.model.HelloWorld;
import ${base_package}.service.HelloWorldService;

import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class HelloWorldServiceServiceImpl implements HelloWorldService {
    private final AtomicLong _counter = new AtomicLong();

    public HelloWorld getHelloWorld(String message) {
        HelloWorldDomain domain = new HelloWorldDomain(_counter.incrementAndGet(), message);
        return domain.toModel();
    }
}