package com.kardz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kardz.entity.UserEntity;
import com.kardz.models.User;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

}
