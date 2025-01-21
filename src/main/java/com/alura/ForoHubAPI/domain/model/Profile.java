package com.alura.ForoHubAPI.domain.model;

import java.util.List;

import com.alura.ForoHubAPI.dto.user.RegisterUserDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Profile")
@Table(name = "profiles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "profileId")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    private String name;
    @ManyToMany(mappedBy = "profiles")
    private List<User> users;

    public Profile(RegisterUserDTO data){
        this.name = data.profileType().toString();
    }
}
