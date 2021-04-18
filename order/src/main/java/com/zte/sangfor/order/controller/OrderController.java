package com.zte.sangfor.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping(value = "/r/r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1() {
        return "访问资源1";
    }
}
