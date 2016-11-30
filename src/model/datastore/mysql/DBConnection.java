package model.datastore.mysql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The "DBConnection" class gets a connection to the database. DBConnection is
 * based on code taken from Professor John Phillip's program.
 *
 * @author Jason Whiting
 * @version 2016-11-30
 */
public class DBConnection {

    /**
     * Gets a connection to the database.
     * 
     * @return 
     */
    public static Connection getConnection() {

        Properties props = new Properties();
        Connection con = null;

        try {
            props.load(new FileInputStream("res/mysql/db.properties"));
            Class.forName(props.getProperty("DB_DRIVER_CLASS"));
            con = DriverManager.getConnection(
                    props.getProperty("DB_URL")
                    + "?user=" + props.getProperty("DB_USERNAME")
                    + "&password=" + props.getProperty("DB_PASSWORD")
                    + "&useSSL=" + props.getProperty("USE_SSL"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            System.out.println("DB error in getConnection()");
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        getConnection();
    }
}
