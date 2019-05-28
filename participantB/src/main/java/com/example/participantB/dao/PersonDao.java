package com.example.participantB.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface PersonDao {
    @Insert("INSERT INTO test (`id`, `name`) VALUES ('1', '2');")
    void insertP();
}