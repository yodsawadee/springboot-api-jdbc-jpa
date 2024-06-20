package org.example.jpaHibernate.repository;

import org.example.jpaHibernate.entity.Story;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story,Long>, JpaSpecificationExecutor<Story> {

    @Query("select s from Story s where s.title like %:text%")
    List<Story> searchStoryByTitle(String text);

    @Query("select s from Story s where s.title like %:title% or s.body like %:body%")
    Page<Story> findByTitleLikeAndBodyLike(String title, String body, Pageable pageable);
}