package com.heshanthenura.userauthentication;


import com.heshanthenura.userauthentication.Database.SQLServices;
import com.heshanthenura.userauthentication.Database.User;
import com.heshanthenura.userauthentication.FormModels.SignUpModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    SQLServices sqlServices;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        return "index";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String SignIn(@RequestParam(name = "error", required = false) String error, Model model, Principal principal) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() == "anonymousUser") {
            if (error != null) {
                model.addAttribute("errorMessage", "Invalid Username or Password");
            }
            return "signin";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public String SignInPOST() {
        return "redirect:/";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String SignUp(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() == "anonymousUser") {
            model.addAttribute("signUpModel", new SignUpModel());
            return "signup";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String SignUpPOST(@Valid @ModelAttribute("signUpModel") SignUpModel signUpModel, BindingResult bindingResult) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        System.out.println("error");
            return "signup";
        }
        System.out.println(signUpModel.getFullName());
        System.out.println(signUpModel.getUsername());
        System.out.println(signUpModel.getPassword());
        System.out.println(signUpModel.getConfPassword());
        System.out.println(signUpModel.getEmail());
        jdbcTemplate.execute("INSERT INTO users ('full_name','username','password','email','roles') VALUES ('"+signUpModel.getFullName()+"','"+signUpModel.getUsername()+"','"+passwordEncoder.encode(signUpModel.getPassword())+"','"+signUpModel.getEmail()+"','USER')");
        System.out.println("Added Data");
        return "redirect:/";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String Profile(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName() == "anonymousUser") {
            return "redirect:/";
        }else{
            User user = sqlServices.findUserByUsername(auth.getName());
            System.out.println(user.getUsername());
            model.addAttribute("fullName",user.getFull_name());
            model.addAttribute("username",user.getUsername());
            model.addAttribute("email",user.getEmail());
            return "profile";
        }



    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String Test() {
        return "Test";
    }

}
