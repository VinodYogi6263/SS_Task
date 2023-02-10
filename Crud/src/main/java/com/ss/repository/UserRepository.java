package com.ss.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ss.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.userName LIKE CONCAT('%',:name,'%')Or u.userEmail LIKE CONCAT('%',:email,'%')")
	public Page<User> findByuserNameOruserEmail(String name,String email, Pageable p);

}
