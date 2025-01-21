package com.alura.ForoHubAPI.service.topic.validations;

import com.alura.ForoHubAPI.dto.topic.RegisterTopicDTO;

public interface ValidatorPostingTopic {
    public void validate(RegisterTopicDTO data);
}
