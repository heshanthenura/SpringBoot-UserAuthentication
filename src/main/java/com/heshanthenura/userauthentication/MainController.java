package com.heshanthenura.userauthentication;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String Index(){
        return "index";
    }

    @RequestMapping(value = "/signin",method = RequestMethod.GET)
    public String SignIn(){
        return "signin";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String SignUp(){
        return "signup";
    }

}
