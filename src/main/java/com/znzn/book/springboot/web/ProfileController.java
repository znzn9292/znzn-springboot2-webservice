package com.znzn.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile(){
        List<String> profiles = Arrays.asList(env.getActiveProfiles());
        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProdile = profiles.isEmpty() ? "default" : profiles.get(0);

        System.out.println("Active :: ==> "+profiles);
        System.out.println("filter1 :: ==> "+profiles.stream().filter(realProfiles::contains).toString());
        System.out.println("filter2 :: ==> "+profiles.stream().filter(realProfiles::contains).findAny());
        System.out.println("filter3 :: ==> "+profiles.stream().filter(realProfiles::contains).findAny().orElse(defaultProdile));

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny().orElse(defaultProdile);
    }
}
