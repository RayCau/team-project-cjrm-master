package app;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.post;

import app.controller.*;
import app.controller.paths.Web;
import app.controller.utils.ViewUtil;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

public class Main
{
    public static void main(String[] args)
    {
        Javalin app = Javalin.create
                (config ->
                {
                    config.addStaticFiles("/public");
                    config.registerPlugin(new RouteOverviewPlugin("/routes"));
                }
                ).start(getHerokuAssignedPort());

        app.routes(() ->
        {
            // You will have to update this, to limit who can see the reviews
            // before(LoginController.ensureLoginBeforeViewing);

            get(Web.INDEX, IndexController.serveIndexPage);
            post(Web.INDEX, IndexController.handleIndexPost);

            get(Web.LOGIN, LoginController.serveLoginPage);
            post(Web.LOGIN, LoginController.handleLoginPost);
            post(Web.LOGOUT, LoginController.handleLogoutPost);

            get(Web.ACCOUNT, AccountController.serveAccountPage);
            
            // Add new actions here
            // Seeing pages (get) and sending information in forms (post)
            get(Web.SIGNUP, SignupController.serveSignupPage);
            post(Web.SIGNUP, SignupController.handleLoginPost);
            
            // Default showpage testing
            get(Web.SHOWPAGE, ShowpageController.serveShowpage);
            post(Web.SHOWPAGE, ShowpageController.handleShowPagePost);
            
            // Default personpage testing
            get(Web.PERSONPAGE, PersonpageController.servePersonpage);
            
            // Search Results
            get(Web.SEARCHRESULTS, SearchResultsController.serveSearchResultsPage);
            post(Web.SEARCHRESULTS, SearchResultsController.handleSearchResultsPost);
            
            // Adding new show
            get(Web.CREATESHOW, CreateShowController.serveCreateShowPage);
            post(Web.CREATESHOW, CreateShowController.handleCreateShowPagePost);

            // Applying for PCo/Critic
            get(Web.REQACCOUNTTYPE, ReqaccController.serveReqPage);
            post(Web.REQACCOUNTTYPE, ReqaccController.handleReqPost);

            // View Unapproved Shows
            get(Web.UNAPPROVEDSHOWS, UnapprovedShowsController.serveUnapprovedPage);
            post(Web.UNAPPROVEDSHOWS, UnapprovedShowsController.handleUnapprovedPost);
            
            // PCo selects a show to edit
            get(Web.SELECTSHOWEDIT, SelectShowEditController.serveSelectShowPage);
            post(Web.SELECTSHOWEDIT, SelectShowEditController.handleSelectShowPost);
            // PCo edits a show
            post(Web.EDITSHOW, EditShowController.handleEditShowPost);
            get(Web.EDITSHOW, EditShowController.serveEditShowPage);
            
            get(Web.REQUESTFORMS, UnapprovedRequestFormsController.serveUnapprovedRequestPage);
            post(Web.REQUESTFORMS, UnapprovedRequestFormsController.handleUnapprovedRequestFormsPost);
        });

        app.error(404, ViewUtil.notFound);
    }

    public static int getHerokuAssignedPort()
    {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null)
        {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 7000;
    }
}
