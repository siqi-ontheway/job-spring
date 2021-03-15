package com.laioffer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.db.MySQLConnection;
import com.laioffer.entity.Item;
import com.laioffer.entity.ResultResponse;
import com.laioffer.external.GitHubClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@RestController
//@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    @Autowired
    private MySQLConnection connection;
    @RequestMapping("/search")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        //MySQLConnection connection = new MySQLConnection();
        Set<String> favoritedItemIds = connection.getFavoriteItemIds(userId);
        connection.close();

        GitHubClient client = new GitHubClient();
        List<Item> items = client.search(lat,lon,null);

        for (Item item : items) {
            item.setFavorite(favoritedItemIds.contains(item.getId()));
        }



        mapper.writeValue(response.getWriter(), items);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
