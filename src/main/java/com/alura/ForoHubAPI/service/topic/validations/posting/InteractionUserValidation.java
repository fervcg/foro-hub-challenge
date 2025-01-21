package com.alura.ForoHubAPI.service.topic.validations.posting;

import com.alura.ForoHubAPI.domain.repository.TopicRepository;
import com.alura.ForoHubAPI.dto.topic.RegisterTopicDTO;
import com.alura.ForoHubAPI.infrastructure.errors.exception.BusinessRulesValidationsException;
import com.alura.ForoHubAPI.service.topic.validations.ValidatorPostingTopic;

public class InteractionUserValidation implements ValidatorPostingTopic {

    private TopicRepository topicRepository;

    @Override
    public void validate(RegisterTopicDTO data) {
        var interactionUserCount = topicRepository.countTopicInteractionFromUser(data.authorId());

        System.out.println("La cantidad de post en los que participo el usuario es: "+ interactionUserCount);
        if ((interactionUserCount < 3)) {
            throw new BusinessRulesValidationsException("No puede publicar un topico ya que solo ha interactuado con "+ interactionUserCount + " topicos");
        }
    }
    
}
