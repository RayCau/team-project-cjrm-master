package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import app.model.Show;
import io.javalin.http.Handler;

public class SelectShowEditController
{
    public static Handler serveSelectShowPage = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        createSelectShowPage(model);
        ctx.render(Template.SELECTSHOWEDIT, model);
    };
    
    public static Handler handleSelectShowPost = ctx ->
    {
        String showId = ctx.formParam("showId");
        // Should never return null; show id is from a previous query
        Show show = ShowDAO.getShowById(showId);
    	EditShowController.setShow(show);
    	// Prevents null pointer exception 
        // when user visits page directly without selecting a show first
    	EditShowController.setIsShowSelected(true);
        
    	ctx.redirect(Web.EDITSHOW);
    };
    
    private static String userProcoId;
    private static void createSelectShowPage(Map<String, Object> model)
    {
    	userProcoId = (String) model.get("currentUserProcoId");
    	getApprovedShows(model);
    	getUnapprovedShows(model);
    }
    
    private static void getApprovedShows(Map<String, Object> model)
    {
    	List<Show> approvedShows = ShowDAO.getApprovedShows(userProcoId);

        if (approvedShows.isEmpty())
        {
            model.put("noApprovedShow", true);
        }
        else
        {
            model.put("approvedShows", approvedShows);
        }
    }
    
    private static void getUnapprovedShows(Map<String, Object> model)
    {
    	List<Show> unapprovedShows = ShowDAO.getUnapprovedShows(userProcoId);

        if (unapprovedShows.isEmpty())
        {
            model.put("noUnapprovedShow", true);
        }
        else
        {
            model.put("unapprovedShows", unapprovedShows);
        }
    }
}