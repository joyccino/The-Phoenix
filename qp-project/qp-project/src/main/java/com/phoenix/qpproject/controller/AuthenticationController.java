package com.phoenix.qpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return "/pages/authentication/card/login";
    }

    @RequestMapping(value = "authentication/register", method = RequestMethod.GET)
    public String registerDefault() {
        return "/pages/authentication/card/register";
    }

    @RequestMapping(value = "authentication/forgotPassword", method = RequestMethod.GET)
    public String forgotPassword() {
        return "/pages/authentication/card/forgot-password";
    }




}