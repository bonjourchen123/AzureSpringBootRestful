package com.bonjour.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.azure.spring.autoconfigure.aad.UserPrincipal;

@RestController
public class AuthenticationController {

	@GetMapping("/test")
    @ResponseBody
    public String test() {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		
        return "Hello Azure Active Directory: " + userPrincipal.getName() + ", Authorities: " + authorities;
    }
	
	@GetMapping("/authorized")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String onlyAuthorizedUsers() {
        return "Hello USER";
    }
}
