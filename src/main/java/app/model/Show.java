package app.model;

import app.dao.ReviewDAO;
import java.util.List;


public class Show
{
    private int showid;
    private int proco_id;
    private String showTitle;
    private String genre;
    private int year;
    private int length;
    private int movie_or_series;
    private int isApproved;

    private List<UserReview> userReviewList;
    private ProductionCompany productionCompany;

    private List<CreditsRoll> creditsRolls;

    public Show(String showId, String proco_id, String showTitle, String genre, String year, String length, String movie_or_series, String isApproved)
    {
    	this.showid = Integer.parseInt(showId);
    	this.proco_id = Integer.parseInt(proco_id);
    	this.showTitle = showTitle;
        this.genre = genre;
        this.year = Integer.parseInt(year);
        this.length = Integer.parseInt(length);
    	this.movie_or_series = Integer.parseInt(movie_or_series);
    	this.isApproved = Integer.parseInt(isApproved);
    	
    	//add reviews from database
    	this.userReviewList = ReviewDAO.getReviewsByTitle(showId);
    }
    

	public int getShowId()
    {
    	return this.showid;
    }
	
	public int getProcoId()
	{
		return proco_id;
	}
    
    public String getShowTitle()
    {
    	return this.showTitle;
    }
    
    public int getShowLength()
    {
    	return this.length;
    }

    public int getMovie_or_series()
    {
        return this.movie_or_series;
    }

    public int getIsApproved()
    {
        return this.isApproved;
    }

    public int getIsSeries()
    {
        if (this.movie_or_series == 1)
        {
            return 0;
        }
    	else return 1;
    }
    
    public String getShowGenre()
    {
    	return this.genre;
    }
    
    public int getShowYear()
    {
    	return this.year;
    }
    
    public void addReview(UserReview review)
    {
    	userReviewList.add(review);
    }
    
    public int getAverageRating()
    {
    	int ratingAvg = 0;
    	
    	if(!userReviewList.isEmpty())
    	{
			for(int i = 0; i < userReviewList.size(); i++)
			{
				ratingAvg += userReviewList.get(i).getRating();
			}
			ratingAvg /= userReviewList.size();
    	}
		
    	return ratingAvg;
    }
}
