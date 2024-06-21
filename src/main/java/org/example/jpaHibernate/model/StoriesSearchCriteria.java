package org.example.jpaHibernate.model;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Builder
public class StoriesSearchCriteria {
    public Long id;
    public String title;
    public String body;
    public String author;
    public Integer page;
    public Integer size;
//    public String sort;
    public List<String> sortList;
    public Sort.Direction direction;
}
