package app.model;

public class ProductionCompany {
    private int id;
	private String name;

    public ProductionCompany(int i, String n) {
        id = i;
    	name = n;
    }
    
    public int getId() {
    	return id;
    }

    public String getName() {
        return name;
    }
}
