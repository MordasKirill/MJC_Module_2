package com.epam.esm.controller;

import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CertificateTagController
 * Spring rest controller
 * receives requests with /certificateTag mapping
 */
@ComponentScan("com.epam.esm")
@RestController
@RequestMapping("/certificateTag")
public class CertificateTagController {
    private static final Logger LOG = Logger.getLogger(CertificateController.class);
    private final CertificateTagService certificateService;
    private final TagService tagService;

    @Autowired
    public CertificateTagController(CertificateTagService certificateService, TagService tagService) {
        this.certificateService = certificateService;
        this.tagService = tagService;
    }

    /**
     * getCertificatesByTag, RequestMethod.GET
     * receives requests with /list/tag mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificatesByTag(@PathVariable int id) {
        try {
            ResponseEntity<?> responseEntity;
            if (tagService.isTagExist(id)) {
                responseEntity = new ResponseEntity<>(certificateService.getCertificatesByTag(id), HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>("Cant find tag with id:" + id, HttpStatus.BAD_REQUEST);
            }
            return responseEntity;
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to get certificates by tag getCertificatesByTag", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * getCertificatesByPartName, RequestMethod.GET
     * receives requests with /list/name/part mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificatesByPartName(@PathVariable String name) {
        try {
            System.out.println(name);
            return new ResponseEntity<>(certificateService.getCertificatesByNamePart(name), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to get certificates by name part getCertificatesByPartName", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * getCertificatesByNameAsc, RequestMethod.GET
     * receives requests with /list/name mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/sorting", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificatesByNameAsc() {
        try {
            return new ResponseEntity<>(certificateService.getCertificatesSortedByPrice(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to get certificates by name asc getCertificatesByNameAsc", HttpStatus.NOT_FOUND);
        }
    }
}
