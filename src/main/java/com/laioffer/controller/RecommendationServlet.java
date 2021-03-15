package com.laioffer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.db.MySQLConnection;
import com.laioffer.entity.Item;
import com.laioffer.entity.ResultResponse;
import com.laioffer.recommendation.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
//@WebServlet(name = "RecommendationServlet", urlPatterns = {"/recommendation"})
public class RecommendationServlet extends HttpServlet {
    @Autowired
    private MySQLConnection connection;
    @RequestMapping("/recommendation")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }

        String userId = request.getParameter("user_id");
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lon = Double.parseDouble(request.getParameter("lon"));

        Recommendation recommendation = new Recommendation();
        List<Item> items = recommendation.recommendItems(userId, lat, lon);
        mapper.writeValue(response.getWriter(), items);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
