package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.impl.TagServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * TagController
 * Spring rest controller
 * receives requests with /tag mapping
 */
@ComponentScan("com.epam.esm.config")
@RestController
@RequestMapping("/tag")
public class TagController {

    private static final Logger LOG = Logger.getLogger(TagController.class);
    private TagServiceImpl tagService;

    public TagController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    /**
     * createTag, RequestMethod.POST
     * receives requests with /new mapping
     *
     * @param request request from client
     * @return ResponseEntity<List < Tag>>
     */
    @PostMapping("/new")
    public ResponseEntity<List<Tag>> createTag(HttpServletRequest request) throws IOException, ServletException {
        String name = request.getParameter("name");
        try {
            tagService.createTag(new Tag(name));
            return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to create tag.", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * getTags, RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Tag>>
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> getTags() throws IOException, ServletException {
        try {
            return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all tags.", e);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * deleteTag, RequestMethod.DELETE
     * receives requests with /id mapping
     *
     * @param request request from client
     * @return ResponseEntity<List < Tag>>
     */
    @RequestMapping(value = "/id", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> deleteTag(HttpServletRequest request) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            tagService.deleteTag(new Tag(id));
            return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to delete tag.", e);
            return ResponseEntity.notFound().build();
        }
    }
}
