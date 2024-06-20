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
    public Integer page;
    public Integer size;
    public String sort;
    public Sort.Direction direction;
//    public List<String> sortList;
}
