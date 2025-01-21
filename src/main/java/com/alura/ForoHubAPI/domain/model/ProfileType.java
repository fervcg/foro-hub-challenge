package com.alura.ForoHubAPI.domain.model;


public enum ProfileType {
    STUDENT,
    TEACHER,
    INSTRUCTOR,
    ADMIN;

    
    public static ProfileType fromString(String name) {
        for(ProfileType p: ProfileType.values() ){
            if(name.toUpperCase().equals(p.toString().toUpperCase())){
                return p;
            }
        }
                return null;
    }
}
