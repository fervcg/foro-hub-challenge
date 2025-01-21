package com.alura.ForoHubAPI.dto.topic;

import com.alura.ForoHubAPI.domain.model.TopicStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTopicDTO(@NotNull Long topicId, @NotBlank String title, @NotBlank String message, TopicStatus status) {
    
}
