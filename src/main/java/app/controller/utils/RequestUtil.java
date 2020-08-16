package app.controller.utils;


import io.javalin.http.Context;

public class RequestUtil
{
    public static String getQueryLoginRedirect(Context ctx)
    {
        return ctx.queryParam("loginRedirect");
    }

    public static String getSessionCurrentUser(Context ctx)
    {
        return (String) ctx.sessionAttribute("currentUser");
    }

    public static String getCurrentUserType(Context ctx)
    {
        return (String) ctx.sessionAttribute("currentUserType");
    }
    
    public static String getCurrentUserProcoId(Context ctx)
    {
        return (String) ctx.sessionAttribute("currentUserProcoId");
    }
}
