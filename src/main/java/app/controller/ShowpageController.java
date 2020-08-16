package app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.ReviewDAO;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ShowpageController {
	
	private static Show show;
	
	public static Handler serveShowpage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        createShowPage(ctx, model);
        ctx.render(Template.SHOWPAGE, model);
    };

	public static Handler handleShowPagePost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        
        String id = (String) model.get("currentUser");        
        int show_id = show.getShowId();
        int rating = Integer.parseInt(getFormRating(ctx));
        String review = getFormReview(ctx);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        
        if(ReviewDAO.addReview(show_id, id, rating, review, date)) {
        	if(!model.containsKey("success")) {
        		model.put("success", true);
        	}
        }        
        
        createShowPage(ctx, model);
        ctx.render(Template.SHOWPAGE, model);
    };
    
    private static void createShowPage(Context ctx, Map model) {
    	String modelKeys[] = {"id", "title", "length", "genre", "year","rating", "reviews"};
/*    	//To access the post information, ctx.formParam(<name field of attribute in signup.vm>) is used.
    	String searchField = ctx.formParam("showTitleSearch");
 		Show show = ShowDAO.getShowsByTitle(searchTerm);
*/    	
    	//If there is already a previous showTitle in the model, reset it as the most recent search
    	//Otherwise, create the showTitle key and set it as the show title.
    	if(show != null) {
    		if(model.containsKey(modelKeys[0]) && model.containsKey(modelKeys[1]) && model.containsKey(modelKeys[2]) && model.containsKey(modelKeys[3])) {
        		model.get(modelKeys[0]).equals(show.getShowId());
        		model.get(modelKeys[1]).equals(show.getShowTitle());
        		model.get(modelKeys[2]).equals(show.getShowLength());
        		model.get(modelKeys[3]).equals(show.getShowGenre());
        		model.get(modelKeys[4]).equals(show.getShowYear());
        		
        		String show_id = Integer.toString(show.getShowId());
        		model.get(modelKeys[6]).equals(ReviewDAO.getReviewsByTitle(show_id));
        		
        		if(model.containsKey(modelKeys[5]))
        		{
        			model.get(modelKeys[5].equals(String.valueOf(show.getAverageRating())));
        		}
        		
        	} else {
        		
        		model.put(modelKeys[0], show.getShowId());
        		model.put(modelKeys[1], show.getShowTitle());
        		model.put(modelKeys[2], show.getShowLength());
        		model.put(modelKeys[3], show.getShowGenre());
        		model.put(modelKeys[4], show.getShowYear());
        		model.put(modelKeys[5], show.getAverageRating());        		
        		String show_id = Integer.toString(show.getShowId());
        		model.put(modelKeys[6], ReviewDAO.getReviewsByTitle(show_id));
        		
        	}
    	}    	
    }
    
    public static void setShow(Show s) {
    	show = s;
    }
    
    public static String getFormRating(Context ctx)
    {
    	return ctx.formParam("rating");
    }
    
    public static String getFormReview(Context ctx) {
        return ctx.formParam("reviewText");
    }
    
    public static String getFormYear(Context ctx) {
        return ctx.formParam("year");
    }
	
    public static String getFormGenre(Context ctx) {
        return ctx.formParam("genre");
    }
}
