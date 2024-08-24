package com.ivoyant.jdbc;

import java.sql.*;

public class DeleteExample {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";
        String query = "DELETE FROM college WHERE id =0";

        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(url, un, pass);
            System.out.println("connection established");
            stmt = con.createStatement();
            int i = stmt.executeUpdate(query);
            if(i>0)
            {
                System.out.println(i+"row deleted");
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
