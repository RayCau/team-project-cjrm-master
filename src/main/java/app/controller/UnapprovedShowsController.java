package app.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.PersonDAO;
import app.dao.ShowDAO;
import app.dao.utils.DatabaseUtils;
import app.model.Account;
import app.dao.AccountDAO;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;



public class UnapprovedShowsController
{
    private static List<Show> unapprovedShows;

    public static Handler serveUnapprovedPage = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        createUnapprovedShowsPage(model);
        ctx.render(Template.UNAPPROVEDSHOWS, model);
    };

    public static Handler handleUnapprovedPost = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        List<String> selectedShows = ctx.formParams("show");
        
        String clicked = ctx.formParam("submit");
        System.out.println(clicked);
        
        if(selectedShows.size() > 0) {
        	
        	if(clicked.equals("Approve")) {
	
        		approveSelectedShows(selectedShows);
        		
        	}else if(clicked.equals("Delete")){
        	//delete shows
        		deleteSelectedShows(selectedShows);
        		
        	}
        }
        
        ctx.redirect(Web.UNAPPROVEDSHOWS);
    };

    private static void createUnapprovedShowsPage(Map<String, Object> model)
    {
        unapprovedShows = ShowDAO.getUnapprovedShows();

        if (unapprovedShows.isEmpty())
        {
            model.put("noResult", true);
        }
        else
        {
            model.put("shows", unapprovedShows);
        }
    }

    private static boolean approveSelectedShows(List<String> clickedResults)
    {
        int check = 0;
        for (String s : clickedResults)
        {
            ShowDAO.approveShow(Integer.parseInt(s));
            check += 1;
        }

        if(check > 0)
        {
            return true;
        }
        return false;
    }
    
  //DELETE SELECTED SHOWS METHOD
    private static boolean deleteSelectedShows(List<String> clickedResults)
    {
        int check = 0;
        for (String s : clickedResults)
        {
            ShowDAO.removeShow(Integer.parseInt(s));
            check += 1;
        }

        if(check > 0)
        {
            return true;
        }
        return false;
    }
}