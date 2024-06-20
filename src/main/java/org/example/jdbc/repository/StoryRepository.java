package org.example.jdbc.repository;

import org.example.jdbc.model.Story;
import org.example.jdbc.model.StoryEntity;

import java.util.List;

public interface StoryRepository {

    public Story findStoryById(Long storyId);
    public List<Story> getAllStories();
    public boolean createStory(Story story);

    public StoryEntity findStoryEntityById(Long storyId);
    public List<StoryEntity> getAllStoryEntities();
    public boolean createStoryEntity(StoryEntity story);
}