package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class connection {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:/Users/silke/TrainSchedule.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Got it!");

        } catch (SQLException e) {
            throw new Error("Problem", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
