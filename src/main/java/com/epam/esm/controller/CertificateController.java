package com.epam.esm.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/certificate")
public class CertificateController extends HttpServlet {

    @PostMapping("/certificate")
    public void createCertificate(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
        requestDispatcher.forward(request, response);
    }

    @GetMapping("/certificate")
    public void getCertificates(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
        requestDispatcher.forward(request, response);
    }

    @PutMapping("/certificate")
    public void deleteCertificates(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
        requestDispatcher.forward(request, response);
    }
}
