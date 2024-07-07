/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vidyalaya.Database;

import java.sql.*;

/**
 *
 * @author trishan9
 */
public class MySqlConnection implements Database {

    @Override
    public Connection openConnection() {
        try {
            String username = "avnadmin";
            String password = "AVNS_w4RtioYNzRH2i__jN9Z";
            String database = "defaultdb";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection;
            connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql-19277503-mailtotrishan-46a5.i.aivencloud.com:20011/" + database, username, password
            );
            if (connection == null) {
                System.out.println("Database connection fail");
            } else {
                System.out.println("Database connection success");
            }
            return connection;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Connection close");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int executeUpdate(Connection conn, String query) {
        try {
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(query);
            return result;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
}
