package app.model;

public class AccountRequestForm
{
    private String user_id;
    private int requestedType;
    private String reason;
   
    
   
    public AccountRequestForm(String user_id, String reason, int requestedType)
    {
       this.user_id = user_id;
       this.requestedType = requestedType;
       this.reason = reason;
    }

    public String getUserID() {
    	return this.user_id;
    }
    
    public int getRequestedType() {
    	return this.requestedType;
    }
    
    public String getReason() {
    	return this.reason;
    }
   
}
