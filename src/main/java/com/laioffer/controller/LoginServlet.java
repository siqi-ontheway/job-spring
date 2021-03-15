package com.laioffer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.db.MySQLConnection;
import com.laioffer.entity.LoginRequestBody;
import com.laioffer.entity.LoginResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@RestController
//@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Autowired
    private MySQLConnection connection;
    @RequestMapping("/login")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        LoginResponseBody loginResponseBody;
        LoginRequestBody body = mapper.readValue(request.getReader(), LoginRequestBody.class);
        //MySQLConnection connection = new MySQLConnection();

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
}

