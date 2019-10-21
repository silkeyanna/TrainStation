package sample;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class Controller {

    TrainModel m= TrainModel.getInstance(); //make a model object when you create the controller
    @FXML
    TextField stat1;
    @FXML
    TextField stat2;
    @FXML
    ComboBox stat3;
    @FXML
    TextField time;
    @FXML
    TextField res;


    @FXML
    public void initialize(){
        // initialize is called by javafx after the fxml file is read and gui objects are created
        // this cannot be done in the constructor because that happens before FXML loading
        for(String s: TrainModel.getInstance().getStations()){
            stat3.getItems().add(s);
        }
    }

    @FXML
    public void routeHandler(ActionEvent e){
        System.out.println("find route");
        res.setText(m.findRoute(stat1.getText(),stat2.getText(),time.getText()));
    }
}

class TrainModel{ //shouldnt know anything about the GUI world
    private TrainModel(){ //private because it should be a singleton!
    } //no body because it is a constructor

    static TrainModel inst;
    static TrainModel getInstance(){if (inst==null) inst=new TrainModel(); return inst;} //only one object of trainmodel class will be created this way


    String[] getStations() {String[] s = {"Kobenhavn", "Roskilde", "Odense"}; return s;}
    String findRoute(String stat1, String stat2, String time){return "route from" + stat1+ "\n to " + stat2 + " at "+ time;}
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

     */
