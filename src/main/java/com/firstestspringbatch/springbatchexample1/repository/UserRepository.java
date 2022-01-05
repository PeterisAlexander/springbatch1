package com.firstestspringbatch.springbatchexample1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.firstestspringbatch.springbatchexample1.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
