package application;
	
import java.io.IOException;

import databaza.Databaza;
import gui.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pouzivatelia.Manazer;
import pouzivatelia.Pouzivatel;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	Pouzivatel user;
	Scene scene2;
	Controller control;
	@Override
	public void start(Stage primaryStage) {
		try {
			Databaza.init();
			control = new Controller();
			Label l1 = new Label("Meno");
			Label l2 = new Label("Heslo");
			Label message = new Label();
			TextField meno = new TextField();
			PasswordField heslo = new PasswordField();
			Button button = new Button("Login");
			VBox layout = new VBox();
			button.setOnAction(e->{
				try {
					control.tryLogin(e,meno.getText(),heslo.getText(),message);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}); 
			Scene scene = new Scene(layout,400,400);
			layout.getChildren().addAll(l1,meno,l2,heslo,button,message);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

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
	}*/
	@Override
	public void stop(){
		System.out.println("Stage is closing");
		Databaza.serializuj_objednavky();
		Databaza.serializuj_pouzivatelov();
	}
}
