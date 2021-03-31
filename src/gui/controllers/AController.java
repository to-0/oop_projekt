package gui.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pouzivatelia.Pouzivatel;
//pretazenie v abstraktnej class a to v metode zobraz_okno
public abstract class AController {
    protected Pouzivatel p;
    public void zobraz_okno(Parent root,int x,int y){ //iba nove okno navyse, neprepnem scenu
        Stage stage = new Stage();
        stage.setTitle("TomOffice");
        stage.setScene(new Scene(root,x,y));
        stage.show();
    }
    public void zobraz_okno(Parent root,int x, int y,Stage stage){ //prepnem celu scenu
        stage.setTitle("TomOffice");
        stage.setScene(new Scene(root,500,500));
        stage.show();
    }
}
