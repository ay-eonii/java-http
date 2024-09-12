package com.techcourse.controller;

import com.techcourse.db.InMemoryUserRepository;
import com.techcourse.model.User;
import org.apache.catalina.request.HttpRequest;
import org.apache.catalina.response.HttpResponse;
import org.apache.catalina.response.Status;

import java.util.Map;

public class RegisterController extends MappingController {

    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        String body = resourceResolver.resolve(request.getUrl());
        response.setStatus(Status.OK);
        response.setContentType("text/html;charset=utf-8");
        response.setBody(body);
    }

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        Map<String, String> requestBody = request.getBody();
        User user = new User(requestBody.get("account"), requestBody.get("password"), requestBody.get("email"));
        InMemoryUserRepository.save(user);

        String body = resourceResolver.resolve("/index.html");
        response.setStatus(Status.FOUND);
        response.setLocation("/index.html");
        response.setContentType("text/html;charset=utf-8");
        response.setBody(body);
    }
}
