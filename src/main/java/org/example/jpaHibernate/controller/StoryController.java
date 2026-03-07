package org.example.jpaHibernate.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.jpaHibernate.entity.Story;
import org.example.jpaHibernate.model.request.StoriesSearchCriteria;
import org.example.jpaHibernate.model.request.StoryListRequest;
import org.example.jpaHibernate.model.request.StoryRequest;
import org.example.jpaHibernate.model.response.StoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.example.jpaHibernate.service.StoryService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@Controller
@RequestMapping("/jpa/story")
public class StoryController {

    private static final Logger logger = LogManager.getLogger(StoryController.class);

    private final StoryService storyService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }

    @Operation(
            summary = "Get all stories",
            description = "Retrieves a list of all stories from the PostgreSQL database"
    )
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<StoryResponse>> getAllStories(HttpServletRequest request, HttpServletResponse response) {
        return ResponseEntity.ok(storyService.getAllStories());
    }

    @Operation(description = "Get story by id")
    @Transactional(readOnly = true)
    @SneakyThrows
    @GetMapping("/{id}")
    public ResponseEntity<StoryResponse> findStoryById(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        logger.debug("findStoryById id: {}",id);
        StoryResponse storyResponse = storyService.getById(id);
        if (storyResponse == null) {
            throw new Exception("Data not found");
        }
        return ResponseEntity.ok(storyResponse);
    }

    @Operation(description = "Create story list")
    @Transactional
    @SneakyThrows
    @PostMapping
    public ResponseEntity<String> createStoryList(HttpServletRequest request, HttpServletResponse response, @RequestBody StoryListRequest storyListRequest) {
        List<Story> createdStoryList = storyService.createStory(storyListRequest);

        if (ObjectUtils.isEmpty(createdStoryList)) {
            throw new Exception("Failed creating story list");
        }

        String ids = createdStoryList.stream()
                .map(story -> String.valueOf(story.getId()))
                .collect(Collectors.joining(", "));
        return ResponseEntity.ok("Created new story list with ids: " + ids);
    }

    @Operation(description = "Get story by id/title/author")
    @Transactional(readOnly = true)
    @SneakyThrows
    @GetMapping("/search")
    public ResponseEntity<List<StoryResponse>> searchStory(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestParam(required = false, value = "id") Long searchID,
                                                   @RequestParam(required = false, value = "title") String title,
                                                   @RequestParam(required = false, value = "author") String author
    ) {

        List<Story> storyList = null;

        if (searchID != null) {
            Story story = storyService.findById(searchID);
            if (story != null) {
                storyList = List.of(story);
            }
        } else if (StringUtils.hasText(title)) {
            storyList = storyService.searchStoryByTitle(title);
        } else if (StringUtils.hasText(author)) {
            storyList = storyService.searchStoryByAuthor(author);
        }

        if (storyList != null) {
            List<StoryResponse> storyResponseList = storyList.stream()
                    .map(StoryResponse::new)
                    .toList();
            return ResponseEntity.ok(storyResponseList);
        }

        throw new Exception("Data not found");
    }

    @Operation(
            summary = "Update story by id",
            description = "Updates an existing story record in the database"
    )
    @Transactional
    @SneakyThrows
    @PutMapping("/{id}")
    public ResponseEntity<StoryResponse> updateStoryById(HttpServletRequest request, HttpServletResponse response,
                                                         @PathVariable("id") Long id,
                                                         @RequestBody StoryRequest storyRequest
    ) {
        logger.debug("updateStoryById id: {}",id);
        StoryResponse storyResponse = storyService.updateStoryById(id, storyRequest);
        if (storyResponse == null) {
            throw new Exception("Data not found");
        }
        return ResponseEntity.ok(storyResponse);
    }

    @Operation(
            summary = "Update story by id (partial update)",
            description = "Updates an existing story record in the database"
    )
    @Transactional
    @SneakyThrows
    @PatchMapping("/{id}")
    public ResponseEntity<StoryResponse> patchStory(@PathVariable("id") Long id, @RequestBody StoryRequest storyRequest) {
        return ResponseEntity.ok(storyService.updateStoryById(id, storyRequest, true));
    }

    @Operation(description = "Get story list with pagination and sorting")
    @Transactional(readOnly = true)
    @GetMapping("/get")
    public ResponseEntity<Page<Story>> fetchStoryWithPageInterface(HttpServletRequest request, HttpServletResponse response,
                                                                   @RequestParam(required = false, value = "id") Long searchID,
                                                                   @RequestParam(required = false, value = "title") String title,
                                                                   @RequestParam(required = false, value = "body") String body,
                                                                   @RequestParam(required = false, value = "author") String author,
                                                                   @RequestParam(required = false, defaultValue = "0", value = "page") Integer page,
                                                                   @RequestParam(required = false, defaultValue = "10", value = "size") Integer size,
                                                                   @RequestParam(required = false, defaultValue = "id", value = "sortList") List<String> sortList,
                                                                   @RequestParam(required = false, defaultValue = "ASC", value = "order") Sort.Direction direction) throws Exception{

        try {
            StoriesSearchCriteria searchCriteria = StoriesSearchCriteria.builder()
                    .id(searchID)
                    .title(title)
                    .body(body)
                    .author(author)
                    .page(page)
                    .size(size)
                    .sortList(sortList)
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
