package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.dao.AccountDAO;
import app.dao.AccountRequestFormDAO;
import app.model.Account;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ReqaccController {
	
    public static Handler serveReqPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        // You'll have to update the model... maybe here
        if(userAlreadySubmittedARequest(model)) {
        	model.put("cantSubmit", true);
        }
        ctx.render(Template.REQACCOUNTTYPE, model);
    };

    public static Handler handleReqPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        

        String reason = getReason(ctx);
        int requestedaccType = getRequestedAccType(ctx);
        String userName = model.get("currentUser").toString();
        Account currentAccount = AccountDAO.getUserByUsername(userName);
        
        //only general user or admin accounts can request another account type.
        if(Integer.parseInt(currentAccount.getAccountType()) < 2) {
        	if(!userAlreadySubmittedARequest(model)) {
            	AccountRequestFormDAO.addUserRequestForm(userName, reason, requestedaccType);           	
            }
        }      
        
        ctx.render(Template.REQACCOUNTTYPE, model);
    };
    
    private static boolean userAlreadySubmittedARequest(Map<String, Object> model) {
    	boolean canSubmitRequest = false;
    	String userName = model.get("currentUser").toString();
    	Account currentAccount = AccountDAO.getUserByUsername(userName);
    	if(currentAccount != null) {
        	int accType = Integer.parseInt(currentAccount.getAccountType());
        	if(accType < 2) {
        		canSubmitRequest = true;
        	}
        }
        
        if(!AccountRequestFormDAO.checkIfUsernameExists(userName)) {
        	canSubmitRequest = false;
        }
        
        return canSubmitRequest;
        
    }

    public static String getQueryAccountType(Context ctx)
    {
        return ctx.formParam("accountType");
    }

    public static String getQueryReason(Context ctx)
    {
        return ctx.formParam("reason");
    }
    
    public static int getRequestedAccType(Context ctx) {
        String selectedType = ctx.formParam("accountType");
        System.out.println(selectedType);
        
        if(selectedType.equals("critic")) {
        	return 2;
        } else if(selectedType.equals("pco")) {
        	return 3;
        }
        
        return 0;
    }
    
    public static String getReason(Context ctx) {
        return ctx.formParam("reason");
    }

}