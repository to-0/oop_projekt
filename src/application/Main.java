package application;
	
import databaza.Databaza;
import gui.controllers.LoginController;
import gui.sceny.LoginScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pouzivatelia.Pouzivatel;
import javafx.scene.Scene;

import java.io.IOException;


public class Main extends Application {
	
	Pouzivatel user;
	Scene scene2;
	LoginController control;
	@Override
	public void start(Stage primaryStage) {
		try { //nacitam databazu cize pouzivatelov a objednavky
			Databaza.init();
			//zapnem login scenu
			LoginScene l = new LoginScene();
			l.startLoginScenu(primaryStage);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void nacitaj_app(Stage primaryStage){

	}

	/*
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../gui/sceny/uvod.fxml"));
			primaryStage.setTitle("Help");
			primaryStage.setScene(new Scene(root, 300, 275));
	        primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Databaza.init();
		Databaza.test();
		launch(args);
	}
	@Override
	public void stop(){
		System.out.println("Stage is closing");
		Databaza.serializuj_objednavky();
		Databaza.serializuj_pouzivatelov();
	}*/
}
