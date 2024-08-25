package com.ivoyant.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionControlExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc1";
        String un = "root";
        String pass = "rahul";

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, un, pass);

            con.setAutoCommit(false);

            String withdrawSQL = "UPDATE Account SET balance = balance - ? WHERE account_id = ?";
            String depositSQL = "UPDATE Account SET balance = balance + ? WHERE account_id = ?";

            PreparedStatement withdrawStmt = con.prepareStatement(withdrawSQL);
            PreparedStatement depositStmt = con.prepareStatement(depositSQL);

            withdrawStmt.setDouble(1, 500.0);
            withdrawStmt.setInt(2, 1);

            depositStmt.setDouble(1, 500.0);
            depositStmt.setInt(2, 2);

            int i = withdrawStmt.executeUpdate();
            int j = depositStmt.executeUpdate();
            if(i>0 && j>0){
                con.commit();
                System.out.println("Transaction committed successfully!");
            }else {
                con.rollback();
                System.out.println("Transaction rolled back due to partial failure.");
            }


        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                    System.out.println("Transaction rolled back due to an error.");
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }
}

