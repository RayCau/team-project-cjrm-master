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
import app.model.AccountRequestForm;
import app.dao.AccountDAO;
import app.dao.AccountRequestFormDAO;
import app.model.Show;
import io.javalin.http.Context;
import io.javalin.http.Handler;


public class UnapprovedRequestFormsController
{
    private static List<AccountRequestForm> requestForms;

    public static Handler serveUnapprovedRequestPage = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        createUnapprovedRequestForms(model);
        ctx.render(Template.REQUESTFORMS, model);
    };

    public static Handler handleUnapprovedRequestFormsPost = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        List<String> selectedShows = ctx.formParams("form");
        
        String clicked = ctx.formParam("submit");
        System.out.println(clicked);
        
        if(selectedShows.size() > 0) {
        	
        	if(clicked.equals("Approve")) {
	
        		approveSelectedForm(selectedShows);
        		
        	}else if(clicked.equals("Delete")){
        	
        		
        	}
        }
        
        ctx.redirect(Web.REQUESTFORMS);
    };

    private static void createUnapprovedRequestForms(Map<String, Object> model)
    {
        requestForms = AccountRequestFormDAO.getUserRequestForms();

        if (requestForms.isEmpty())
        {
            model.put("noResult", true);
        }
        else
        {
            model.put("requestForms", requestForms);
        }
    }

    private static boolean approveSelectedForm(List<String> clickedResults)
    {
        int check = 0;
        for (String id : clickedResults)
        {
        	AccountRequestForm arf = AccountRequestFormDAO.getUserRequestFormByUserID(id);
        	AccountDAO.updateUserAccountType(arf.getUserID(), arf.getRequestedType());
        	AccountRequestFormDAO.deleteRequestForm(arf.getUserID());
            check += 1;
        }

        if(check > 0)
        {
            return true;
        }
        return false;
    }

}