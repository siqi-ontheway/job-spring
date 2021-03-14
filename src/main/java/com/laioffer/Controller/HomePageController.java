package com.laioffer.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.db.MySQLConnection;
import com.laioffer.entity.LoginRequestBody;
import com.laioffer.entity.LoginResponseBody;
import com.laioffer.entity.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class HomePageController {
    @Autowired
    private MySQLConnection connection;
    @ResponseBody
    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        LoginResponseBody loginResponseBody;
        LoginRequestBody body = mapper.readValue(request.getReader(), LoginRequestBody.class);
        HttpSession session = request.getSession(false);
        if (session != null) {
            String userId = (String) session.getAttribute("user_id");
            loginResponseBody = new LoginResponseBody("OK", userId, connection.getFullname(userId));
            connection.close();
            response.setContentType("application/json");
            mapper.writeValue(response.getWriter(), loginResponseBody);
            return;
        }

        if (connection.verifyLogin(body.userId, body.password)) {
            session = request.getSession();
            session.setMaxInactiveInterval(300);
            session.setAttribute("user_id", body.userId);

            loginResponseBody = new LoginResponseBody("OK", body.userId, connection.getFullname(body.userId));
        } else {
            loginResponseBody = new LoginResponseBody("Login failed, user id and passcode do not exist.", null, null);
            response.setStatus(401);
        }
        connection.close();
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), loginResponseBody);
    }

    @RequestMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        ResultResponse resultResponse = new ResultResponse("OK");
        mapper.writeValue(response.getWriter(), resultResponse);
    }

}

