package com.laioffer.Controller;

import com.laioffer.entity.users;
import com.laioffer.service.UserService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerCustomer(@ModelAttribute users user) {
        ModelAndView modelAndView = new ModelAndView();
        userService.addUser(user);
        modelAndView.setViewName("index");
        modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password");
        return modelAndView;
    }

    @RequestMapping(value = "/delete")
    public String deleteUser(HttpSession session) {
        String userId = (String)session.getAttribute("user_id");
        userService.deleteUser(userId);
        return "redirect:/login";
    }
}

