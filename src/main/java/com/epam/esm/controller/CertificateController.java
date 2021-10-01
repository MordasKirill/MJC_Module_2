package com.epam.esm.controller;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.entity.Certificate;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.impl.CertificateServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    private static final Logger LOG = Logger.getLogger(CertificateController.class);

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> createCertificate(HttpServletRequest request){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CertificateServiceImpl certificateService = context.getBean(CertificateServiceImpl.class);
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String description = request.getParameter("description");
        String currentDate = certificateService.getCurrentDate();
        try {
            certificateService.createCertificates(new Certificate(name, price, duration, currentDate, "test", description));
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e){
            LOG.log(Level.ERROR, "FAIL DB: Fail to create certificate.", e);
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> getCertificates() throws IOException, ServletException{
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CertificateServiceImpl certificateService = context.getBean(CertificateServiceImpl.class);
        try {
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/id", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> deleteCertificate(HttpServletRequest request){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CertificateServiceImpl certificateService = context.getBean(CertificateServiceImpl.class);
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            certificateService.deleteCertificates(new Certificate(id));
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }
}
