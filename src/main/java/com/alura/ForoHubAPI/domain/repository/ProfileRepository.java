package com.alura.ForoHubAPI.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.ForoHubAPI.domain.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
}
