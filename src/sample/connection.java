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

public static String getStation(String Station) throws SQLException {
    String[] result = new String[7];
    String query="select StationID from TrainStation where City ="+"\""+Station+"\"";
    Statement stmt = null;
    ResultSet res = null;
    Connection conn = connect();
    stmt = conn.createStatement();
    res = stmt.executeQuery(query);
    int i=0;
    while (res.next()) {
        result[i] = res.getString(1);
        i++;
    }

    return result[0];
}


    public static String[] CalculateRoute(String DepartureStation, String endStation, String time) throws  SQLException{

        String[] result = new String[106];



        DepartureStation=getStation(DepartureStation);
        endStation=getStation(endStation);


      String query="SELECT *\n" +
              "FROM Departure AS start\n" +
              "JOIN\n" +
              "Departure AS finish ON start.TrainID = finish.TrainID\n" +
              "WHERE start.Time >= "+time+" AND\n" +
              "start.Time < finish.Time AND\n" +
              "start.StationID = "+DepartureStation+" AND\n" +
              "finish.StationID = "+endStation+"\n" +
              "ORDER BY Time";

         Statement stmt = null;
        ResultSet res = null;
        Connection conn = connect();
        stmt = conn.createStatement();
        res = stmt.executeQuery(query);

        int i = 0;
        while (res.next()) {
            result[i] = res.getString(1);
            System.out.println(result[i]);
            i++;
        }

       return result;
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