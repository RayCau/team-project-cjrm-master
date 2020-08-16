package test;

import app.dao.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;


/**
 *  Implement and test {@link ReviewDAO} that respects the following constraints:

 *
 * Initialize the test object with setUp method.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewDAOTest {
	
	ReviewDAO testSubject;


	
	@BeforeAll
	public void setUp()
	{
		
		testSubject = new ReviewDAO();

	}
	
	///TEST AREA////
	
	@Test ///get review list
	public void getReviewsByTitle_ReturnList_KnownShow()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(ArrayList.class,testSubject.getReviewsByTitle("star wars").getClass());
	}
	
	@Test ///get review list
	public void addReview_returnTrue_KnownShow()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(true,testSubject.addReview(1,"caramel6",5,"this is a test review","'2019-01-04'"));
	}
	
	@Test ///get review list
	public void addReview_returnTrue_unknownShow()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(false,testSubject.addReview(50,"caramel6",5,"this is a test review","'2019-01-04'"));
	}
	
}
