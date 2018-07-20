package ${base_package}.service.impl;

import ${base_package}.dao.domain.AppUserDomain;
import ${base_package}.model.AppUser;
import ${base_package}.service.AppUserService;

import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AppUserServiceImpl implements AppUserService {
    private final AtomicLong _counter = new AtomicLong();

    public AppUser getHelloWorld(String message) {
        AppUserDomain domain = new AppUserDomain(_counter.incrementAndGet(), message);
        return domain.toModel();
    }
}