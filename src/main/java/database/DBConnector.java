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

        System.out.println("DBHelper: PostgreSQL JDBC Driver Registered!");

        Connection connection = null;

        try {

            connection = DriverManager.getConnection("jdbc:postgres://otgtfczhjzawoi:a11b6b36d7b03dc1424ff132543a425a8a7b26a74baeb922674368cde0eb72b0@ec2-52-202-185-87.compute-1.amazonaws.com:5432/d9q1c8e4t90ihu", "otgtfczhjzawoi", "a11b6b36d7b03dc1424ff132543a425a8a7b26a74baeb922674368cde0eb72b0");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println("DBHelper: The Database Version is " + rs.getString(1));
            }

        } catch (SQLException e) {

            System.out.println("DBHelper: Connection Failed! Check output console");
            e.printStackTrace();
            return null;

        }

        if (connection != null) {
            System.out.println("DBHelper: You have a database connection!");
        } else {
            System.out.println("DBHelper: Failed to make connection!");
        }

        return connection;
    }
}