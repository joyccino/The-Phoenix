package com.phoenix.qpproject.controller;

import com.phoenix.qpproject.dto.MailDTO;
import com.phoenix.qpproject.dto.MembersDTO;
import com.phoenix.qpproject.dto.UnisDTO;
import com.phoenix.qpproject.service.EmailService;
import com.phoenix.qpproject.service.MemberService;
import com.phoenix.qpproject.service.UnisService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Member;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService memberService;

    @Autowired
    public UnisService unisService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login( HttpServletRequest request) {
        HttpSession session = request.getSession();

        Object qpUser = session.getAttribute("qpUser");

        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in");
            return "/pages/authentication/card/login";
        }
        else {
            return "redirect:/quiz/quizList";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register( HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object qpUser = session.getAttribute("qpUser");

        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in. get register page %%");
            return "/pages/authentication/card/register";
        }
        else {
            return "redirect:/quiz/quizList";
        }
    }

    @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
    public String forgotPassword( HttpServletRequest request) {
        HttpSession session = request.getSession();

        Object qpUser = session.getAttribute("qpUser");

        if(ObjectUtils.isEmpty(qpUser)) {
            System.out.println("not logged in. get password page %%");
            return "/pages/authentication/card/forgot-password";
        }
        else {
            return "redirect:/quiz/quizList";
        }
    }

    @PostMapping("/addMember")
    public String addMember(MembersDTO member) {
        String rawPassword = member.getMemberPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setMemberPw(encPassword);
        member.setMemberMemberTypeId(1);
        String uuid = UUID.randomUUID().toString();
        member.setMemberUUId(uuid);
        memberService.addMember(member);

        // 인증 이메일 전송하기
        MailDTO mailDTO = new MailDTO();
        mailDTO.setTitle("[큐피] 이메일 인증");
        mailDTO.setContent("다음의 URL 에서 이메일 인증을 완료해주세요! "+"http://localhost:8080/auth/user/verify/"+uuid);
        mailDTO.setAddress(member.getMemberEmail());
        emailService.sendPassResetEmail(mailDTO);
        System.out.println("register 메일 전송 완료");

        return "redirect:/auth/login";
    }

    @PostMapping("/idCheck")
    @ResponseBody
    public int idCheck(String memberId) {
        System.out.println("넘겨받은 아이디: "+memberId);
        int isIdDupl = memberService.checkMemberById(memberId);
        System.out.println("idCheck 실행중"+isIdDupl);
        return isIdDupl;
    }

    @GetMapping("/user/verify/{memberUUId}")
    public void memberVerify(@PathVariable("memberUUId") String memberUUId){
        MembersDTO member = memberService.checkMemberByUUId(memberUUId);
        System.out.println("memberUUID: "+ memberUUId);
        memberService.memberVerify(memberUUId);
        System.out.println("이메일 인증 성공");

        // 대학생 여부 체크 후 institution id 에 반영하는 로직 추가 예정.
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout( HttpServletRequest request) {
        HttpSession session = request.getSession();

        Object qpUser = session.getAttribute("qpUser");

        if(!ObjectUtils.isEmpty(qpUser)) {
            // 세션값 삭제
            session.removeAttribute("user_id");
            // 세션 전체 제거, 무효화
            session.invalidate();
            return "/pages/authentication/card/login";
        }
        return "redirect:/auth/login";
    }

    @PostMapping("/emailCheck")
    @ResponseBody
    public int emailCheck(@RequestParam("email") String email) {
        int memberCnt = memberService.checkMemberByEmail(email);
        return memberCnt;
    }
    @PostMapping("/uniCheck")
    @ResponseBody
    public String getUniByDomain(@RequestParam("uniDomain") String domain){
        UnisDTO uni = unisService.getUniByDomain(domain);
        String uniName = "";

        if(uniName == null || uniName.length() == 0) {
            uniName = "empty";
        }
        else {
            uniName = uni.getUnisName();
            System.out.println("대학교이름: "+uniName);

        }
        return uniName;
    }

    @PostMapping("/memberLogin")
    public String memberLogin(MembersDTO member, Model model, HttpServletRequest request, RedirectAttributes rttr) {
        log.info("로그인폼에서 입력받은 데이터: {}", member.getMemberId());

        String rawPassword = member.getMemberPw();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setMemberPw(encPassword);

        MembersDTO membersInfo = memberService.login(member.getMemberId(), member.getMemberPw());
        // id 비교
        //int memberCount = memberService.checkMemberById(member.getMemberId());

        System.out.println("isMemberIsBlocked: "+member.isMemberIsBlocked());
        System.out.println("isMemberIsRemoved: "+member.isMemberIsRemoved());


        if (membersInfo != null) {
            log.info("멤버 not null");

            // admin 여부 확인

            // recent visit 기록
            //session.setAttribute("qpUser", membersInfo);
            membersInfo.setMemberPw("masked");

            HttpSession session = request.getSession();

            session.setAttribute("qpUser", membersInfo);

            session.setMaxInactiveInterval(-1);

            int mId = membersInfo.getId();

            memberService.addVisitHistory(mId);

            System.out.println(mId+" 로그인 history insterted");

            if(membersInfo.getMemberMemberTypeId() == 0) {
                return "redirect:/auth/analytics";
            }
            else {
                return "redirect:/quiz/quizList";
            }

        }
        else {
            if (member.isMemberIsBlocked()) {
                String msg = "차단된 회원입니다.";
                model.addAttribute("msgLoginFailed",msg);
            }
            else {
                String msg = "아이디 또는 비밀번호를 확인해주세요.";
                model.addAttribute("msgLoginFailed",msg);
            }

            return "redirect:/auth/login?error=true";
        }

    }



    @RequestMapping(value = "/memberList", method = RequestMethod.GET)
    public String adminDashboard(HttpServletRequest request, RedirectAttributes rttr,  Model model) {
        // 세션에 멤버 존재 여부
        HttpSession session = request.getSession();

        MembersDTO qpUser = (MembersDTO) session.getAttribute("qpUser");

        System.out.println("qpUser membertype Id:"+qpUser.getMemberMemberTypeId());

        // 어드민인지 여부.

        //System.out.println("qpUser membertype Id:"+qpUser.getMemberMemberTypeId());


        if(qpUser.getMemberMemberTypeId() == 0) {
            // 전체 멤버 호출 및 전달.
            List<MembersDTO> memberList = memberService.getMemberList();
            model.addAttribute("memberList",memberList);
            return "/members";
        }
        else {
            // alert 하나 띄워줌.
            return "/quiz/quizList";
        }
    }

    @RequestMapping(value = "/analytics", method = RequestMethod.GET)
    public String adminAnalytics(HttpServletRequest request, RedirectAttributes rttr,  Model model) {
        // 세션에 멤버 존재 여부
        HttpSession session = request.getSession();

        MembersDTO qpUser = (MembersDTO) session.getAttribute("qpUser");

        System.out.println("qpUser membertype Id:"+qpUser.getMemberMemberTypeId());


        if(qpUser.getMemberMemberTypeId() == 0) {
            // 전체 멤버 호출 및 전달.
            List<MembersDTO> memberList = memberService.getMemberList();
            model.addAttribute("memberList",memberList);
            return "/dashboard/analytics";
        }
        else {
            // alert 하나 띄워줌.
            return "/quiz/quizList";
        }
    }

    private final EmailService emailService;
    public AuthController(EmailService emailService) {
        this.emailService = emailService;
    }
    @PostMapping("/passReset")
//    @GetMapping("/passReset")
    public String sendPasswordResetEmail(MailDTO mailDTO) {
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789")).toCharArray();
        //String newPass = RandomStringUtils.random( randomStrLength, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
        String newPass = RandomStringUtils.random( 20, possibleCharacters );
        //String newPass ="q9r890qadfaiklsjdfilashj";
        String encodeNewPass = bCryptPasswordEncoder.encode(newPass);
//        mailDTO.setAddress("sbins402@naver.com");
        mailDTO.setTitle("[큐피] 비밀번호 재설정");
        mailDTO.setContent("재생성된 비밀번호는 "+newPass+" 입니다.");
        emailService.sendPassResetEmail(mailDTO);
        System.out.println("passReset 메일 전송 완료");
        //newPass 암호화 과정 추가 예정
        //DB member table 의 password 컬럼 newPass 로 update 추가 예정
        System.out.println("DB 업데이트 완료");
        memberService.resetPass(encodeNewPass, mailDTO.getAddress());
        // 팝업 메세지 전달 (비밀번호가 리셋되었습니다. 이메일함 (스팸) 함을 확인해주세요.
        return "redirect:/auth/login";

    }
}