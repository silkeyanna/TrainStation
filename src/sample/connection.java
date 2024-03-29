package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class connection {

    //static member holds only one instance of connection class
    private static connection connectionInstance;
    //prevents any other class from instantiating
    private connection(){}

    //provides global point of access
    public static connection getConnectionInstance(){
        if(connectionInstance == null){
            connectionInstance = new connection();
            System.out.println("Creating new connection instance");
        }
        return connectionInstance;
    }

    public static String getStation(String Station) throws SQLException { //This method is for selecting all the StationsId of the stations
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
    //This method is from calculating all the possibles routes from a station to another in a time, it returns a array with the times
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

            i++;
        }

        return result;
    }
    public static String[] getRoute() //This method is for getting all the names of the citys with the stationid
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

    public static Connection connect(){ //This method is for connecting to the database
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:TrainSchedule.db"; //relative path
            conn = DriverManager.getConnection(url);
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