package com.laioffer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.db.MySQLConnection;
import com.laioffer.entity.RegisterRequestBody;
import com.laioffer.entity.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@RestController
//@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    @Autowired
    private MySQLConnection connection;
    @RequestMapping("/register")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        RegisterRequestBody body = mapper.readValue(request.getReader(), RegisterRequestBody.class);
        //MySQLConnection connection = new MySQLConnection();
        ResultResponse resultResponse;
        if (connection.addUser(body.userId, body.password, body.firstName, body.lastName)) {
            resultResponse = new ResultResponse("OK");
        } else {
            resultResponse = new ResultResponse("User Already Exists");
        }
        connection.close();
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), resultResponse);
    }
}