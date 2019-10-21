package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class connection {

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
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:/Users/ juanb/desktop/Trains2.db";
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
