package com.epam.esm.controller;

import com.epam.esm.service.CertificateTagService;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.TagService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * CertificateTagController
 * Spring rest controller
 * receives requests with /certificateTag mapping
 */
@Component
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
    public ResponseEntity<?> getCertificatesByTag(@PathVariable Optional<Integer> id) throws ServiceException {
        return new ResponseEntity<>(certificateService.getCertificatesByTag(id.get()), HttpStatus.OK);
    }

    /**
     * getCertificatesByPartName, RequestMethod.GET
     * receives requests with /list/name/part mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificatesByPartName(@PathVariable String name) throws ServiceException {
        return new ResponseEntity<>(certificateService.getCertificatesByNamePart(name), HttpStatus.OK);
    }

    /**
     * getCertificatesByNameAsc, RequestMethod.GET
     * receives requests with /list/name mapping
     *
     * @return ResponseEntity<List < Certificate>>
     */
    @RequestMapping(value = "/sorting/{sortParam}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCertificatesByNameAsc(@PathVariable String sortParam, @RequestParam(defaultValue = "asc") String direction) throws ServiceException {
        return new ResponseEntity<>(certificateService.getCertificatesSorted(sortParam, direction), HttpStatus.OK);
    }
}
