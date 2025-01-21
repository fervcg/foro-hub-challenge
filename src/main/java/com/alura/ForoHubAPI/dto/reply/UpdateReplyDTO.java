package com.alura.ForoHubAPI.dto.reply;

import jakarta.validation.constraints.NotNull;

public record UpdateReplyDTO(@NotNull Long replyId, String message, String solution) {


    
}
