package test;

import app.dao.*;

import app.model.AccountRequestForm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;


/**
 *  Implement and test {@link AccountRequestFormDAO} that respects the following constraints:

 *
 * Initialize the test object with setUp method.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountRequestFormDAOTest {
	
	AccountRequestFormDAO testSubject;
	String testKnownUsername;
	String testUnknownName;
	String knownID;

	
	@BeforeAll
	public void setUp()
	{
		
		testSubject = new AccountRequestFormDAO();
		testKnownUsername = "caramel6";
		knownID = "test6";
		testUnknownName = "Fudge7";
		
	}
	
	///TEST AREA////
	
	@Test ///get user account by username function test (known user)
	public void getUserRequestFormByUserID_ReturnClass_KnownUser()
	{	//if returns class type Account, result is true.
		Assertions.assertEquals(AccountRequestForm.class,testSubject.getUserRequestFormByUserID(knownID).getClass());
	}
	
	@Test ///check user exists from userID
	public void checkIfUsernameExists_ReturnTrue_KnownUser()
	{
		Assertions.assertEquals(true,testSubject.checkIfUsernameExists(knownID));
	}
	
	@Test ///check user exists from userID
	public void checkIfUsernameExists_ReturnFalse_unknownUser()
	{	
		Assertions.assertEquals(false,testSubject.checkIfUsernameExists(testUnknownName));
	}
	
	@Test ///check arraylist is returned
	public void getUserRequestForms_ReturnList_callingFunction()
	{
		Assertions.assertEquals(ArrayList.class,testSubject.getUserRequestForms().getClass());
	}

}
