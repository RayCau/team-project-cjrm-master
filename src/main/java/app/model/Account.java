package app.model;

public class Account
{
    private String firstName;
    private String lastName;
    private String address;
    private String username;
    private int accountType;
    private int procoId;
    /**
     * Only stores hashed passwords.
     */
    private String password;
    private String country;
    private String gender;
    private String email;
    
    public Account(String un, String p, int acctype, int procoId)
    {
        username = un;
        password = p;
        accountType = acctype;
        this.procoId = procoId;
    }

    public Account(String fn, String ln, String a, String c, String g, String email)
    {
        // TODO fill in here
        /* You should use this constructor when you are showing the account page,
        hence, the user is already logged in. Therefore, the username Should be used
        to fetch this information from the database. You may have to tweek some stuff
        here and there.
        You should NEVER show the current password in the form. NEVER!
        And if you want to change the password, you need to ask for current password as well.
         */
    }

    public void updateDetails(int option, String updatedDetail)
    {
        // TODO
        switch (option)
        {
            case(2): this.country = updatedDetail;
        }
    }

    public String getUsername()
    {
        return username;
    }


    public String getPassword()
    {
        return password;
    }

    public String getAccountType()
    {
        return Integer.toString(accountType);
    }
    
    public String getProcoId()
    {
    	return Integer.toString(procoId);
    }
}
