package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authentication")
public class JYJController {
    private static final Logger log = LoggerFactory.getLogger(JYJController.class);

    @Autowired
    public MemberService memberService;

    /**
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/pages/authentication/card/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerDefault() {
        return "/pages/authentication/card/register";
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String forgotPassword() {
        return "/pages/authentication/card/forgot-password";
    }

    @PostMapping("/addMember")
    public String addMember(MemberDTO member) {
        memberService.addMember(member);
        return "redirect:/";
    }
    **/

    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("id") String id) {

        int memberCnt = memberService.checkMemberById(id);

        return memberCnt;
    }

}