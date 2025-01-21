package com.alura.ForoHubAPI.service.topic.validations.posting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alura.ForoHubAPI.domain.repository.TopicRepository;
import com.alura.ForoHubAPI.dto.topic.RegisterTopicDTO;
import com.alura.ForoHubAPI.infrastructure.errors.exception.BusinessRulesValidationsException;
import com.alura.ForoHubAPI.service.topic.validations.ValidatorPostingTopic;


@Component
public class DuplicateTopicValidation implements ValidatorPostingTopic {

    @Autowired
    private TopicRepository topicRepository;
    
    @Override
    public void validate(RegisterTopicDTO data) {
        var duplicateTopic = topicRepository.existsByTitleAndMessage(data.title(),  data.message());

        if (duplicateTopic) {
            throw new BusinessRulesValidationsException("Existe un topico con exactamente el mismo titulo y mensaje, por lo que no puede ser duplicado");
        }
    }
    
}
