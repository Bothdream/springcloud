package com.zte.sangfor.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(principal.toString());
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");

        return principal;
    }
}
