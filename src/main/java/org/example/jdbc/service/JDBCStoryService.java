package org.example.jdbc.service;

import org.example.jdbc.model.Story;
import org.example.jdbc.model.StoryEntity;
import org.example.jdbc.repository.StoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JDBCStoryService {

    private final StoryRepository storyRepository;

    public JDBCStoryService(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public Story findById(Long storyId) {
        return this.storyRepository.findStoryById(storyId);
    }

    public List<Story> getAllStories() {
        return storyRepository.getAllStories();
    }

    public boolean createStory(Story story) {
        return storyRepository.createStory(story);
    }


    public StoryEntity findStoryEntityById(Long storyId) {
        return this.storyRepository.findStoryEntityById(storyId);
    }

    public List<StoryEntity> getAllStoryEntities() {
        return storyRepository.getAllStoryEntities();
    }

    public boolean createStory(StoryEntity storyEntity) {
        return storyRepository.createStoryEntity(storyEntity);
    }
}
