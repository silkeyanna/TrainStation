package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.sql.SQLException;

public class Controller {

    boolean done=false;
    TrainModel m = TrainModel.getInstance(); //make a model object when you create the controller
    private connection MyConnection = new connection();
    String[] trainStations = connection.getRoute();

    @FXML //loads fxml file which generates objects for objects that appear in window and assign objects here as well
    ComboBox stat1;
    @FXML
    ComboBox stat2;
    @FXML
    TextArea res1;
    @FXML
    Spinner<Double> hour;
    @FXML
    Spinner<Double> minutes;


    public Controller() throws SQLException {
    }


    @FXML
    public void initialize(){
        // initialize is called by javafx after the fxml file is read and gui objects are created
            // this cannot be done in the constructor because that happens before FXML loading

       // for(int i=0;i<trainStations.length;i++){
        if(done==false) {
            stat1.getItems().setAll(trainStations);
            stat2.getItems().setAll(trainStations);
            done=true;
        }
       // }
        //configures the hour Spinner with values 0-24
        SpinnerValueFactory<Double> svf = new SpinnerValueFactory.DoubleSpinnerValueFactory(00.00, 24.00);
        hour.setValueFactory(svf);
        //configures the Spinner with values 0-59
        SpinnerValueFactory<Double> svf2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(00.00, 59.00);
        minutes.setValueFactory(svf2);
    }

    @FXML
    public void routeHandler(ActionEvent e) throws SQLException {
        double time = hour.getValue() + minutes.getValue()/100;
        res1.setText(m.findRoute(String.valueOf(stat1.getSelectionModel().getSelectedItem()),String.valueOf(stat2.getSelectionModel().getSelectedItem()),String.valueOf(time)));
    }
}

class TrainModel{ //shouldnt know anything about the GUI world
    private TrainModel(){ //private because it should be a singleton!
    } //no body because it is a constructor

    static TrainModel inst;
    static TrainModel getInstance(){if (inst==null) inst=new TrainModel(); return inst;} //only one object of trainmodel class will be created this way

    String findRoute(String stat1,String stat2, String time) throws SQLException {

        String[] result=connection.CalculateRoute(stat1,stat2,time);
        String res="";
        if (result[0]==null) {
            if(stat1.equals(stat2)){
                res="Please, insert a valid route";
            }else {
                res = "There is no trains at this hour today";
            }

        }
        for (int i=0;i<result.length;i++){
            if(result[i]!=null) {
                res = res + "\n" + (i+1) + ": train from " + stat1 + " to " + stat2 + " at " + result[i];
            }
        }
        return res;

    }
}

    /*
    @FXML
    Button btn;

    @FXML
    TextField password;

    @FXML
    TextField txt;


    public void initialize() { //executed when GUI is ready
        btn.addEventHandler(ActionEvent.ACTION, new EventHandler<Event>() { //Ctrl Space Enter
            @Override
            public void handle(Event event) {
                if (password.getText().equals("admin")) { //equals because they are objects and objects can be complex to compare
                    txt.setVisible(true);
                }

            }
        });

    }

}
