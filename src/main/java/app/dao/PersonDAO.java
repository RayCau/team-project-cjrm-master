package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.Person;

public class PersonDAO {
	public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";

    public static List<Person> getPeopleByName(String fullname) {
        // Fish out the results
        List<Person> people = new ArrayList<>();
        try {
            // Here you prepare your sql statement
            String sql = "SELECT person_id, fullname, role, birthdate, bio "
            		+ "FROM `imdb`.`person` WHERE fullname "
            		+ "LIKE \"%" + fullname + "%\" ";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                people.add(
                  //Create a new person object
                  //Person(int id, String fn, String r, Date bd, String b)
                  new Person(result.getString("person_id"), 
                		  result.getString("fullname"), result.getString("role"), 
                		  result.getDate("birthdate"), result.getString("bio"))
                );
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Return results as list of people
        return people;
    }
}
