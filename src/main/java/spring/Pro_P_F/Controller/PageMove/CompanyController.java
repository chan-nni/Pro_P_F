package spring.Pro_P_F.Controller.PageMove;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyController {
    @GetMapping("/employ")
    public String Employ(Model model) {
        return "company/employ";
    }

    @GetMapping("/employ_add")
    public String Employ_add(Model model) {
        return "company/employ_add";
    }

    @GetMapping("/em_de")
    public String Employ_detail(Model model) {
        return "company/employ_detail";
    }

    @GetMapping("/company_my")
    public String company(Model model) {
        return "company/company-mypage";
    }

    @GetMapping("/company_ch")
    public String company_ch(Model model) {
        return "company/company_channel";
    }

    @GetMapping("/cmain")
    public String company_main (Model model) {
        return "home/company_index";
    }
}
