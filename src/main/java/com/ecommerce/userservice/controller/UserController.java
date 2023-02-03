package com.ecommerce.userservice.controller;

import com.ecommerce.userservice.dto.BaseResponse;
import com.ecommerce.userservice.exception.UserNotFoundException;
import com.ecommerce.userservice.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getDefault() {
        return "Application user-service is up";
    }

    @GetMapping("/users")
    public ResponseEntity<BaseResponse> getAllUsers() {
        var usersList = userService.getAllUsers();

        BaseResponse baseResponse = new BaseResponse(200, "Users fetched successfully", usersList, "SUCCESS");
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping(value = "/users/{username}")
    public ResponseEntity<BaseResponse> getSpecificUser(@PathVariable String username) throws Exception {
        var userObj = userService.getSpecificUser(username);

        if (Objects.isNull(userObj)) {
            //return ResponseEntity.badRequest().body(new BaseResponse(1401, String.format("User [%s] not found", username), "ERROR"));
            throw new UserNotFoundException(String.format("User [%s] not found", username));
        }
        BaseResponse baseResponse = new BaseResponse(200, "User fetched successfully", userObj, "SUCCESS");
        return ResponseEntity.ok(baseResponse);
    }

    @GetMapping(value = "/users/getSpecificUserByQueryParam")
    public ResponseEntity<BaseResponse> getSpecificUserByQueryParam(@RequestParam(value = "username") String username) throws Exception {
        var userObj = userService.getSpecificUser(username);

        if (Objects.isNull(userObj)) {
            //return ResponseEntity.badRequest().body(new BaseResponse(1401, String.format("User [%s] not found", username), "ERROR"));
            throw new UserNotFoundException(String.format("User [%s] not found", username));
        }
        BaseResponse baseResponse = new BaseResponse(200, "User fetched successfully", userObj, "SUCCESS");
        return ResponseEntity.ok(baseResponse);
    }

    @PostMapping("/users/getByUsername")
    public ResponseEntity<BaseResponse> getSpecificUserPost(@RequestBody Map<String, Object> request,
        @RequestHeader(value = "authToken") String authToken) throws Exception {
        System.out.println("authToken: " + authToken);
        String username = String.valueOf(request.get("username"));
        var userObj = userService.getSpecificUser(username);

        if (Objects.isNull(userObj)) {
            //return ResponseEntity.badRequest().body(new BaseResponse(1401, String.format("User [%s] not found", username), "ERROR"));
            throw new UserNotFoundException(String.format("User [%s] not found", username));
        }
        return ResponseEntity.ok(new BaseResponse(200, "User fetched successfully", userObj, "SUCCESS"));
    }


}
