package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService memberService;

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit() {
        return "/pages/user/settings.html";
    }
}
