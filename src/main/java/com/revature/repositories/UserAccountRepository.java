package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {

	UserAccount findByUsernameIgnoreCaseAndPassword(String username, String password);

	
}
