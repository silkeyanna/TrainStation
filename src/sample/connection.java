package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

// SILKE: import JDBC library: C:\Users\silke\Dropbox\RUC\Software Development\JDBC jar

public class connection {

  //  public static void main(String[] args) throws SQLException {
    //   Connection conn = connect();
     //  getRoute(conn);
   // }


    public static String[] CalculateRoute(double time, String DepartureStation, String endStation) throws  SQLException{
        String[] result = new String[7];
        String[] trains = new String[106];
        String query="select StationID from TrainStation where City ="+"\""+DepartureStation+"\"";
        Statement stmt = null;
        ResultSet res = null;
        Connection conn = connect();
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);

        int i = 0;

        while (res.next()) {
            result[i] = res.getString(1);
            i++;
        }
      query="select TrainID from Departure where StationID ="+"\""+result[0]+"\" and Time >"+time;
         stmt = null;
        res = null;
        conn = connect();
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);

        i = 0;

        while (res.next()) {
            trains[i] = res.getString(1);
            i++;
        }

    }
    public static String[] getRoute()
            throws SQLException {
        String[] result = new String[7];
        String query = "select City From TrainStation";
        Statement stmt = null;
        ResultSet res = null;
        Connection conn = connect();
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);
        int i = 0;

        while (res.next()) {
            result[i] = res.getString(1);
            i++;
        }
        System.out.println(result);
        return result;
    }

    public static Connection connect(){
        Connection conn = null;
        try {
            //SILKE --> "jdbc:sqlite:C:/Users/silke/TrainSchedule.db"
            //JUAN --> "jdbc:sqlite:C:/Users/ juanb/desktop/Trains2.db"

            String url = "jdbc:sqlite:C:/Users/juanb/desktop/Trains2.db";
            conn = DriverManager.getConnection(url);

            System.out.println("Got it!");
            return conn;
        } catch (SQLException e) {
            throw new Error("Problem", e);

        } finally {
            try {
                if (conn == null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}