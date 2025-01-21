package com.alura.ForoHubAPI.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.alura.ForoHubAPI.dto.reply.RegisterReplyDTO;
import com.alura.ForoHubAPI.dto.reply.UpdateReplyDTO;

@Entity(name = "Reply")
@Table(name = "replies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "replyId")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;
    private String message;
    @ManyToOne
    @JoinColumn(name = "topic")
    private Topic topic;
    private LocalDateTime createdAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    private String solution;

    public Reply(RegisterReplyDTO data){
        this.message = data.message();
        this.solution = data.solution();
    }

    public void updateData(UpdateReplyDTO data) {
        if (!data.message().isBlank()) {
            this.message = data.message();
        }

        if (!data.solution().isBlank()) {
            this.solution = data.solution();
        }
    }
}
