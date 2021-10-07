package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.impl.CertificateServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * CertificateController
 * Spring rest controller
 * receives requests with /certificate mapping
 */
@ComponentScan("com.epam.esm.config")
@RestController

public class CertificateController {

    private static final Logger LOG = Logger.getLogger(CertificateController.class);
    private static final String PATTERN_HH_MM = "yyyy-MM-dd'T'HH:mm'Z'";
    private static final String PATTERN_HH_MM_SS = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private CertificateServiceImpl certificateService;

    public CertificateController(CertificateServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * createCertificate RequestMethod.POST
     * receives requests with /new mapping
     *
     * @param request request from client
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/certificate/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> createCertificate(HttpServletRequest request) {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        String description = request.getParameter("description");
        String currentDate = certificateService.getCurrentDate(PATTERN_HH_MM);
        String lastUpdateDate = certificateService.getCurrentDate(PATTERN_HH_MM_SS);
        try {
            certificateService.createCertificates(new Certificate(name, price, duration, currentDate, lastUpdateDate, description));
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to create certificate.", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * getCertificates RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/certificate/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> getCertificates() throws IOException, ServletException {
        try {
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * deleteCertificate , RequestMethod.DELETE
     * receives requests with /id mapping
     *
     * @param request request from client
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/certificate/id", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> deleteCertificate(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            certificateService.deleteCertificates(new Certificate(id));
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * updateCertificate, RequestMethod.PUT
     * receives requests with /put mapping
     *
     * @param request request from client
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/certificate/put", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> updateCertificate(HttpServletRequest request) {
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
