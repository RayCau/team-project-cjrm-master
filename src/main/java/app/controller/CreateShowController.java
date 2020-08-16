package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.dao.ShowDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class CreateShowController {
	
	public static Handler serveCreateShowPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        ctx.render(Template.CREATESHOW, model);
    };

    public static Handler handleCreateShowPagePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        
        String title = getFormTitle(ctx);
        String genre = getFormGenre(ctx);
        String year =  getFormYear(ctx);
        int length = getFormLength(ctx);
        int productionId = getFormProductionId(ctx);
        boolean isMovie = getFormIsMovie(ctx);
        if(ShowDAO.addUnapprovedShow(productionId, title, genre, year, length, isMovie))
        {
        	if(!model.containsKey("success")) {
        		model.put("success", true);
        	}
        }        
        
        ctx.render(Template.CREATESHOW, model);
    };
    
    public static boolean getFormIsMovie(Context ctx)
    {
    	String isMovie = ctx.formParam("type");
    	if(isMovie.contentEquals("movie"))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}    	
    }
    
    public static String getFormTitle(Context ctx) {
        return ctx.formParam("title");
    }
    
    public static String getFormYear(Context ctx) {
        return ctx.formParam("year");
    }
	
    public static String getFormGenre(Context ctx) {
        return ctx.formParam("genre");
    }
    
    public static int getFormLength(Context ctx)
    {
        return Integer.parseInt(ctx.formParam("length"));
    }
    
    public static int getFormProductionId(Context ctx)
    {
        return Integer.parseInt(ctx.formParam("productionCompany"));
    }
}
