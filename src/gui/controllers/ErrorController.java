package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Controller chybnej hlášky, v prípade, že použivateľ vyberie zlý vstup.
 */
public class ErrorController extends AController{
    @FXML
    Label sprava;

    /**
     * Metóda slúži n načítanie scény error správa a následne zavolá vlastnú funkciu zobrazError
     * @param s
     */
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

    /**
     * Zobrazí nové okno so správou s
     * @param s správa chyby
     */
    public void zobrazError(String s){
        sprava.setText(s);
    }
}
