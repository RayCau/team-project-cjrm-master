package app.controller;

import java.util.Map;

import app.controller.paths.Template;
import app.controller.utils.ViewUtil;
import app.model.Person;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class PersonpageController {
	
	private static Person person;
	
	public static Handler servePersonpage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        createPersonPage(ctx, model);
        ctx.render(Template.PERSONPAGE, model);
    };
    
    private static void createPersonPage(Context ctx, Map model) {
    	String modelKeys[] = {"personId", "fullname", "role", "bio", "birthdate"};
/*    	//To access the post information, ctx.formParam(<name field of attribute in signup.vm>) is used.
    	String searchField = ctx.formParam("personSearch");
    	//create a new person using the personDAO
    	/*Person person = PersonDAO.getPersonByName(searchTerm);
*/    	
    	//If there is already a previous personPage in the model, reset it as the most recent search
    	if(person != null) {
    		if(model.containsKey(modelKeys[0]) && model.containsKey(modelKeys[1]) && model.containsKey(modelKeys[2]) && model.containsKey(modelKeys[3])) {
        		model.get(modelKeys[0]).equals(person.getPersonId());
        		model.get(modelKeys[1]).equals(person.getFullName());
        		model.get(modelKeys[2]).equals(person.getRole());
        		model.get(modelKeys[3]).equals(person.getBio());
        		model.get(modelKeys[4]).equals(person.getBirthdate());

        	} else {
        		model.put(modelKeys[0], person.getPersonId());
        		model.put(modelKeys[1], person.getFullName());
        		model.put(modelKeys[2], person.getRole());
        		model.put(modelKeys[3], person.getBio());
        		model.put(modelKeys[4], person.getBirthdate());
        	}
    	}
    }
    
    public static void setPerson(Person p) {
    	person = p;
    }
}
