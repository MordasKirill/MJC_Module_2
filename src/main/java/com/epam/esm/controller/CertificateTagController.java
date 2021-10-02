package com.epam.esm.controller;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.service.impl.CertificateTagServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

@RestController
@RequestMapping("/certificateTag")
public class CertificateTagController {

    private static final Logger LOG = Logger.getLogger(CertificateController.class);

    @RequestMapping(value = "/list/tag", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Certificate>> getCertificatesByTag(HttpServletRequest request) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CertificateTagServiceImpl certificateService = context.getBean(CertificateTagServiceImpl.class);
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
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CertificateTagServiceImpl certificateService = context.getBean(CertificateTagServiceImpl.class);
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
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        CertificateTagServiceImpl certificateService = context.getBean(CertificateTagServiceImpl.class);
        try {
            return new ResponseEntity<>(certificateService.getCertificatesByName(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all certificates.", e);
            return ResponseEntity.notFound().build();
        }
    }
}
