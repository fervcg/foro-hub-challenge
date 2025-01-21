package com.alura.ForoHubAPI.domain.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.alura.ForoHubAPI.domain.model.Category;
import com.alura.ForoHubAPI.domain.model.Course;
import com.alura.ForoHubAPI.domain.model.ProfileType;
import com.alura.ForoHubAPI.domain.model.Topic;
import com.alura.ForoHubAPI.domain.model.User;
import com.alura.ForoHubAPI.dto.user.RegisterUserDTO;
import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TopicRepositoryTest {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("should fail if user or couse doesn't exist")
    void testTopicValidToSave(){
        
        var userDto = new RegisterUserDTO("cleymer", "cleymer.avila@gmail.com","12345" , ProfileType.STUDENT);
        var user = new User(userDto);
        em.persist(user);


        var course = new Course(null, "Programacion Java", Category.BACK_END);
        em.persist(course);

        // Crear un tópico referenciando al curso y usuario válidos
        Topic validTopic = new Topic();
        validTopic.setTitle("Título válido");
        validTopic.setMessage("Mensaje válido");
        validTopic.setUser(user);
        validTopic.setCourse(course);

        Topic invalidTopic = new Topic();
        invalidTopic.setTitle("Título inválido");
        invalidTopic.setMessage("Mensaje inválido");
        invalidTopic.setUser(null); // Usuario inexistente
        invalidTopic.setCourse(course);

        // Guardar el tópico válido
        Topic validResult = topicRepository.save(validTopic);
        assertNotNull(validResult.getTopicId());

        // Guardar el tópico inválido (debería lanzar una excepción)
        assertThrows(DataIntegrityViolationException.class, () -> {
            topicRepository.save(invalidTopic);
        });
    }    
}
