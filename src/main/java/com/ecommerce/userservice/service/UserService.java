package com.ecommerce.userservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    public List<Map<String, Object>> getAllUsers() {
        Map<String, Object> user1 = new HashMap<>();
        user1.put("userid", 1);
        user1.put("username", "Akshay");
        Map<String, Object> user2 = new HashMap<>();
        user2.put("userid", 2);
        user2.put("username", "Snehal");
        Map<String, Object> user3 = new HashMap<>();
        user3.put("userid", 3);
        user3.put("username", "Jagtap");
        return Arrays.asList(user1, user2, user3);
    }

    @HystrixCommand(fallbackMethod = "getDefaultUser", commandProperties = {
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public Map<String, Object> getSpecificUser(String username) throws InterruptedException {
        System.out.println("getSpecificUser");

        if ("jagtap".equalsIgnoreCase(username)) {
            //Added to test hystrix
            while (true) {
                int count = 0;
            }
        }

        return this.getAllUsers().stream()
            .filter(u -> username.equalsIgnoreCase(u.get("username").toString()))
            .findAny()
            .orElse(null);
    }

    public Map<String, Object> getDefaultUser(String username) {
        System.out.println("inside getDefaultUser: " + username);
        Map<String, Object> defaultUser = new HashMap<>();
        defaultUser.put("userid", 100);
        defaultUser.put("username", "defaultUser");
        return defaultUser;
    }
}
