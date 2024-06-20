package org.example.jdbc.repository;

import org.example.jdbc.model.Story;
import org.example.jdbc.model.StoryEntity;
import org.example.jdbc.rowmapper.StoryRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class StoryRepositoryImpl implements StoryRepository {

    private final JdbcTemplate jdbcTemplate;
    private final StoryRowMapper storyRowMapper;

    @PersistenceContext
    private EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

    public StoryRepositoryImpl(JdbcTemplate jdbcTemplate, TransactionTemplate transactionTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.storyRowMapper = new StoryRowMapper();

        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Story findStoryById(Long storyId) {
        try {

            Story story = (Story) jdbcTemplate.queryForObject("SELECT *  FROM stories WHERE ID = ?", storyRowMapper, storyId);

            return story;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Story> getAllStories() {
        try {
            List<Story> storyList =  jdbcTemplate.query("SELECT *  FROM stories", storyRowMapper);

            return storyList;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean createStory(Story story) {
        try {
            jdbcTemplate.update("INSERT INTO stories(title, body) VALUES (?,?)",story.getTitle(),story.getBody());

            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }


    @Override
    public StoryEntity findStoryEntityById(Long storyId) {
        try {
            StoryEntity storyEntity = (StoryEntity) entityManager.createNativeQuery("SELECT *  FROM stories WHERE ID =:storyId", StoryEntity.class)
                    .setParameter("storyId",storyId)
                    .getSingleResult();

            System.out.println("fetched data from db");
            return storyEntity;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<StoryEntity> getAllStoryEntities() {
        try {
            List<StoryEntity> storyList = entityManager.createNativeQuery("SELECT *  FROM stories", StoryEntity.class).getResultList();

            return storyList;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean createStoryEntity(StoryEntity story) {
        try {
            transactionTemplate.execute(transactionStatus ->{
                entityManager.createNativeQuery("INSERT INTO stories(title, body) VALUES (:title, :body)")
                        .setParameter("title",story.getTitle())
                        .setParameter("body",story.getBody())
                        .executeUpdate();
                transactionStatus.flush();
                return null;
            });

            return true;

        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}