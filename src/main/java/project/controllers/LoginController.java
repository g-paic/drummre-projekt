package project.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.forms.CustomOAuth2User;

import java.security.Principal;

@Controller
public class LoginController {

    @GetMapping("/")
    public String loginRouter(Model model, Principal principal) {
        if(principal != null) {
            return "redirect:dashboard";
        } else {
            return "redirect:login";
        }
    }
}
