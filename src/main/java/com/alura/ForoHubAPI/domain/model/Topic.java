package com.alura.ForoHubAPI.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alura.ForoHubAPI.dto.topic.RegisterTopicDTO;
import com.alura.ForoHubAPI.dto.topic.UpdateTopicDTO;


@Entity(name = "Topic")
@Table(name = "topics")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "topicId")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;
    private String title;
    private String message;
    private LocalDateTime createdAt = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private TopicStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    public Topic(RegisterTopicDTO data){
        this.title = data.title();
        this.message = data.message();
        if (data.status()==null) {
            this.status = TopicStatus.ACTIVE ;  
        }
    }

    public void updateData(UpdateTopicDTO data){
        if (data.title()!= null) {
            this.title = data.title();
        }

        if (data.message() != null) {
            this.message = data.message();
        }

        if (data.status()!=null) {
            this.status = data.status();
        } else {
            this.status = TopicStatus.ACTIVE;
        }
    }
}
