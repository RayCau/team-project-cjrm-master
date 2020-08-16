package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.Show;
import app.model.UserReview;

public class ShowDAO
{
	public static final String SALT = "$2a$10$h.dl5J86rGH7I8bD9bZeZe";

	public static List<Show> getShowsByTitle(String showtitle)
	{
		// Fish out the results
		List<Show> shows = new ArrayList<>();

		try
		{
			// Here you prepare your sql statement
			String sql = "SELECT * FROM `imdb`.`show` WHERE show_title " + "LIKE \"%" + showtitle + "%\" " + "AND isApproved = 1";

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			ResultSet result = connection.createStatement().executeQuery(sql);

			// If you have multiple results, you do a while
			while(result.next())
			{
				shows.add
				(
					new Show(result.getString("showid"),
							result.getString("proco_id"),
							result.getString("show_title"),
							result.getString("genre"),
							result.getString("year"),
							result.getString("length"),
							result.getString("movie_or_series"),
							result.getString("isApproved"))
				);
			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// Return results as list of shows
		return shows;
	}
	
	public static Show getShowById(String showId)
	{
		Show show = null;

		try
		{
			String sql = "SELECT * FROM `imdb`.`show` WHERE showid = " + showId;

			Connection connection = DatabaseUtils.connectToDatabase();
			ResultSet result = connection.createStatement().executeQuery(sql);

			while(result.next())
			{
				show = new Show(result.getString("showid"),
								result.getString("proco_id"),
								result.getString("show_title"),
								result.getString("genre"),
								result.getString("year"),
								result.getString("length"),
								result.getString("movie_or_series"),
								result.getString("isApproved"));
			}

			DatabaseUtils.closeConnection(connection);
			
			// Return result as a show
			return show;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Show> getApprovedShows()
	{
		// Fish out the results
		List<Show> shows = new ArrayList<>();

		try
		{
			// Here you prepare your sql statement
			String sql = "SELECT * FROM `imdb`.`show` WHERE isApproved = 1";

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			ResultSet result = connection.createStatement().executeQuery(sql);

			// If you have multiple results, you do a while
			while(result.next())
			{
				shows.add
						(
								new Show(result.getString("showid"),
										result.getString("proco_id"),
										result.getString("show_title"),
										result.getString("genre"),
										result.getString("year"),
										result.getString("length"),
										result.getString("movie_or_series"),
										result.getString("isApproved"))
						);
			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// Return results as list of shows
		return shows;
	}
	
	public static List<Show> getApprovedShows(String procoId)
	{
		List<Show> shows = new ArrayList<>();

		try
		{
			String sql = "SELECT * FROM `imdb`.`show` WHERE isApproved = 1 "
					+ "AND proco_id = " + procoId;

			Connection connection = DatabaseUtils.connectToDatabase();
			ResultSet result = connection.createStatement().executeQuery(sql);

			while(result.next())
			{
				shows.add
						(
								new Show(result.getString("showid"),
										result.getString("proco_id"),
										result.getString("show_title"),
										result.getString("genre"),
										result.getString("year"),
										result.getString("length"),
										result.getString("movie_or_series"),
										result.getString("isApproved"))
						);
			}

			DatabaseUtils.closeConnection(connection);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// Return results as list of shows
		return shows;
	}

	public static List<Show> getUnapprovedShows()
	{
		// Fish out the results
		List<Show> shows = new ArrayList<>();

		try
		{
			// Here you prepare your sql statement
			String sql = "SELECT * FROM `imdb`.`show` WHERE isApproved = 0";

			// Execute the query
			Connection connection = DatabaseUtils.connectToDatabase();
			ResultSet result = connection.createStatement().executeQuery(sql);

			// If you have multiple results, you do a while
			while(result.next())
			{
				shows.add
						(
								new Show(result.getString("showid"),
										result.getString("proco_id"),
										result.getString("show_title"),
										result.getString("genre"),
										result.getString("year"),
										result.getString("length"),
										result.getString("movie_or_series"),
										result.getString("isApproved"))
						);
			}

			// Close it
			DatabaseUtils.closeConnection(connection);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// Return results as list of shows
		return shows;
	}
	
	public static List<Show> getUnapprovedShows(String procoId)
	{
		List<Show> shows = new ArrayList<>();

		try
		{
			String sql = 
				"SELECT * FROM `imdb`.`show` WHERE isApproved = 0 AND "
				+ "proco_id = " + procoId;

			Connection connection = DatabaseUtils.connectToDatabase();
			ResultSet result = connection.createStatement().executeQuery(sql);

			while(result.next())
			{
				shows.add
						(
								new Show(result.getString("showid"),
										result.getString("proco_id"),
										result.getString("show_title"),
										result.getString("genre"),
										result.getString("year"),
										result.getString("length"),
										result.getString("movie_or_series"),
										result.getString("isApproved"))
						);
			}

			DatabaseUtils.closeConnection(connection);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		// Return results as list of shows
		return shows;
	}

	public static boolean addUnapprovedShow(int productionID, String title, String genre, String year, int length, boolean isMovie)
	{
		try
		{
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();

			//Get the number of shows in the database and increase it by 1 to get the next show id.
			int showId = findNumberOfShows(connection) + 1;
			int movieShow = 0;

			if(isMovie)
			{
				movieShow = 1;
			}
			else
			{
				movieShow = 0;
			}

			String sql = "INSERT INTO `show` VALUES (" + showId + ", " + productionID + ", '" + title + "', '" + genre + "', '" + year + "', " + length + ", " + movieShow + ", " + 0 + ");";

			statement.executeUpdate(sql);
			DatabaseUtils.closeConnection(connection);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
	
	public static int findNumberOfShows(Connection connection)
	{
		int numShows = 0;
		String sql = "SELECT showid FROM `imdb`.`show`;";
		ResultSet result;
		
		try
		{
			Statement statement = connection.createStatement();
			result = statement.executeQuery(sql);
			
			while(result.next())
			{
				numShows++;
			}
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return numShows;
	}

	public static boolean approveShow(int showId)
	{
		try
		{
			Connection connection = DatabaseUtils.connectToDatabase();
			Statement statement = connection.createStatement();

			String sql = "UPDATE imdb.show SET isApproved=1 WHERE showid=" + showId;

			statement.executeUpdate(sql);
			DatabaseUtils.closeConnection(connection);
			return true;

		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	//REMOVE SHOW METHOD
		public static boolean removeShow(int showId)
		{
			try
			{
				Connection connection = DatabaseUtils.connectToDatabase();
				Statement statement = connection.createStatement();

				String sql = "DELETE FROM imdb.show WHERE showid = " + showId;

				statement.executeUpdate(sql);
				DatabaseUtils.closeConnection(connection);
				return true;

			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
		
		public static boolean editShow(int showId, int productionID, String title, String genre, String year, int length, boolean isMovie)
		{
			try
			{
				Connection connection = DatabaseUtils.connectToDatabase();
				Statement statement = connection.createStatement();
				
				int movieShow = 0;
				if(isMovie)
				{
					movieShow = 1;
				}
				else
				{
					movieShow = 0;
				}
				
				String sql = "UPDATE `imdb`.`show` SET proco_id=" + productionID + ", show_title='" + title + "', genre='" + genre + "', year=" + year + ", length=" + length + ", movie_or_series=" + movieShow + " WHERE showid=" + showId;

				statement.executeUpdate(sql);
				DatabaseUtils.closeConnection(connection);
				return true;

			}
			catch (Exception e)
			{
				e.printStackTrace();
				return false;
			}
		}
}
