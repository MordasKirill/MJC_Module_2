package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.ServiceException;
import com.epam.esm.service.impl.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * TagController
 * Spring rest controller
 * receives requests with /tag mapping
 */
@Component
@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagServiceImpl tagService;

    @Autowired
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
    public ResponseEntity<?> createTag(@RequestBody @Valid Tag tag) throws ServiceException {
        tagService.createTag(tag.getName());
        return new ResponseEntity<>("New tag created.", HttpStatus.OK);
    }

    /**
     * getTags, RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Tag>>
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTags() throws ServiceException {
        return new ResponseEntity<>(tagService.getTags(), HttpStatus.OK);
    }

    /**
     * getTags, RequestMethod.GET
     * receives requests with /list mapping
     *
     * @return ResponseEntity<List < Tag>>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTag(@PathVariable Optional<Integer> id) throws ServiceException {
        return new ResponseEntity<>(tagService.getTag(id.get()), HttpStatus.OK);
    }

    /**
     * deleteTag, RequestMethod.DELETE
     * receives requests with /id mapping
     *
     * @return ResponseEntity<List < Tag>>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteTag(@PathVariable Optional<Integer> id) throws ServiceException {
        tagService.deleteTag(id.get());
        return new ResponseEntity<>(String.format("Tag with id: %d deleted.", id.get()), HttpStatus.OK);
    }
}
