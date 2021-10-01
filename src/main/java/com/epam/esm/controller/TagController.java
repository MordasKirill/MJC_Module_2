package com.epam.esm.controller;

import com.epam.esm.config.SpringConfig;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.impl.TagServiceImpl;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private static final Logger LOG = Logger.getLogger(TagController.class);

    @PostMapping("/new")
    public ResponseEntity<List<Tag>> createTag(HttpServletRequest request) throws IOException, ServletException {
        String name = request.getParameter("name");
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TagServiceImpl tagService = context.getBean(TagServiceImpl.class);
        try {
            tagService.createTag(new Tag(name));
            return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to create tags.", e);
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> getTags() throws IOException, ServletException{
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TagServiceImpl tagService = context.getBean(TagServiceImpl.class);
        try {
            return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all tags.", e);
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/id", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tag>> deleteTag(HttpServletRequest request) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        TagServiceImpl tagService = context.getBean(TagServiceImpl.class);
        try {
            tagService.deleteTag(new Tag(id));
            return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to delete tag.", e);
            return ResponseEntity.notFound().build();
        }
    }
}
