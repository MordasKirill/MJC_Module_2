package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/certificate")
public class CertificateController extends HttpServlet {

    @PostMapping("/certificate")
    public String createCertificate(HttpServletResponse response, HttpServletRequest request){
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        Certificate certificate = new Certificate(name, price, duration);
        return "success";
    }

    @GetMapping("/certificate")
    public String getCertificates(HttpServletResponse response, HttpServletRequest request){
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        Certificate certificate = new Certificate(name, price, duration);
        return "success";
    }
}
