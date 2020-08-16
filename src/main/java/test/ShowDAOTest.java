package test;

import app.dao.*;
import app.model.Show;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;


/**
 *  Implement and test {@link ShowDAO} that respects the following constraints:

 *
 * Initialize the test object with setUp method.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ShowDAOTest {
	
	ShowDAO testSubject;


	
	@BeforeAll
	public void setUp()
	{
		
		testSubject = new ShowDAO();

	}
	
	///TEST AREA////
	
	@Test ///get show list
	public void getShowsByTitle_ReturnList_KnownShow()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(ArrayList.class,testSubject.getShowsByTitle("star wars").getClass());
	}
	
	@Test ///get show object
	public void getShowsByID_ReturnShow_KnownShow()
	{	
		Assertions.assertEquals(Show.class,testSubject.getShowById("1").getClass());
	}
	
	@Test ///get null
	public void getShowsByID_ReturnShow_unknownShow()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(null,testSubject.getShowById("1000000"));
	}
	
	@Test ///get show list
	public void getApprovedShows_ReturnList_callFunction()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(ArrayList.class,testSubject.getApprovedShows().getClass());
	}
	
	@Test ///get show list
	public void getUnapprovedShows_ReturnList_callFunction()
	{	//if returns class type list, result is true.
		Assertions.assertEquals(ArrayList.class,testSubject.getUnapprovedShows().getClass());
	}
	
	@Test ///get show list
	public void addUnapprovedShow_ReturnTrue_goodApplication()
	{	
		Assertions.assertEquals(true,testSubject.addUnapprovedShow(1,"testShow","testGenre","2020",120,true));
	}
	
	@Test ///get show list
	public void addUnapprovedShow_ReturnFalse_badApplication()
	{	//fake production company
		Assertions.assertEquals(false,testSubject.addUnapprovedShow(1000,"testShow","testGenre","2020",120,true));
	}
	
}
