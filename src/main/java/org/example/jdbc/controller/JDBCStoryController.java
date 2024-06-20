package org.example.jdbc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.jdbc.model.Story;
import org.example.jdbc.service.JDBCStoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/jdbc/story")
public class JDBCStoryController {

    private static final Logger logger = LogManager.getLogger(JDBCStoryController.class);

    private final JDBCStoryService storyService;

    public JDBCStoryController(JDBCStoryService storyService) {
        this.storyService = storyService;
    }


    @ResponseBody
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public ResponseEntity<Story> findStoryById(@PathVariable("id")Long id, HttpServletRequest request, HttpServletResponse response) throws Exception{

        try {
            logger.debug("findStoryById id: {}",id);
            Story story = storyService.findById(id);

            if(story != null) {
                return ResponseEntity.ok(story);
            }

            throw new Exception("Data not found");

        } catch (Exception e){

            e.printStackTrace();
            throw e;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseEntity<List<Story>> getAllStories(HttpServletRequest request, HttpServletResponse response) throws Exception{

        try {
            List<Story> storyList = storyService.getAllStories();

            if(storyList != null) {
                return ResponseEntity.ok(storyList);
            }

            throw new Exception("Data not found");

        } catch (Exception e){

            e.printStackTrace();
            throw e;
        }

    }

    @ResponseBody
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseEntity<String> createStory(HttpServletRequest request, HttpServletResponse response, @RequestBody Story story) throws Exception{

        try {
            boolean created = storyService.createStory(story);

            if(created) {
                return ResponseEntity.ok("Created new story");
            }

            throw new Exception("Failed creating story");

        } catch (Exception e){

            e.printStackTrace();
            throw e;
        }

    }
}
