package com.home.andmark.bookkeepingsb.service.impl;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
    public void doAdminStuff() {
        System.out.println("only admin here");
    }
}
