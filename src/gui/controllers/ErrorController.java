package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class ErrorController extends AController{
    @FXML
    Label sprava;
    public void startError(String s){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/error-sprava.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            ErrorController controller = loader.<ErrorController>getController();
            controller.zobrazError(s);
            zobraz_okno(root,200,200);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void zobrazError(String s){
        sprava.setText(s);
    }
}
