package org.example.jpaHibernate.service;

import org.example.jpaHibernate.entity.Story;
import org.example.jpaHibernate.model.StoriesSearchCriteria;
import org.example.jpaHibernate.repository.StoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoryService {

    private final StoryRepository storyRepository;

    public StoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public Story findById(Long storyId) {
        return this.storyRepository.findById(storyId).orElse(null);
    }

    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    public boolean createStory(Story story) {
        Story newStory = storyRepository.saveAndFlush(story);
        if(newStory != null) {
            return true;
        }
        return false;
    }

    public List<Story> searchStoryByTitle(String text) {
        List<Story> storyList = storyRepository.searchStoryByTitle(text);
        return storyList;
    }


//    public Page<Story> fetchStoryWithPageInterface(String title, String body, int page, int size, List<String> sortList, String sortOrder) {
//
//        // create Pageable object using the page, size and sort details
//        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
//        // fetch the page object by additionally passing pageable with the filters
//        Page<Story> storyList = storyRepository.findByTitleLikeAndBodyLike(title, body, pageable);
//        return storyList;
//    }
//
    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }


//    private PageRequest genPageRequest(int page, int limit, String sort, Sort.Direction direction) {
    private PageRequest genPageRequest(int page, int limit, List<String> sortList, Sort.Direction direction) {
//        return PageRequest.of(page - 1, limit, Sort.by(direction, sort));
        String directionString = String.valueOf(direction);
        return PageRequest.of(page, limit, Sort.by(createSortOrder(sortList, directionString)));
    }

    public Page<Story> findAllStories(StoriesSearchCriteria searchCriteria) {
        PageRequest pageRequest = genPageRequest(
                searchCriteria.getPage(),
                searchCriteria.getSize(),
                searchCriteria.getSortList(),
                searchCriteria.getDirection()
        );
        return storyRepository.findAll(getStorySpecification(searchCriteria), pageRequest);
    }

    private Specification<Story> getStorySpecification(StoriesSearchCriteria filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getId() != null) {
                predicates.add(cb.equal(root.get("id"), filter.getId()));
            }
            if (!StringUtils.isEmpty(filter.getTitle())) {
                predicates.add(cb.like(cb.lower(root.get("title")),
                        "%" + filter.getTitle().toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(filter.getBody())) {
                predicates.add(cb.like(cb.lower(root.get("body")),
                        "%" + filter.getBody().toLowerCase() + "%"));
            }
            if (!StringUtils.isEmpty(filter.getAuthor())) {
                predicates.add(cb.like(cb.lower(root.get("author")),
                        "%" + filter.getAuthor().toLowerCase() + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

//    private Specification<Story> getStorySpecification(StoriesSearchCriteria filter) {
//        return (root, query, cb) -> {
//            List<Predicate> andPredicates = new ArrayList<>();
//            List<Predicate> orPredicates = new ArrayList<>();
//
//            // can search specific Id or one of (title and body)
//
//            // Add predicate for filtering by id (A)
//            if (filter.getId() != null) {
//                orPredicates.add(cb.equal(root.get("id"), filter.getId()));
//            }
//
//            // Add predicate for filtering by title (B, case-insensitive)
//            if (!StringUtils.isEmpty(filter.getTitle())) {
//                andPredicates.add(cb.like(cb.lower(root.get("title")),
//                        "%" + filter.getTitle().toLowerCase() + "%"));
//            }
//
//            // Add predicate for filtering by body (C, case-insensitive)
//            if (!StringUtils.isEmpty(filter.getBody())) {
//                andPredicates.add(cb.like(cb.lower(root.get("body")),
//                        "%" + filter.getBody().toLowerCase() + "%"));
//            }
//
//            // Combine B and C using AND
//            Predicate andCombined = cb.and(andPredicates.toArray(new Predicate[andPredicates.size()]));
//
//            // Combine A and (B AND C) using OR
//            if (!andPredicates.isEmpty()) {
//                orPredicates.add(andCombined);
//            }
//
//            // Combine all OR predicates
//            return cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
//        };
//    }
}
