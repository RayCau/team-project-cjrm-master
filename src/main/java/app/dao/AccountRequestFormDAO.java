package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.Account;
import app.model.AccountRequestForm;
import app.model.Show;

import org.eclipse.collections.impl.list.mutable.FastList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountRequestFormDAO
{
    public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";

    /**
     * Method to fetch users from the database.
     * You should use this as an example for future queries, though the sql statement
     * will change -and you are supposed to write them.
     *
     * Current user: caramel 6, password (the password is "password" without quotes)
     * @param username what the user typed in the log in form.
     * @return Some of the user data to check on the password. Null if there
     *         no matching user.
     */

    public static void addUserRequestForm(String user_id, String reason, int requestedAccType)
    {
        try
        {
        	//String sql = "INSERT INTO `show` VALUES (" + showId + ", " + productionID + ", '" + title + "', '" + 
        	//genre + "', '" + year + "', " + length + ", " + movieShow + ", " + 0 + ");";
        	
            String sql = "INSERT INTO `account_requestform` VALUES ('" + user_id + "', '" + reason + "', '" + requestedAccType + "');";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    
    public static AccountRequestForm getUserRequestFormByUserID(String user_id)
    {
    	List<AccountRequestForm> requestForms = new ArrayList<>();
        try
        {        	
            String sql = "SELECT * FROM `imdb`.`account_requestform` WHERE user_id = '" + user_id + "';";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = connection.createStatement().executeQuery(sql);
            
            while(result.next())
			{
				requestForms.add
						(
							new AccountRequestForm(result.getString("user_id"),
										result.getString("reason"),
										Integer.parseInt(result.getString("requested_acc_type")))
						);
			}
            
            DatabaseUtils.closeConnection(connection);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return requestForms.get(0);

    }
    
    public static void deleteRequestForm(String user_id) {    	
    	try
        {        	
            String sql = "DELETE FROM imdb.account_requestform WHERE user_id = '" + user_id + "';";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
           
            
            DatabaseUtils.closeConnection(connection);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    	
    }
    
    public static List<AccountRequestForm> getUserRequestForms()
    {
    	List<AccountRequestForm> requestForms = new ArrayList<>();
        try
        {        	
            String sql = "SELECT * FROM `imdb`.`account_requestform`;";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = connection.createStatement().executeQuery(sql);
            
            while(result.next())
			{
				requestForms.add
						(
							new AccountRequestForm(result.getString("user_id"),
										result.getString("reason"),
										Integer.parseInt(result.getString("requested_acc_type")))
						);
			}
            
            DatabaseUtils.closeConnection(connection);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return requestForms;
    }
    
    public static boolean checkIfUsernameExists(String username)
    {
    	boolean exists = false;
        try
        {
            String sql = "SELECT user_id FROM account_requestform WHERE user_id ='" + username + "'";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next())
            {
            	
                exists = true;
                
            }

            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return exists;
    }
}
