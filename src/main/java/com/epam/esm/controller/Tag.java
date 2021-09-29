package com.epam.esm.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Tag extends HttpServlet {

    @PostMapping("/tag")
    private void createTag(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
        requestDispatcher.forward(request, response);
    }

    @GetMapping("/tag")
    private void getTag(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
        requestDispatcher.forward(request, response);
    }

    @DeleteMapping("/tag")
    private void deleteTag(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
        requestDispatcher.forward(request, response);
    }

}
