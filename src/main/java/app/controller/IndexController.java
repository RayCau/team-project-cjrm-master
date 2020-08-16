package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import io.javalin.http.Handler;
import java.util.Map;

public class IndexController
{
    public static Handler serveIndexPage = ctx ->
	{
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        ctx.render(Template.INDEX, model);
    };
    
    public static Handler handleIndexPost = ctx ->
	{
	  	String showSearch = ctx.formParam("showTitleSearch");
	  	String personSearch = ctx.formParam("personSearch");

	  	if(showSearch != null)
	  	{
	  		SearchResultsController.setSearchTerm("show", showSearch);
	  		ctx.redirect(Web.SEARCHRESULTS);
	  	}

	  	else if(personSearch != null)
	  	{
	  		SearchResultsController.setSearchTerm("person", personSearch);
	  		ctx.redirect(Web.SEARCHRESULTS);
	  	}
  };
}
