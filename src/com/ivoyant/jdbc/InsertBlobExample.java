package com.ivoyant.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertBlobExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";
        String insertQuery = "INSERT INTO images (id, name, data) VALUES (?, ?, ?)";

        try {
             Connection conn = DriverManager.getConnection(url, un, pass);
             PreparedStatement pstmt = conn.prepareStatement(insertQuery);
             FileInputStream fis = new FileInputStream("C:\\Users\\RAHUL\\OneDrive\\Pictures\\Screenshots\\Screenshot (1).png");

            pstmt.setInt(1, 1);
            pstmt.setString(2, "Sample Image");

            pstmt.setBinaryStream(3, fis, fis.available());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Image was inserted successfully!");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}

