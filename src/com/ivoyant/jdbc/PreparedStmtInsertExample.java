package com.ivoyant.jdbc;

import java.sql.*;

public class PreparedStmtInsertExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";
        String query = "INSERT INTO `college` (`id`, `username`, `email`, `password`) VALUES (?, ?,? ,?)";

        Connection con = null;
        PreparedStatement pstmt= null;

        try {
            con = DriverManager.getConnection(url, un, pass);
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,4);
            pstmt.setString(2,"kishor");
            pstmt.setString(3,"kishor@gmail.com");
            pstmt.setString(4,"kishor");

            int i = pstmt.executeUpdate();
            if (i>0){
                System.out.println(i+" row inserted");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
