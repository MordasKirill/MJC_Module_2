package com.epam.esm.controller;

import com.epam.esm.entity.Certificate;
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
import java.util.List;

@ComponentScan("com.epam.esm.config")
@RestController
@RequestMapping("/certificateTag")
public class CertificateTagController {

    private static final Logger LOG = Logger.getLogger(CertificateController.class);
    private CertificateTagServiceImpl certificateService;

    public CertificateTagController(CertificateTagServiceImpl certificateService) {
        this.certificateService = certificateService;
    }

    @RequestMapping(value = "/list/tag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> getCertificatesByTag(HttpServletRequest request) {
        int tagId = Integer.parseInt(request.getParameter("tagId"));
        try {
            return new ResponseEntity<>(certificateService.getCertificatesByTag(new Tag(tagId)), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/list/name/part", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> getCertificatesByPartName(HttpServletRequest request) {
        String name = request.getParameter("name");
        try {
            return new ResponseEntity<>(certificateService.getCertificatesByNamePart(name), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/list/name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> getCertificatesByNameAsc() {
        try {
            return new ResponseEntity<>(certificateService.getCertificatesSortedByPrice(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }
}
