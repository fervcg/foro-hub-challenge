package com.alura.ForoHubAPI.dto.topic;

import java.time.LocalDateTime;

import com.alura.ForoHubAPI.domain.model.Topic;
import com.alura.ForoHubAPI.domain.model.TopicStatus;

public record NewTopicDTO(
    Long topicId,
    String title,
    String message,
    LocalDateTime createdAt,
    TopicStatus status,
    Long userId,
    Long courseId
    ) {

    public NewTopicDTO(Topic topic){
        this(topic.getTopicId(), topic.getTitle(), topic.getMessage(), 
        topic.getCreatedAt(), topic.getStatus(), topic.getUser().getUserId(), topic.getCourse().getCourseId());
    }    
    
}
