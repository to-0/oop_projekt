package application;


import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	Application view;
	public Controller(Application view) {
		this.view = view;
	}
	public void test(Scene scene) {
		System.out.println("Does this work?");
		
	}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
    }
    

}