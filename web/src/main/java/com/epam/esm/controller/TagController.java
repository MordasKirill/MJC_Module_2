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
import org.springframework.web.bind.annotation.*;

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
    private final TagServiceImpl tagService;

    public TagController(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    /**
     * createTag, RequestMethod.POST
     * receives requests with /new mapping
     *
     * @return ResponseEntity<List < Tag>>
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTag(@RequestBody Tag tag) {
        try {
            tagService.createTag(new Tag(tag.getName()));
            return new ResponseEntity<>("New tag created.", HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to create tag.", e);
            return new ResponseEntity<>("Fail to createTag", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * getTags, RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Tag>>
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTags() {
        try {
            return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to get all tags.", e);
            return new ResponseEntity<>("Fail to getTags", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * deleteTag, RequestMethod.DELETE
     * receives requests with /id mapping
     *
     * @return ResponseEntity<List < Tag>>
     */
    @RequestMapping(value = "/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTag(@RequestParam int id) {
        try {
            ResponseEntity<?> responseEntity;
            if (tagService.isTagExist(id)) {
                tagService.deleteTag(new Tag(id));
                responseEntity = new ResponseEntity<>("Tag with id: " + id + " deleted.", HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>("Cant find tag with id:" + id, HttpStatus.BAD_REQUEST);
            }
            return responseEntity;
        } catch (ServiceException e) {
            LOG.log(Level.ERROR, "FAIL DB: Fail to delete tag.", e);
            return new ResponseEntity<>("Fail to deleteTag", HttpStatus.NOT_FOUND);
        }
    }
}
