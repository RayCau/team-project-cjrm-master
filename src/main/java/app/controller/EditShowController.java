package app.controller;

import java.util.List;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ProductionCompanyDAO;
import app.dao.ShowDAO;
import app.model.ProductionCompany;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class EditShowController
{
	private static Show show;
	private static boolean isShowSelected;
	private static String showProcoName;
	
	public static Handler serveEditShowPage = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        
        // Wrapped in if-block to prevent null pointer exception 
        // when user visits page directly without selecting a show first
        if (isShowSelected)
        {
        	model.put("show", show);
        	// For displaying PCo name at bottom of form
        	model.put("showProcoName", getShowProcoName());
        }
        	
        ctx.render(Template.EDITSHOW, model);
    };
    
    public static Handler handleEditShowPost = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        
        int showId = show.getShowId();
        String title = getFormTitle(ctx);
        String genre = getFormGenre(ctx);
        String year =  getFormYear(ctx);
        int length = getFormLength(ctx);
        int productionId = getFormProductionId(ctx);
        boolean isMovie = getFormIsMovie(ctx);
        if(ShowDAO.editShow(showId, productionId, title, genre, year, length, isMovie))
        {
        	if(!model.containsKey("success")) {
        		model.put("success", true);
        	}
        }
        else
        {
        	model.put("fail", true);
        }
        
        // Put updated show data in model map
        // or same original data if updating the database failed
        show = ShowDAO.getShowById(Integer.toString(showId));
        model.put("show", show);
        // For displaying PCo name at bottom of form
        model.put("showProcoName", getShowProcoName());
        
    	ctx.render(Template.EDITSHOW, model);
    };
    
    public static void setShow(Show s)
    {
    	show = s;
    };
    
    public static void setIsShowSelected(boolean b)
    {
    	isShowSelected = b;
    }
    
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
    
    private static String getShowProcoName()
    {
    	List<ProductionCompany> procos = ProductionCompanyDAO.getProcos();
        
        for (ProductionCompany proco : procos)
        {
        	if (proco.getId() == show.getProcoId())
        	{
        		showProcoName = proco.getName();
        	}
        }
        
        return showProcoName;
    }
}