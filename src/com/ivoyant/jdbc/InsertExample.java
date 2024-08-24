package com.ivoyant.jdbc;

import java.sql.*;

public class InsertExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";
        String query = "INSERT INTO `college` (`id`, `username`, `email`, `password`) VALUES ('3', 'rakesh', 'rakesh@gamil.com', '123')";

        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(url, un, pass);
            System.out.println("connection established");
            stmt = con.createStatement();
            int i = stmt.executeUpdate(query);
            if (i>0)
            {
                System.out.println(i +" row inserted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
