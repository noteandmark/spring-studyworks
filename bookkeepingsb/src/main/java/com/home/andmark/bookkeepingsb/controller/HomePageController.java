package com.home.andmark.bookkeepingsb.controller;

import com.home.andmark.bookkeepingsb.security.PersonDetails;
import com.home.andmark.bookkeepingsb.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

    private final AdminService adminService;

    @Autowired
    public HomePageController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "index";
    }

    @GetMapping("/showUserInfo")
    @ResponseBody
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        return personDetails.getUsername();
    }

    @GetMapping("/admin")
    public String adminPage() {
        adminService.doAdminStuff();
        return "admin";
    }
}
