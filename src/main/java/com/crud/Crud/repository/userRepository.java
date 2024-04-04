package com.crud.Crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.Crud.model.user;

public interface userRepository extends JpaRepository <user,Long>{
	

}
