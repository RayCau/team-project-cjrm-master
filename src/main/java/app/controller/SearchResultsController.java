package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import app.dao.ShowDAO;
import app.model.Person;
import app.model.Show;
import io.javalin.http.Handler;

public class SearchResultsController
{
	
	private static String searchTerm = "";
	private static String searchType = "";
	
	private static List<Show> shows;
	private static List<Person> people;
	
	public static Handler serveSearchResultsPage = ctx ->
	{
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        createSearchResultPage(model);
        ctx.render(Template.SEARCHRESULTS, model);
    };
    
    public static Handler handleSearchResultsPost = ctx ->
	{
        String clickedResult = ctx.formParam("clicked");
        
    	if (searchType.equals("show")) {
    		Show s = compareShowTitle(clickedResult);
    		ShowpageController.setShow(s);
    		ctx.redirect(Web.SHOWPAGE);
 
    	} else if (searchType.equals("person")) {
    		Person p = comparePersonName(clickedResult);
    		PersonpageController.setPerson(p);
    		ctx.redirect(Web.PERSONPAGE);
    	}
    };
    
    private static void createSearchResultPage(Map<String, Object> model)
	{
    	model.put("searchTerm", searchTerm);
    	model.put("searchType", searchType);
    	
    	if (searchType.equals("show")) {
    		// Search for show
    		shows = ShowDAO.getShowsByTitle(searchTerm);
    		// Pass shows to view
    		if (shows.isEmpty()) {
    			model.put("noResult", true);
    		} else {
    			model.put("shows", shows);
    		}

    	} else if (searchType.equals("person")) {
    		// Search for person
    		people = PersonDAO.getPeopleByName(searchTerm);
    		// Pass people to view
    		if (people.isEmpty()) {
    			model.put("noResult", true);
    		} else {
    			model.put("people", people);
    		}
    	}
    }
    
    // Compare clicked result with show titles
    // Should never return null
    private static Show compareShowTitle(String clickedResult)
	{
    	for (Show s : shows) {
			String sTitle = s.getShowTitle();
			// Remove role(s) from clicked result
			String clickedTitle = clickedResult.substring( 0, 
						clickedResult.indexOf("(")-1 );
			
			if (sTitle.equals(clickedTitle)) {
				return s;
			}
		}
    	return null;
    }
    
    // Compare clicked result with person names
    // Should never return null
    private static Person comparePersonName(String clickedResult)
	{
    	for (Person p : people) {
			String pName = p.getFullName();
			// Remove year (and "TV Series") from clicked result
			String clickedName = clickedResult.substring( 0, 
						clickedResult.indexOf("(")-1 );
			
			if (pName.equals(clickedName)) {
				return p;
			}
    	}
    	return null;
    }
    
    public static void setSearchTerm(String type, String search)
	{
    	searchType = type;
    	searchTerm = search;
    }
}