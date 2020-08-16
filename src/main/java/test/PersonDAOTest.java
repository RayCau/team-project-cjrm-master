package test;

import app.dao.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;


/**
 *  Implement and test {@link PersonDAO} that respects the following constraints:

 *
 * Initialize the test object with setUp method.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonDAOTest {
	
	PersonDAO testSubject;
	String testKnownUsername;
	String testUnknownName;
	String knownID;

	
	@BeforeAll
	public void setUp()
	{
		
		testSubject = new PersonDAO();
		testKnownUsername = "caramel6";
		knownID = "test6";
		testUnknownName = "Fudge7";
		
	}
	
	///TEST AREA////
	
	@Test ///get user list
	public void getPeopleByName_ReturnList_KnownUser()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(ArrayList.class,testSubject.getPeopleByName(testKnownUsername).getClass());
	}
	
}
