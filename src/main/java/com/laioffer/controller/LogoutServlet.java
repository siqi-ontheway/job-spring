package com.laioffer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.entity.ResultResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@RestController
//@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @RequestMapping("/logout")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

