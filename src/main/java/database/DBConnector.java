package database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnector {

    // This class uses the Java Database Connectivity API
    @SuppressWarnings("unused")
    public static Connection getConnection() {

        System.out.println("\nDatabase Connector: -------- PostgreSQL " + "JDBC Connection  ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("DBConnector: Check Where  your PostgreSQL JDBC Driver exists and " + "Include in your library path!");
            e.printStackTrace();
            return null;

        }

        System.out.println("DBConnector: PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            // Connection calls DB host, user, and password from Heroku environment variables for encapsulation
            connection = DriverManager.getConnection(System.getenv("DATABASE_URL"), System.getenv("DATABASE_USER"), System.getenv("DATABASE_PASSWORD"));
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println("DBConnector: The Database Version is " + rs.getString(1));
            }

        } catch (SQLException e) {

            System.out.println("DBConnector: Connection Failed! Check output console");
            e.printStackTrace();
            return null;

        }

        if (connection != null) {
            System.out.println("DBConnector: You have a database connection!");
        } else {
            System.out.println("DBConnector: Failed to make connection!");
        }

        return connection;
    }
}