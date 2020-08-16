package app.controller.utils;

import app.controller.paths.Template;
import io.javalin.http.Context;
import io.javalin.http.ErrorHandler;
import java.util.HashMap;
import java.util.Map;

public class ViewUtil
{
    public static Map<String, Object> baseModel(Context ctx)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("currentUser", RequestUtil.getSessionCurrentUser(ctx));
        model.put("currentUserType", RequestUtil.getCurrentUserType(ctx));
        model.put("currentUserProcoId", RequestUtil.getCurrentUserProcoId(ctx));
        return model;
    }

    public static ErrorHandler notFound = ctx ->
    {
        ctx.render(Template.NOT_FOUND, baseModel(ctx));
    };

}
