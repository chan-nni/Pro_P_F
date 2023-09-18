package spring.Pro_P_F.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import spring.Pro_P_F.Controller.Form.CommunityForm;
import spring.Pro_P_F.Controller.Form.MemberForm;
import spring.Pro_P_F.Controller.Form.PostForm;
import spring.Pro_P_F.domain.Community;
import spring.Pro_P_F.domain.Member;
import spring.Pro_P_F.domain.Posting;
import spring.Pro_P_F.service.MemberService;
import spring.Pro_P_F.service.PostingService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private PostingService postingService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "home/join";
    }

    @PostMapping("/join")
    public String memberForm(MemberForm form) {
        Member member = new Member();

        member.setM_id(form.getM_id());
        member.setM_pwd(form.getM_pwd());
        member.setM_name(form.getM_name());
        member.setM_phone(form.getM_phone());
        member.setM_email(form.getM_email());
        member.setM_git(form.getM_git());

        memberService.join(member);
        return "home/login";
    }

    @GetMapping("/")
    public String login(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "home/login";
    }

//    @PostMapping("/login")
//    public String loginId(MemberForm form, HttpSession session) {
//        String mId = form.getM_id();
//        String mPwd = form.getM_pwd();
//
//        if (memberService.login(mId, mPwd)) {
//            throw new IllegalArgumentException("존재하지 않는 id입니다");
//        } else {
//            String memId = form.getM_id();
//            session.setAttribute("m_id", memId);
//
//            return "redirect:/main";
//        }
//    }

    @PostMapping("/login")
    public String loginId(MemberForm form, HttpSession session) {
        String mId = form.getM_id();
        String mPwd = form.getM_pwd();

        // 입력된 아이디로 회원을 조회합니다.
        Member member = memberService.findOne(mId);

        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 id입니다");
        } else {
            // 회원이 존재하면 비밀번호를 확인합니다.
            if (member.getM_pwd().equals(mPwd)) {
                // 아이디와 비밀번호가 일치하는 경우 세션에 아이디를 저장하고 로그인 성공 처리를 합니다.
                session.setAttribute("m_id", mId);
                return "redirect:/main";
            } else {
                // 비밀번호가 일치하지 않는 경우 예외를 던지거나 에러 메시지를 처리할 수 있습니다.
                throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
            }
        }
    }


    @GetMapping("/main")
    public String mainHome(HttpSession session, Model model) {
        String mId = (String) session.getAttribute("m_id");
        model.addAttribute("m_id", mId);
        System.out.println("이걸 지났다네~~" + mId);

        List<Posting> new_posting = postingService.new_posting();
        model.addAttribute("new_posting", new_posting);

        return "home/index";
    }

    @GetMapping("/comch")
    public String comch(Model model) {
        return "company/company_channel";
    }

    @GetMapping("/home_main")
    public String h_main(Model model) {
        return "home/index";
    }

    @GetMapping("/c_join")
    public String c_join(Model model) {
        return "home/company_join";
    }

    @GetMapping("/c_login")
    public String c_login(Model model) {
        return "home/company_login";
    }
}

