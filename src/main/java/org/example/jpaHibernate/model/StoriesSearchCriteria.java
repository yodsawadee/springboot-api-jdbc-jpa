package org.example.jpaHibernate.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Builder
public class StoriesSearchCriteria {
    private Long id;
    private String title;
    private String body;
    private String author;
    private Integer page;
    private Integer size;
//    public String sort;
    private List<String> sortList;
    private Sort.Direction direction;
}
