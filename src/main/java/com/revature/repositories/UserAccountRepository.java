package com.revature.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {

	UserAccount findByUsernameIgnoreCaseAndPassword(String username, String password);

	UserAccount findByUsernameIgnoreCase(String username);
	
}
