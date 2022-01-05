package com.firstestspringbatch.springbatchexample1.batch;


import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.firstestspringbatch.springbatchexample1.model.UserEntity;
import com.firstestspringbatch.springbatchexample1.repository.UserRepository;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<UserEntity> {

    private UserRepository userRepository;

    @Autowired
    public DBWriter (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void write(List<? extends UserEntity> users) throws Exception{
        System.out.println("Data Saved for Users: " + users);
        userRepository.saveAll(users);
    }
}
