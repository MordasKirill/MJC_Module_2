package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ServiceException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * CertificateController
 * Spring rest controller
 * receives requests with /certificate mapping
 */
@ComponentScan("com.epam.esm")
@RestController
@RequestMapping("/certificates")
public class CertificateController {
    private static final Logger LOG = Logger.getLogger(CertificateController.class);
    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * createCertificate RequestMethod.POST
     * receives requests with /new mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCertificate(@RequestBody @Validated Certificate certificate) {
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
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificates() {
        try {
            return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to get certificates getCertificates", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * getCertificates RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificate(@PathVariable int id) {
        try {
            ResponseEntity<?> responseEntity;
            if (certificateService.isCertificateExist(id)) {
                responseEntity = new ResponseEntity<>(certificateService.getCertificate(id), HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>("Cant find certificate with id:" + id, HttpStatus.BAD_REQUEST);
            }
            return responseEntity;
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to get certificates getCertificates", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * deleteCertificate , RequestMethod.DELETE
     * receives requests with /id mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCertificate(@PathVariable int id) {
        try {
            ResponseEntity<?> responseEntity;
            if (certificateService.isCertificateExist(id)) {
                certificateService.deleteCertificate(id);
                responseEntity = new ResponseEntity<>("Certificate with id: " + id + " deleted.", HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>("Cant find certificate with id:" + id, HttpStatus.BAD_REQUEST);
            }
            return responseEntity;
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
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCertificate(@PathVariable int id, @RequestBody @Validated Certificate certificate) {
        try {
            ResponseEntity<?> responseEntity;
            if (certificateService.isCertificateExist(id)) {
                certificateService.updateCertificate(new Certificate(certificate.getName(), certificate.getPrice(), certificate.getDescription(), id, certificate.getTagName(), certificate.getDuration()));
                responseEntity = new ResponseEntity<>("Certificate with id: " + id + " updated.", HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>("Cant find certificate with id:" + id, HttpStatus.BAD_REQUEST);
            }
            return responseEntity;
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to update certificate field updateCertificate", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}