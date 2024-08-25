package com.ivoyant.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class BatchProcessingExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";

        Connection con = null;
        Scanner scanner = new Scanner(System.in);

        try {
            con = DriverManager.getConnection(url, un, pass);
            con.setAutoCommit(false);
            String insertSQL = "INSERT INTO college (id, username, email, password) VALUES (?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL);

            System.out.print("Enter the number of users to add: ");
            int userCount = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < userCount; i++) {
                System.out.println("Enter details for User " + (i + 1) + ":");

                System.out.print("User ID: ");
                int userId = scanner.nextInt();
                scanner.nextLine();

                System.out.print("User Name: ");
                String userName = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Password: ");
                String userPassword = scanner.nextLine();

                pstmt.setInt(1, userId);
                pstmt.setString(2, userName);
                pstmt.setString(3, email);
                pstmt.setString(4, userPassword);

                pstmt.addBatch();
            }

            int[] updateCounts = pstmt.executeBatch();

            con.commit();
            System.out.println("Batch executed successfully!");

            System.out.println("Rows affected: " + updateCounts.length);

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                    System.out.println("Batch processing rolled back due to an error.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                scanner.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}

