package app.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.utils.DatabaseUtils;
import app.model.Account;
import app.dao.AccountDAO;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class SignupController {

	
	public static Handler serveSignupPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here

        ctx.render(Template.SIGNUP, model);
    };
    
    public static Handler handleLoginPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);

        // Check if the two passwords match. If they dont, add "dontMatch" to the model.
        if (!checkPasswordsMatch(ctx))
        {
            model.put("dontMatch", true);
            ctx.render(Template.SIGNUP, model); //Rerender the page with the updated information.
        }

        if (!checkAccountExists(ctx))
        {
            model.put("accountExists", true);
            ctx.render(Template.SIGNUP, model); //Rerender the page with the updated information.
        }

        ctx.render(Template.SIGNUP, model);
    };
    
    
    public static String getQueryUsername(Context ctx) {
        return ctx.formParam("username");
    }

    public static String getQueryPassword(Context ctx) {
        return ctx.formParam("password");
    }

    public static boolean checkAccountExists(Context ctx)
    {
        String accountName = ctx.formParam("username");

        if (AccountDAO.checkIfUsernameExists(accountName))
        {
            return true;
        }
        else return false;
    }

    public static boolean checkPasswordsMatch(Context ctx)
    {
    	//Method to check if the passwords from the POST form in signup.vm match
    	
    	//To access the post information, ctx.formParam(<name field of attribute in signup.vm>) is used.
    	String firstPassword = ctx.formParam("password");
    	String secondPassword = ctx.formParam("confirm_password");
    	
    	if(firstPassword.equals(secondPassword) && !firstPassword.equals("")) {
    		return true;
    	}
    	
    	return false;
    }

}
