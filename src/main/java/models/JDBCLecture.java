package models;

import java.sql.*;

import com.mysql.cj.jdbc.Driver;


public class JDBCLecture {
    public static void main(String[] args) throws SQLException {

        Config config = new Config();


        // Set up the database driver, and make a connection
        DriverManager.registerDriver(new Driver());

        // Get a connection object
        Connection conn = DriverManager.getConnection(
                config.getUrl(),
                config.getUsername(),
                config.getPassword()
        );

        // ********** Create a Statement Object **********
        Statement stmt = conn.createStatement();

        // ********** Execute a query **********
        // create a query string to ght everything in the contacts table
        String contactsQuery = "SELECT * FROM contacts";

        ResultSet rs = stmt.executeQuery(contactsQuery);

        // display whats in the result set (rs)
//        rs.next();
//        System.out.println(rs.getString(1));

        while (rs.next() ) {
            System.out.println(rs.getString("first_name") + " " + rs.getString("last_name") + " " + rs.getString("phone_number"));
        }

        // If we want to add a row to our database, we'll:
            // 1: Create a contact Object (Bean)
            // 2: Create an SQL Query to insert that Contact object into our database
            // 3: To add to our Dao - instantiate ContactListDao, and use the saveContact() method

        // this will allow us to use the method defined in our DAO


        Contacts clDao = DaoFactory.getContactsDao();

        // Instantiate a new Contact Object
        Contact casey = new Contact(
                "Casey",
                "Friday",
                "2105557777"
        );

        long newContactId = clDao.saveContact(casey);
        Contact newlyCreatedContact = clDao.getContactById(newContactId);


        // INSERT INTO contacts ('first_name, last_name, phone_number) VALUES ('casey', 'friday', '212212121');
        String addContactQuery = String.format("INSERT INTO contacts (first_name, last_name, phone_number) VALUES ('%s', '%s', '%s')",
                newlyCreatedContact.getFirstName(),
                newlyCreatedContact.getLastName(),
                newlyCreatedContact.getPhoneNumber()
        );

        System.out.println("This is the query string we'll be sending to mySQL:\n");
        System.out.println(addContactQuery);

        // NOW execute the SQL query
        stmt.executeUpdate(addContactQuery, Statement.RETURN_GENERATED_KEYS);

        long insertedRowId = 0;
        ResultSet ks = stmt.getGeneratedKeys();
        if(ks.next()) {
            insertedRowId = ks.getLong(1);
            System.out.println("The ID of the newly inserted row is: " + ks.getLong(1));
        }
//        System.out.println("Before doing the mysql id check, " + newlyCreatedContact.getFirstName() + "'s id was: " + newlyCreatedContact.setId(insertedRowId));


        // Check to see if the id was returned, or if insertedRowId is still at its defoult of "0"
        if( insertedRowId != 0) {
            newlyCreatedContact.setId(insertedRowId);
        }

    }
}
