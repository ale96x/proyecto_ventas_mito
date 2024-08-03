package com.mitocode.secutiry;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public boolean checkRole(String method){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(r -> System.out.println(r.getAuthority()));
        if(method.equalsIgnoreCase("ADMIN")){
            return true;
        }
        return false;
    }
}
