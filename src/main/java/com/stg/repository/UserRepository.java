package com.stg.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stg.entity.Ticket;
import com.stg.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUserId(int userId);

	public User findByUserIdAndPassword(int userId, String password);

}
