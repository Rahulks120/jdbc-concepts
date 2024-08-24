package com.ivoyant.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStmtDeleteExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";

        String deleteQuery = "DELETE FROM college WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the student ID to delete: ");
            int stdId = scanner.nextInt();

            conn = DriverManager.getConnection(url, un, pass);

            pstmt = conn.prepareStatement(deleteQuery);

            pstmt.setInt(1, stdId);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("student with ID " + stdId + " was deleted successfully!");
            } else {
                System.out.println("No student found with ID " + stdId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
