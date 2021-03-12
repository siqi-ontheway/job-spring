package com.laioffer.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.laioffer.handler.LoginResponseBody;
import com.laioffer.handler.ResultResponse;
import com.laioffer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;



@Controller
public class HomePageController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("/login")
    public LoginResponseBody login(@RequestBody@JsonProperty("user_id") String userId, @RequestBody@JsonProperty("password") String password, HttpSession session) {
        LoginResponseBody lb;
        if (session != null) {
            session.setAttribute("user_id", userId);
            lb = new LoginResponseBody("OK", userId, userService.getFullname(userId));
        } else {
            lb = new LoginResponseBody("Login failed, user id and passcode do not exist.", null, null);
        }
        return lb;
    }

    @RequestMapping("/logout")
    public ResultResponse logout(HttpSession session) {
        ResultResponse res ;
        if (session != null) {
            session.invalidate();
        }
        res = new ResultResponse("OK");

        return res;
    }

}

