package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;



public class MySQLContactsDao implements Contacts {

    private Connection conn;

    public MySQLContactsDao() throws SQLException {


        Config config = new Config();

        DriverManager.registerDriver(new Driver());

        this.conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );
    }

    @Override
    public List<Contact> getContacts() {
        List<Contact> output = new ArrayList<>();

        String query = "SELECT * FROM contacts";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                output.add(
                        new Contact(
                                rs.getLong("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("phone_number")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;  // returns the List<Contact> of all the contacts we imported from MySQL DB
    }

    @Override
    public long saveContact(Contact contact) {



        // INSERT INTO contacts ('first_name, last_name, phone_number) VALUES ('casey', 'friday', '212212121');

        long newlyCreatedUserId = 0;


        String addContactQuery = String.format("INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('%s', '%s', '%s')",

                contact.getFirstName(),
                contact.getLastName(),
                contact.getPhoneNumber()
        );

        try {
            Statement stmt = conn.createStatement();
            // NOW execute the SQL query
            stmt.executeUpdate(addContactQuery, Statement.RETURN_GENERATED_KEYS);


            ResultSet ks = stmt.getGeneratedKeys();
            if(ks.next()) {
                newlyCreatedUserId = ks.getLong(1);
                // Check to see if the id was returned, or if insertedRowId is still at its defoult of "0"
                if( newlyCreatedUserId != 0) {
                    contact.setId(newlyCreatedUserId);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newlyCreatedUserId;
    }


    @Override
    public void deleteContactById(long id) {

        String query = String.format("DELETE FROM contacts WHERE id = %d", id);


        try {
            Statement stmt = conn.createStatement();
            boolean success = stmt.execute(query);

            if(success) {
                System.out.println("Contact has been deleted");
            } else {
                System.out.println("Contact was not deleted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Contact getContactById(long id) {

        Contact returnContact = new Contact();
        String query = String.format("SELECT * FROM contacts WHERE id = %d", id);


        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if(rs.next()) {
                returnContact.setId(id);
                returnContact.setFirstName(rs.getString("first_name"));
                returnContact.setLastName(rs.getString("last_name"));
                returnContact.setPhoneNumber(rs.getString("phone_number"));

            } else {
                System.out.println("Supplied user id found no contact matches");

            }


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return returnContact;

    }

}
