package com.revature.services;

import static org.junit.Assert.*;

import javax.servlet.http.Cookie;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.models.UserAccount;
import com.revature.repositories.UserAccountRepository;

@RunWith (SpringRunner.class)
@DataJpaTest
public class UserAccountServiceTest {
	@TestConfiguration
	static class UserAccountServiceTestContextConfiguration {

	    @MockBean
	    private UserAccountRepository userAccountRepository;
		
		@Bean
		public UserAccountService userAccountService () {
			UserAccountService serv = new UserAccountService ();
			serv.setUserAccountRepository(userAccountRepository);
			return serv;
		}
	}
	
	@Autowired
	private UserAccountService userAccountService;
	
	private UserAccount users[] = {
			new UserAccount (null, "Test_Dannie", "pass"),
			new UserAccount (null, "Test_Bobbert", "bobbert"),
			new UserAccount (null, "Test_Nathan", "pass"),
			new UserAccount (null, "Test_Ben", "pass"),	
	};
	
	
	
	@Test
	public void giveCredentials_thenCreateAccount () {
		for (UserAccount u : users) {
			assertTrue (this.userAccountService.insertUserAccount(u));
		}
	}
	
	@Test public void givenCredentials_thenLogin_thenLogout () {
		for (UserAccount u : users) {
			Cookie cookie = this.userAccountService.login(u);
			
			assertNotNull (cookie);
			
//			assertTrue (this.userAccountService.logout(cookie.getValue()));
		}
	}
	
	
}










