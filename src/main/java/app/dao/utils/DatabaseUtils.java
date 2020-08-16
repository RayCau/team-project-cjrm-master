package app.dao.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This class connects you to the database.
 * When you import the database, you should create it under the name "imbd",
 * with user "root" and database password "root1".
 *
 * If you know about DB password encryption and want to secure this a little bit
 * more, go ahead. But it is not required.
 */
public class DatabaseUtils {



    public static Connection connectToDatabase() throws Exception {
        // creates a Connection object
        Connection connection = null;

        try {
            // Prepare the information to connect (hard-coded)
        	
        	/* Please leave this here because I need to change the url to this for it to access the database (Raymond)
        	*String url = "jdbc:mysql://localhost:3306/imdb?useLegacyDatetimeCode=false&serverTimezone=UTC";
        	*String url = "jdbc:mysql://localhost:3306/imdb";
            */
//        	String url = "jdbc:mysql://localhost:3306/imdb";
        	String url = "jdbc:mysql://localhost:3306/imdb?useLegacyDatetimeCode=false&serverTimezone=UTC";
            String user = "root";
            //this is root123! because the password field wont take root1
            String password = "root123!";

            // Connect through the Driver
            connection = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid.");
            ex.printStackTrace();
        }

        // Return what you got
        return connection;
    }



    public static void closeConnection(Connection connection) {
        if(connection != null) {
            try {
                connection.close();
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }




}
