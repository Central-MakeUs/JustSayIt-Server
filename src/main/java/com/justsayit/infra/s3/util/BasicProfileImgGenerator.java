package com.justsayit.infra.s3.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Configuration
@PropertySource("classpath:application.yml")
public class BasicProfileImgGenerator {

    private static Set<String> urlSet = new HashSet<>();

    static {
        urlSet.add("basic-profile-img.blue");
        urlSet.add("basic-profile-img.pink");
        urlSet.add("basic-profile-img.purple");
        urlSet.add("basic-profile-img.yellow");
    }

    public static String getRandom() {
        if (urlSet == null || urlSet.isEmpty()) {
            throw new IllegalArgumentException("The Set cannot be empty.");
        }
        int randomIndex = new Random().nextInt(urlSet.size());
        int i = 0;
        for (String url : urlSet) {
            if (i == randomIndex) {
                return url;
            }
            i++;
        }
        throw new IllegalStateException("Something went wrong while picking a random element.");
    }
}
