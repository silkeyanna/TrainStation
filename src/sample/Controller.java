package sample;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;


public class Controller {

    TrainModel m = TrainModel.getInstance(); //make a model object when you create the controller
    private connection MyConnection = new connection();
    String[] trainStations = connection.getRoute();


    @FXML //loads fxml file which generates objects for objects that appear in window and assign objects here as well
    ComboBox stat1;
    @FXML
    ComboBox stat2;
    @FXML
    TextField res;
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

        for(int i=0;i<trainStations.length;i++){
            stat1.getItems().add(trainStations[i]);
            stat2.getItems().add(trainStations[i]);
        }
        //configures the Spinner with values 0-24
        SpinnerValueFactory<Double> svf = new SpinnerValueFactory.DoubleSpinnerValueFactory(00.00, 24.00);
        hour.setValueFactory(svf);
        SpinnerValueFactory<Double> svf2 = new SpinnerValueFactory.DoubleSpinnerValueFactory(00.00, 59.00);
        minutes.setValueFactory(svf2);
    }

    @FXML
    public void routeHandler(ActionEvent e) throws SQLException {
        System.out.println("find route");
        double time = hour.getValue() + minutes.getValue();
        res.setText(m.findRoute(String.valueOf(stat1.getSelectionModel().getSelectedItem()),String.valueOf(stat2.getSelectionModel().getSelectedItem()),String.valueOf(time)));
    }
}

class TrainModel{ //shouldnt know anything about the GUI world
    private TrainModel(){ //private because it should be a singleton!
    } //no body because it is a constructor

    static TrainModel inst;
    static TrainModel getInstance(){if (inst==null) inst=new TrainModel(); return inst;} //only one object of trainmodel class will be created this way

    String findRoute(String stat1,String stat2, String time) throws SQLException {

        String[] result=connection.CalculateRoute(stat1,stat2,time);


        return  "First train from " + stat1+ "\n to " + stat2 + " at "+ result[0];
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

    @FXML
    ComboBox stat3; //not working

    //create a constructor for the controller
    Controller(){
        for(String s: TrainModel.getInstance().getStations()){ //this will give the model its objects
            //stat3.getItems().add(s); //returns list with all the stations in it?
        }
    }


    @FXML
    public void routeHandleer (ActionEvent e) {
    res.setText(m.findRoute(stat1.getText(),stat2.getText(), time.getText()));
    }
     */
