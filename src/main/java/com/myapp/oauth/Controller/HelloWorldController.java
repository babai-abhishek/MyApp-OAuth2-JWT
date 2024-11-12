package com.myapp.oauth.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
    public String sendGreetings() {
        return "Hello, World!";
    }


    @GetMapping("/services")
    public List<String> jwtProtected(@AuthenticationPrincipal Jwt jwt) {
        String role = jwt.getClaimAsString("role");
        return getServices("developer".equals(role));
    }

    private List<String> getServices(boolean developer) {
        List<String> services = new ArrayList<>();
        services.add("https://localhost/service/email");
        services.add("https://localhost/service/support");
        if (developer) {
            services.add("https://localhost/service/devportal");
        }
        return services;

    }
}
