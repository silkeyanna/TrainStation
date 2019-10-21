package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

// SILKE: import JDBC library: C:\Users\silke\Dropbox\RUC\Software Development\JDBC jar

public class connection {

<<<<<<< HEAD
    public static void main(String[] args) {
=======
    public ResultSet getRoute(int start, int end, float time,Connection conn)
            throws SQLException {
        String query = "select
        Statement stmt = null;
        ResultSet res = null;
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);
        return res;
    }
    public Connection connect(){
>>>>>>> fb430a13677e83546131baf30a7b90b4cad3f3f1
        Connection conn = null;
        try {
            //SILKE --> "jdbc:sqlite:C:/Users/silke/TrainSchedule.db"
            //JUAN --> "jdbc:sqlite:C:/Users/ juanb/desktop/Trains2.db"

            String url = "jdbc:sqlite:C:/Users/silke/TrainSchedule.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Got it!");
            return conn;
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
