package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * CertificateController
 * Spring rest controller
 * receives requests with /certificate mapping
 */
@Component
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
    public ResponseEntity<?> createCertificate(@RequestBody @Validated Certificate certificate) throws ServiceException {
        certificateService.createCertificate(certificate);
        return new ResponseEntity<>("Certificate created.", HttpStatus.OK);
    }

    /**
     * getCertificates RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificates() throws ServiceException {
        return new ResponseEntity<>(certificateService.getCertificates(), HttpStatus.OK);
    }

    /**
     * getCertificates RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificate(@PathVariable Optional<Integer> id) throws ServiceException {
        return new ResponseEntity<>(certificateService.getCertificate(id.get()), HttpStatus.OK);
    }

    /**
     * deleteCertificate , RequestMethod.DELETE
     * receives requests with /id mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCertificate(@PathVariable Optional<Integer> id) throws ServiceException {
        certificateService.deleteCertificate(id.get());
        return new ResponseEntity<>(String.format("Certificate with id: %d deleted.", id.get()), HttpStatus.OK);
    }

    /**
     * updateCertificate, RequestMethod.PUT
     * receives requests with /put mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = {"/", "/{id}"}, method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCertificate(@PathVariable Optional<Integer> id, @RequestBody @Validated Certificate certificate) throws ServiceException {
        certificateService.updateCertificate(id.get(), certificate);
        return new ResponseEntity<>(String.format("Certificate with id: %d updated.", id.get()), HttpStatus.OK);
    }
}
