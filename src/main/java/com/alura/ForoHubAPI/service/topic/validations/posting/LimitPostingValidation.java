package com.alura.ForoHubAPI.service.topic.validations.posting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.ForoHubAPI.domain.repository.TopicRepository;
import com.alura.ForoHubAPI.dto.topic.RegisterTopicDTO;
import com.alura.ForoHubAPI.infrastructure.errors.exception.BusinessRulesValidationsException;
import com.alura.ForoHubAPI.service.topic.validations.ValidatorPostingTopic;

@Component
public class LimitPostingValidation implements ValidatorPostingTopic{

    @Autowired
    private TopicRepository topicRepository;
    
    @Override
    public void validate(RegisterTopicDTO data) {
        var countTopicPostedToday = topicRepository.countTopicsCreatedByUserToday(data.authorId());
        
        if (countTopicPostedToday >= 3) {
            throw new BusinessRulesValidationsException("Haz alcanzado el limite de 3 post diarios");
        }
    }
    
}
