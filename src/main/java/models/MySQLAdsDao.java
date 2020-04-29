package models;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public class MySQLAdsDao implements Ads {

    private Connection conn;

    public MySQLAdsDao() throws SQLException {


        Config config = new Config();

        DriverManager.registerDriver(new Driver());

        this.conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );
    }



    // ********** This lists all of the ads currently in the database **********
    @Override
    public List<Ad> all() {

        List<Ad> ads = new ArrayList<>();
        String query = "SELECT * FROM ads";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ads.add(
                        new Ad(
                                rs.getLong("id"),
                                rs.getString("title"),
                                rs.getString("description")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ads;
    }



    // *********** This inserts a new ad into the database and returns the new "Id" of the ad ************
    @Override
    public Long insert(Ad ad) {

        long newUserId = 0;

        String addNewAdQuery = String.format("INSERT INTO ads (title, description) VALUES ('%s', '%s')",

                ad.getTitle(),
                ad.getDescription()
        );

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(addNewAdQuery, Statement.RETURN_GENERATED_KEYS);
            ResultSet ks = stmt.getGeneratedKeys();

            if(ks.next()) {
                newUserId = ks.getLong(1);

                if(newUserId != 0) {
                    ad.setId(newUserId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newUserId;
    }
}
