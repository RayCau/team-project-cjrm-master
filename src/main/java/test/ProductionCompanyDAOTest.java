package test;

import app.dao.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;


/**
 *  Implement and test {@link ProductionCompanyDAO} that respects the following constraints:

 *
 * Initialize the test object with setUp method.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductionCompanyDAOTest {
	
	ProductionCompanyDAO testSubject;


	
	@BeforeAll
	public void setUp()
	{
		
		testSubject = new ProductionCompanyDAO();

	}
	
	///TEST AREA////
	
	@Test ///get production company list
	public void getProcos_ReturnList_CallFunction()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(ArrayList.class,testSubject.getProcos().getClass());
	}
	
}
