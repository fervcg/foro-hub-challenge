package com.alura.ForoHubAPI.dto.reply;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterReplyDTO(@NotBlank String message, @NotNull Long topicId, 
                            @NotNull Long authorId, @NotBlank String solution) {
    
}
