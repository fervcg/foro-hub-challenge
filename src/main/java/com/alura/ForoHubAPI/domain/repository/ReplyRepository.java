package com.alura.ForoHubAPI.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.ForoHubAPI.domain.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
    
}
