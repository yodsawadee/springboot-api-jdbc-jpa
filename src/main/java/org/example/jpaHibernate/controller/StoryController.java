package org.example.jpaHibernate.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.jpaHibernate.entity.Story;
import org.example.jpaHibernate.model.StoriesSearchCriteria;
import org.example.jpaHibernate.model.StorySearchReq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.example.jpaHibernate.service.StoryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/jpa/story")
public class StoryController {

    private static final Logger logger = LogManager.getLogger(StoryController.class);

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
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

    @ResponseBody
    @RequestMapping(value = "/searchByTitle",method = RequestMethod.POST)
    public ResponseEntity<List<Story>> searchStoryByTitle(HttpServletRequest request, HttpServletResponse response, @RequestBody StorySearchReq storySearchReq) throws Exception{

        try {
            List<Story> storyList = storyService.searchStoryByTitle(storySearchReq.getText());

            if(storyList != null) {
                return ResponseEntity.ok(storyList);
            }

            throw new Exception("Data not found");

        } catch (Exception e){

            e.printStackTrace();
            throw e;
        }

    }

//    @ResponseBody
//    @RequestMapping(value = "/get",method = RequestMethod.GET)
//    public ResponseEntity<Page<Story>> fetchStoryWithPageInterface(HttpServletRequest request, HttpServletResponse response,
//                                                                   @RequestParam(defaultValue = "") String title,
//                                                                   @RequestParam(defaultValue = "") String body,
//                                                                   @RequestParam(defaultValue = "0") int page,
//                                                                   @RequestParam(defaultValue = "5") int size,
//                                                                   @RequestParam(defaultValue = "") List<String> sortList,
//                                                                   @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder) throws Exception{
//
//        try {
//            Page<Story> storyList = storyService.fetchStoryWithPageInterface(title, body, page, size, sortList, sortOrder.toString());
//
//            if(storyList != null) {
//                return ResponseEntity.ok(storyList);
//            }
//            throw new Exception("Data not found");
//
//        } catch (Exception e){
//            e.printStackTrace();
//            throw e;
//        }
//
//    }

    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<Page<Story>> fetchStoryWithPageInterface(HttpServletRequest request, HttpServletResponse response,
                                                                   @RequestParam(required = false, value = "id") Long searchID,
                                                                   @RequestParam(required = false, value = "title") String title,
                                                                   @RequestParam(required = false, value = "body") String body,
                                                                   @RequestParam(required = false, defaultValue = "1", value = "page") Integer page,
                                                                   @RequestParam(required = false, defaultValue = "10", value = "size") Integer size,
                                                                   @RequestParam(required = false, defaultValue = "id", value = "sort") String sort,
                                                                   @RequestParam(required = false, defaultValue = "ASC", value = "order") Sort.Direction direction) throws Exception{

        try {
            StoriesSearchCriteria searchCriteria = StoriesSearchCriteria.builder()
                    .id(searchID)
                    .title(title)
                    .body(body)
                    .page(page)
                    .size(size)
                    .sort(sort)
                    .direction(direction)
                    .build();
            Page<Story> storyListPage = storyService.findAllStories(searchCriteria);

            if(storyListPage != null) {
                return ResponseEntity.ok(storyListPage);
            }
            throw new Exception("Data not found");

        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }

    }
}
