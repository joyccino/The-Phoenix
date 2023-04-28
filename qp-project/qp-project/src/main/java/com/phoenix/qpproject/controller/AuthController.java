package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.dto.MailDTO;
import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.service.EmailService;
import com.phoenix.qpproject.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService memberService;

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
    public String addMember(MembersDTO member) {
        String rawPassword = member.getMemberPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setMemberPw(encPassword);
        member.setMemberMemberTypeId(1);
        memberService.addMember(member);
        return "redirect:/";
    }

    @PostMapping("/memberLogin")
    public String memberLogin(MembersDTO member, Model model, HttpServletRequest request, RedirectAttributes rttr) {
        log.info("로그인폼에서 입력받은 데이터: {}", member.getMemberId());

        HttpSession session = request.getSession();

        MembersDTO membersInfo = memberService.login(member.getMemberId(), member.getMemberPw());
        // id 비교
        //int memberCount = memberService.checkMemberById(member.getMemberId());

        if (membersInfo != null) {
            log.info("멤버 not null");
            System.out.println("membersInfo: " + membersInfo.getMemberFirstname());
            // pw 비교

            // admin 여부 확인

            // recent visit 기록
            session.setAttribute("qpUser", membersInfo);

            int mId = membersInfo.getId();

            memberService.addVisitHistory(mId);

            System.out.println(mId+" 로그인 history insterted");

            return "redirect:/quiz/quizList";
        }
        else {
            String msg = "아이디 또는 비밀번호를 확인해주세요.";
            model.addAttribute("msgLoginFailed",msg);
            return "redirect:/authentication/login?error=true";
        }

    }

    private final EmailService emailService;
    public AuthController(EmailService emailService) {
        this.emailService = emailService;
    }
    //@PostMapping("/passReset")
    @GetMapping("/passReset")
    public String sendPasswordResetEmail(MailDTO mailDTO) {
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")).toCharArray();
        //String newPass = RandomStringUtils.random( randomStrLength, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
        String newPass = RandomStringUtils.random( 20, possibleCharacters );
        //String newPass ="q9r890qadfaiklsjdfilashj";
        mailDTO.setAddress("sbins402@naver.com");
        mailDTO.setTitle("[큐피] 비밀번호 재설정");
        mailDTO.setContent("재생성된 비밀번호는 "+newPass+" 입니다.");
        emailService.sendPassResetEmail(mailDTO);
        System.out.println("passReset 메일 전송 완료");
        //newPass 암호화 과정 추가 예정
        //DB member table 의 password 컬럼 newPass 로 update 추가 예정
        System.out.println("DB 업데이트 완료");
        // 팝업 메세지 전달 (비밀번호가 리셋되었습니다. 이메일함 (스팸) 함을 확인해주세요.
        return "redirect:/authentication/forgotPassword";

    }
}