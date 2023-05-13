package com.heshanthenura.userauthentication;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        return "index";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String SignIn(@RequestParam(name = "error", required = false) String error, Model model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() == "anonymousUser"){
            if (error != null) {
                model.addAttribute("errorMessage", "Invalid Username or Password");
            }
            return "signin";
        }else {
         return "redirect:/";
        }
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String SignUpPOST() {
        return "redirect:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String SignUp() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() == "anonymousUser"){
        return "signup";
        }else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String Test() {
        return "Test";
    }

}
