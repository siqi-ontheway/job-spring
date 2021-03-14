package com.laioffer.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.db.MySQLConnection;
import com.laioffer.entity.RegisterRequestBody;
import com.laioffer.entity.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class UserController {

    @Autowired
    private MySQLConnection connection;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        RegisterRequestBody body = mapper.readValue(request.getReader(), RegisterRequestBody.class);
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

    @RequestMapping(value = "/delete")
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.setStatus(403);
            mapper.writeValue(response.getWriter(), new ResultResponse("Session Invalid"));
            return;
        }
        //deleteRequestBody body = mapper.readValue(request.getReader(), deleteRequestBody.class);
        String userId = (String) session.getAttribute("user_id");
        response.setContentType("application/json");
        MySQLConnection connection = new MySQLConnection();
        connection.deleteUser(userId);
        connection.close();
        ResultResponse resultResponse = new ResultResponse(userId +" Account Deleted.");
        mapper.writeValue(response.getWriter(), resultResponse);
    }
}

