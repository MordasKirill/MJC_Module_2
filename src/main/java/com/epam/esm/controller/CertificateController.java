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
import java.util.regex.Pattern;

@RestController
@RequestMapping("/certificate")
public class CertificateController {

    private static final Logger LOG = Logger.getLogger(CertificateController.class);
    private static final String patternHhMm = "yyyy-MM-dd'T'HH:mm'Z'";
    private static final String patternHhMmSs = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> createCertificate(HttpServletRequest request){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CertificateServiceImpl certificateService = context.getBean(CertificateServiceImpl.class);
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String description = request.getParameter("description");
        String currentDate = certificateService.getCurrentDate(patternHhMm);
        String lastUpdateDate = certificateService.getCurrentDate(patternHhMmSs);
        try {
            certificateService.createCertificates(new Certificate(name, price, duration, currentDate, lastUpdateDate, description));
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

    @RequestMapping(value = "/put", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> updateCertificate(HttpServletRequest request){
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CertificateServiceImpl certificateService = context.getBean(CertificateServiceImpl.class);
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            certificateService.updateCertificates(new Certificate(name, price, description, id));
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }
}
