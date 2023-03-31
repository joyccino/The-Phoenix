package com.phoenix.qpproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class AuthenticationController {
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/pages/authentication/card/login";
    }

}