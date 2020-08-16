package app.dao;

import app.dao.utils.DatabaseUtils;
import app.model.Account;
import org.eclipse.collections.impl.list.mutable.FastList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDAO
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

    public static Account getUserByUsername(String username)
    {
        List<Account> accounts = new ArrayList<>();

        try
        {
            String sql = "SELECT username, password, account_type, proco_id FROM account WHERE username ='" + username + "'";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next())
            {
                accounts.add(new Account(result.getString("username"), 
                						 result.getString("password"), 
                						 result.getInt("account_type"),
                						 result.getInt("proco_id")));
            }

            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // If there is a result
        if(!accounts.isEmpty())
        {
            return accounts.get(0);
        }
        // If we are here, something bad happened
        return null;
    }

    public static boolean checkIfUsernameExists(String name)
    {
        List<String> accounts = new ArrayList<>();

        try
        {
            String sql = "SELECT username FROM account WHERE username = '" + name + "'";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next())
            {
                String username = "username";
				accounts.add(result.getString(username));
            }

            DatabaseUtils.closeConnection(connection);
            if(accounts.isEmpty())
            	return false;
        }
        catch (Exception e)
        {	//none exist
            return false;
        }
        
        if(!accounts.get(0).equalsIgnoreCase(name))
        {
        	return false;
        }
        
        return true;
    }
    
    public static void updateUserAccountType(String username, int newAccountType)
    {
        try
        {
            String sql = "UPDATE imdb.account SET account_type = " + newAccountType + " WHERE username = '" + username + "';";

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
}
