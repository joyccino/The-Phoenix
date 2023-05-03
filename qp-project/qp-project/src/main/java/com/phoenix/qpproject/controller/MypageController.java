package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
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
    public String edit( HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("qpUser");

        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in. get register page %%");
            return "/pages/authentication/card/login";
        }
        else {
            return "/pages/user/settings.html";
        }

    }
}
