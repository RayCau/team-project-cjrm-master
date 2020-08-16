package app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.UserReview;

public class ReviewDAO {
	public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";

	public static List<UserReview> getReviewsByTitle(String showID) {
        // Fish out the results
        List<UserReview> reviews = new ArrayList<>();
        try {
            // Here you prepare your sql statement
            String sql = "SELECT reviewId, show_id, user_id, rating, review, date"
            		+ " FROM `imdb`.`user_review` WHERE show_id "
            		+ "LIKE \"%" + showID + "%\" ";

            // Execute the query
            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // If you have multiple results, you do a while
            while(result.next()) {
                reviews.add(
                  //Create a new show object
                  new UserReview(result.getString("review"), Integer.parseInt(result.getString("rating")))
                  );
            }

            // Close it
            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        // Return results as list of shows
        return reviews;
    }
	
	public static boolean addReview(int show_id, String user_id, int rating, String review, String date) {
		try {
			Connection connection = DatabaseUtils.connectToDatabase();
	        Statement statement = connection.createStatement();
			
			int numReviews = 0;
			String sql = "SELECT reviewId FROM `imdb`.`user_review`;";
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				numReviews++;
			}
			//reviewId, show_id, user_id, rating, review, date
			sql = "INSERT INTO `user_review` VALUES (" + (numReviews + 1) + "," + show_id + ", '" + user_id + "'," +  rating + ",'" + review + "', " + date + ");";
			
			statement.executeUpdate(sql);
			DatabaseUtils.closeConnection(connection);
			
			return true;

        }
        catch (Exception e) {
            return false;
        }
	}
}
