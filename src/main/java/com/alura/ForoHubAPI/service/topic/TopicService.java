package com.alura.ForoHubAPI.service.topic;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.alura.ForoHubAPI.domain.model.Course;
import com.alura.ForoHubAPI.domain.model.Topic;
import com.alura.ForoHubAPI.domain.model.User;
import com.alura.ForoHubAPI.domain.repository.CourseRepository;
import com.alura.ForoHubAPI.domain.repository.TopicRepository;
import com.alura.ForoHubAPI.domain.repository.UserRepository;
import com.alura.ForoHubAPI.dto.topic.NewTopicDTO;
import com.alura.ForoHubAPI.dto.topic.RegisterTopicDTO;
import com.alura.ForoHubAPI.dto.topic.UpdateTopicDTO;
import com.alura.ForoHubAPI.infrastructure.errors.exception.BusinessRulesValidationsException;
import com.alura.ForoHubAPI.service.topic.validations.ValidatorPostingTopic;


@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private List<ValidatorPostingTopic> postValidators;

    
    public NewTopicDTO publish(RegisterTopicDTO data){

        Optional<User> userFound = userRepository.findById(data.authorId());
        
        Optional<Course> courseFound = courseRepository.findById(data.courseId());

        if (userFound.isEmpty()) {
            throw new BusinessRulesValidationsException("El usuario para crear el topico no existe");
        }

        if (courseFound.isEmpty()) {
            throw new BusinessRulesValidationsException("El Curso al que hace referencia no existe");
        }

        // VALIDATION
        postValidators.forEach(v -> v.validate(data));


        User user = userFound.get();
        Course course = courseFound.get();
        var authenticatedUser = getAuthenticatedUser();

        if (!data.authorId().equals(authenticatedUser.getUserId())) {
            throw new BusinessRulesValidationsException("Error al crear el topico, accion no permitida para el usuario especificado");
        }
        
        Topic topic = new Topic(data);
        topic.setUser(user);
        topic.setCourse(course);

        return new  NewTopicDTO(topicRepository.save(topic));
    }

    public NewTopicDTO updateTopic(UpdateTopicDTO data){
        var authenticatedUser = getAuthenticatedUser();

        validateTopicAuthor(data.topicId(), authenticatedUser.getUserId());
        Topic topic = topicRepository.getReferenceById(data.topicId());


        // validate 15 minutes gap between created time and update time
        var createdTime = topic.getCreatedAt();
        var now = LocalDateTime.now();

        var minutesDiference = Duration.between(createdTime, now).toMinutes();

        if (minutesDiference > 15) {
            throw new BusinessRulesValidationsException("El topico no puede ser actualizado despues de haber pasado 15 minutos");            
        }
        topic.updateData(data);

        return new NewTopicDTO(topicRepository.save(topic));
    }

    public void deleteTopic(Long topicId){
        var authenticatedUser = getAuthenticatedUser();

        validateTopicAuthor(topicId, authenticatedUser.getUserId());

        topicRepository.deleteById(topicId);
    }

    private void validateTopicAuthor(Long topicId, Long userId){

        Topic topic = topicRepository.getReferenceById(topicId);

        if (topic == null) {
            throw new BusinessRulesValidationsException("El topico no se encuentra");
        }

        if (!topic.getUser().getUserId().equals(userId)) {
            throw new BusinessRulesValidationsException("No tienes permiso para modificar topicos de otros usuarios");
        }
    }


    private User getAuthenticatedUser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user;
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessRulesValidationsException("Usuario no autenticado");
        }


        try {
            user = userRepository.findByEmail(authentication.getName());
        } catch (RuntimeException exception){
            throw new BusinessRulesValidationsException("El usuario no existe");
        }
        return user;
    }   
    

}