package com.alura.ForoHubAPI.service.reply;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alura.ForoHubAPI.domain.model.Reply;
import com.alura.ForoHubAPI.domain.model.Topic;
import com.alura.ForoHubAPI.domain.model.User;
import com.alura.ForoHubAPI.domain.repository.ReplyRepository;
import com.alura.ForoHubAPI.domain.repository.TopicRepository;
import com.alura.ForoHubAPI.domain.repository.UserRepository;
import com.alura.ForoHubAPI.dto.reply.RegisterReplyDTO;
import com.alura.ForoHubAPI.dto.reply.ReplyDTO;
import com.alura.ForoHubAPI.dto.reply.UpdateReplyDTO;
import com.alura.ForoHubAPI.infrastructure.errors.exception.BusinessRulesValidationsException;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;

    public ReplyDTO answerTopic(RegisterReplyDTO data){
        Optional<User> userFound = userRepository.findById(data.authorId());
        Optional<Topic> topicFound = topicRepository.findById(data.topicId());

        if (userFound.isEmpty()) {
            throw new BusinessRulesValidationsException("El usuario ingresado no existe");
        }
        if (topicFound.isEmpty()) {
            throw new BusinessRulesValidationsException("El topico al que hace referencia no existe");
        }

        User user = userFound.get();
        Topic topic = topicFound.get();
        var authenticatedUser = getAuthenticateUser();

        if (!data.authorId().equals(authenticatedUser.getUserId())) {
            throw new BusinessRulesValidationsException("Usuario no authenticado");
        }
        if (data.authorId().equals(topic.getUser().getUserId())) {
            throw new BusinessRulesValidationsException("No es posible responder al post publicado por el mismo usuario");
        }

        Reply reply = new Reply(data);
        reply.setUser(user);  
        reply.setTopic(topic);

        return new ReplyDTO(replyRepository.save(reply));

    }

    public Page<ReplyDTO> getAllReplies(Pageable pageable){
        return replyRepository.findAll(pageable).map(ReplyDTO::new);
    }

    public ReplyDTO updateReply(UpdateReplyDTO data){
        var reply = replyRepository.getReferenceById(data.replyId());

        // Verify 5 minutes gap between created time and update time
        var createdTime = reply.getCreatedAt();
        var now = LocalDateTime.now();
        var minutesDiference = Duration.between(createdTime, now).toMinutes();

        if (minutesDiference > 5) {
            throw new BusinessRulesValidationsException("La respuesta no puede ser actualizada pasado 5 minutos");
        }
        reply.updateData(data);
        return new ReplyDTO(replyRepository.save(reply));
    }

    public void deleteReply(Long replyId){
        var authenticatedUser = getAuthenticateUser();
        var reply = replyRepository.getReferenceById(replyId);
        if (!reply.getUser().getUserId().equals(authenticatedUser.getUserId())) {
            throw new BusinessRulesValidationsException("No puede eliminar respuestas de otros usuarios");
        }
        replyRepository.delete(reply);
    }
    private User getAuthenticateUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new BusinessRulesValidationsException("Usuario no authenticado");
        }

        User user;
        try {
            user = userRepository.findByEmail(authentication.getName());
        } catch (RuntimeException exception){
            throw new BusinessRulesValidationsException("El usuario no existe");
        }
        return user;
    }

}
