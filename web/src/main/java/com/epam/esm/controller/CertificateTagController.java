package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.impl.CertificateTagServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * CertificateTagController
 * Spring rest controller
 * receives requests with /certificateTag mapping
 */
@ComponentScan("com.epam.esm.config")
@RestController
@RequestMapping("/certificateTag")
public class CertificateTagController {

    private static final Logger LOG = Logger.getLogger(CertificateController.class);
    private final CertificateTagServiceImpl certificateService;

    public CertificateTagController(CertificateTagServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    /**
     * getCertificatesByTag, RequestMethod.GET
     * receives requests with /list/tag mapping
     *
     * @param request request from client
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/list/tag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificatesByTag(HttpServletRequest request) {
        int tagId = Integer.parseInt(request.getParameter("tagId"));
        try {
            return new ResponseEntity<>(certificateService.getCertificatesByTag(new Tag(tagId)), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to get certificates by tag getCertificatesByTag", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * getCertificatesByPartName, RequestMethod.GET
     * receives requests with /list/name/part mapping
     *
     * @param request request from client
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/list/name/part", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificatesByPartName(HttpServletRequest request) {
        String name = request.getParameter("name");
        try {
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
    @RequestMapping(value = "/list/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificatesByNameAsc() {
        try {
            return new ResponseEntity<>(certificateService.getCertificatesSortedByPrice(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return new ResponseEntity<>("Fail to get certificates by name asc getCertificatesByNameAsc", HttpStatus.NOT_FOUND);
        }
    }
}
