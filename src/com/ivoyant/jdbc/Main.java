package com.ivoyant.jdbc;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";
        String query = "select*from employee";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, un, pass);
            System.out.println("connection established ");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getLong(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs !=null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}