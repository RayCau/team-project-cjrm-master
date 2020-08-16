package app.model;

import java.sql.Date;





public class UserReview {
    private int reviewID;
    private String username;
    private String review;
    private int rating;
    private Date date;






    public UserReview(String r, int v) {
        review = r;
        rating = v;
        date = new Date(System.currentTimeMillis());
    }


    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public int getReviewID() {
    	return reviewID;
    }
   
    public void setReviewID(int reviewID) {
    	this.reviewID = reviewID;
    }

    public String getReview() {
        return review;
    }
    

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }
}
