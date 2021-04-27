package com.lzc.assessment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lzc.assessment.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
}
