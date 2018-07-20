package ${base_package}.service.impl;

import ${base_package}.domain.HelloWorld;
import ${base_package}.service.HelloWorldService;

import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class HelloWorldServiceServiceImpl implements HelloWorldService {
    private final AtomicLong _counter = new AtomicLong();

    public HelloWorld getHelloWorld(String message) {
        return new HelloWorld(_counter.incrementAndGet(), message);
    }
}