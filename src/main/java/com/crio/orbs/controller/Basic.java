package com.crio.orbs.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Basic {

    @GetMapping("/public")
    public String home() {
        return "Welcome to Online Room Booking System";
    }

    @GetMapping("/admin")
    // @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "Admin, Welcome to Online Room Booking System";
    }

    @GetMapping("/manager")
    // @PreAuthorize("hasRole('HOTEL_MANAGER')")
    public String manager() {
        return "Manager, Welcome to Online Room Booking System";
    }

    @GetMapping("/customer")
    // @PreAuthorize("hasRole('CUSTOMER')")
    public String customer() {
        return "Customer, Welcome to Online Room Booking System";
    }
    
    
}
