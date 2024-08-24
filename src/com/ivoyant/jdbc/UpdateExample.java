package com.ivoyant.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateExample {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";
        String query = "UPDATE college SET password=456 WHERE id =2";

        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(url, un, pass);
            System.out.println("connection established");
            stmt = con.createStatement();
            int i = stmt.executeUpdate(query);
            if(i>0)
            {
                System.out.println(i+"row Updated");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

