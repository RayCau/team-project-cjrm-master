package app.controller;

import app.controller.paths.Template;
import app.controller.paths.Web;
import app.controller.utils.RequestUtil;
import app.controller.utils.ViewUtil;
import app.model.Account;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;
import app.dao.AccountDAO;


public class LoginController
{
	private static Account currentAccount;
	
    public static Handler serveLoginPage = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("loggedOut", removeSessionAttrLoggedOut(ctx));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(ctx));
        ctx.render(Template.LOGIN, model);
    };

    public static Handler handleLoginPost = ctx ->
    {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if (!UserController.authenticate(getQueryUsername(ctx), getQueryPassword(ctx)))
        {
            model.put("authenticationFailed", true);
            ctx.render(Template.LOGIN, model);
        }
        else
        {
            ctx.sessionAttribute("currentUser", getQueryUsername(ctx));
            model.put("authenticationSucceeded", true);
            model.put("currentUser", getQueryUsername(ctx));

            // Determine if logged in user is admin
            currentAccount = AccountDAO.getUserByUsername(getQueryUsername(ctx));
            ctx.sessionAttribute("currentUserType", currentAccount.getAccountType());
            model.put("currentUserType", currentAccount.getAccountType());
            
            // Store PCo id of user for future use (eg edit show)
            ctx.sessionAttribute("currentUserProcoId", currentAccount.getProcoId());

            if (RequestUtil.getQueryLoginRedirect(ctx) != null)
            {
                ctx.redirect(RequestUtil.getQueryLoginRedirect(ctx));
            }
            ctx.render(Template.LOGIN, model);
        }
    };

    public static Handler handleLogoutPost = ctx ->
    {
        ctx.sessionAttribute("currentUser", null);
        
        // Added so users can't see pages like edit show and 
        // unapproved shows after logging out
        ctx.sessionAttribute("currentUserType", null);
        ctx.sessionAttribute("currentUserProcoId", null);
        
        ctx.sessionAttribute("isAdmin", null);
        ctx.sessionAttribute("loggedOut", "true");
        ctx.redirect(Web.LOGIN);
    };

    // The origin of the request (request.pathInfo()) is saved in the session so
    // the user can be redirected back after login
   /*public static Handler ensureLoginBeforeViewing = ctx -> {
        if (!ctx.path().startsWith("/someprefix")) {
            return;
        }
        if (ctx.sessionAttribute("currentUser") == null) {
            ctx.sessionAttribute("loginRedirect", ctx.path());
            ctx.redirect(Web.LOGIN);
        }
    };*/

    public static String getQueryUsername(Context ctx)
    {
        return ctx.formParam("username");
    }

    public static String getQueryPassword(Context ctx)
    {
        return ctx.formParam("password");
    }

    public static boolean removeSessionAttrLoggedOut(Context ctx)
    {
        String loggedOut = ctx.sessionAttribute("loggedOut");
        ctx.sessionAttribute("loggedOut", null);
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Context ctx)
    {
        String loginRedirect = ctx.sessionAttribute("loginRedirect");
        ctx.sessionAttribute("loginRedirect", null);
        return loginRedirect;
    }
}
