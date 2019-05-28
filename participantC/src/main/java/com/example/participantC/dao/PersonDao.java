package com.example.participantC.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author: Halo
 * @date: 2019/5/8 9:43
 * @Description:
 */
@Component
public interface PersonDao {
    @Insert("INSERT INTO test (`id`, `name`) VALUES ('1', '2');")
    void insertP();
}
