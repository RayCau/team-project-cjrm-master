package app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.dao.utils.DatabaseUtils;
import app.model.ProductionCompany;

public class ProductionCompanyDAO
{
    public static List<ProductionCompany> getProcos()
    {
        List<ProductionCompany> procos = new ArrayList<>();

        try
        {
            String sql = "SELECT * FROM `production_company`";

            Connection connection = DatabaseUtils.connectToDatabase();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next())
            {
                procos.add(new ProductionCompany(result.getInt("proco_id"), 
                						 result.getString("proco_name")));
            }

            DatabaseUtils.closeConnection(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Return results as list of production companies
        return procos;
    }
}