package test;

import app.dao.AccountDAO;
import app.model.Account;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Assertions;


/**
 *  Implement and test {@link AccountDAO} that respects the following constraints:

 *
 * Initialize the test object with setUp method.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountDAOTest {
	
	AccountDAO testSubject;
	String testKnownUsername;
	String testUnknownName;
	

	
	@BeforeAll
	public void setUp()
	{
		
		testSubject = new AccountDAO();
		testKnownUsername = "caramel6";
		testUnknownName = "Fudge7";
		
	}
	
	///TEST AREA////
	
	@Test ///get user account by username function test (known user)
	public void getUserByUsername_ReturnClass_KnownUser()
	{	//if returns class type Account, result is true.
		Assertions.assertEquals(Account.class,testSubject.getUserByUsername(testKnownUsername).getClass());
	}
	
	@Test ///get user by username function test (unknown user) should return null
	public void getUserByUsername_ReturnNull_UnknownUser()
	{
		Assertions.assertEquals(null,testSubject.getUserByUsername(testUnknownName));
	}
	
	@Test ///check username exists in database with known user
	public void checkIfUsernameExists_returnTrue_KnownUser()
	{
		Assertions.assertEquals(true,testSubject.checkIfUsernameExists(testKnownUsername));
	}

	@Test ///check username exists in database with unknown user
	public void checkIfUsernameExists_returnFalse_unknownUser()
	{
		Assertions.assertEquals(false,testSubject.checkIfUsernameExists(testUnknownName));
	}

}
