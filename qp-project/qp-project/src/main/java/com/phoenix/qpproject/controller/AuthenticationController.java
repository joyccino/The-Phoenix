package com.phoenix.qpproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class AuthenticationController {
    @GetMapping("/authentication/join")
    public void Join() {

    }
    @GetMapping("/authentication/checkJoin")
    public void checkJoin() {

    }

    @GetMapping("/authentication/login")
    public void login() {

    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
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

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return "/app/e-learning/course/create-a-course";
    }




}