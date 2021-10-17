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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * CertificateController
 * Spring rest controller
 * receives requests with /certificate mapping
 */
@ComponentScan("com.epam.esm.config")
@RestController
public class CertificateController {

    private static final Logger LOG = Logger.getLogger(CertificateController.class);
    private final CertificateServiceImpl certificateService;

    public CertificateController(CertificateServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * createCertificate RequestMethod.POST
     * receives requests with /new mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/certificate/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCertificate(@RequestBody Certificate certificate) {
        try {
            certificateService.createCertificate(new Certificate(certificate.getName(), certificate.getPrice(), certificate.getDescription(), certificate.getTagName(), certificate.getDuration()));
            return new ResponseEntity<>("Certificate created.", HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to create certificate.", e);
            return new ResponseEntity<>("Fail to create certificate createCertificate", HttpStatus.CONFLICT);
        }
    }

    /**
     * getCertificates RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/certificate/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificates() {
        try {
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to get certificates getCertificates", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * deleteCertificate , RequestMethod.DELETE
     * receives requests with /id mapping
     *
     * @param request request from client
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/certificate/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCertificate(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            certificateService.deleteCertificate(new Certificate(id));
            return new ResponseEntity<>("Certificate with id: " + id + " deleted.", HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to delete certificate field deleteCertificate", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * updateCertificate, RequestMethod.PUT
     * receives requests with /put mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/certificate/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCertificate(@RequestBody Certificate certificate) {
        try {
            certificateService.updateCertificate(new Certificate(certificate.getName(), certificate.getPrice(), certificate.getDescription(), certificate.getId()));
            return new ResponseEntity<>("Certificate updated.", HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to update certificate field updateCertificate", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
