package application;
	
import java.io.IOException;

import databaza.Databaza;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import pouzivatelia.Manazer;
import pouzivatelia.Pouzivatel;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	Pouzivatel user;
	Scene scene2;
	Controller control;
	@Override
	public void start(Stage primaryStage) {
		control = new Controller(this);
		try {
			Databaza.init();
			user = new Manazer("Jozko Mrkvicka","00000","jozko@jozko.sk");
			VBox layout2 = new VBox();
			scene2 = new Scene(layout2,400,400);
			Label l3 = new Label("Logged in");
			layout2.getChildren().add(l3);
			
			
			Label l1 = new Label("Meno");
			Label l2 = new Label("Heslo");
			TextField meno = new TextField();
			PasswordField heslo = new PasswordField();
			Button button = new Button("Login");
			VBox layout = new VBox();
			Scene scene = new Scene(layout,400,400);
			layout.getChildren().addAll(l1,meno,l2,heslo,button);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			button.setOnAction(e->{
				control.test(scene);
				}
			); 
			
		
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/*@Override
	public void start(Stage primaryStage) {
		try {
			Databaza.init();
			user = new Manazer("Jozko Mrkvicka","00000","jozko@jozko.sk");
			VBox layout2 = new VBox();
			scene2 = new Scene(layout2,400,400);
			Label l3 = new Label("Logged in");
			layout2.getChildren().add(l3);
			
			
			Label l1 = new Label("Meno");
			Label l2 = new Label("Heslo");
			TextField meno = new TextField();
			PasswordField heslo = new PasswordField();
			Button button = new Button("Login");
			VBox layout = new VBox();
			button.setOnAction(e->{
				String nick = meno.getText();
				String pass = heslo.getText();
				if(user.getLogin().valid_login(nick, pass)) {
					primaryStage.setScene(scene2);
					primaryStage.show();
				}
			}); 
			Scene scene = new Scene(layout,400,400);
			layout.getChildren().addAll(l1,meno,l2,heslo,button);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}*/
	/*@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			primaryStage.setTitle("Help");
			primaryStage.setScene(new Scene(root, 300, 275));
	        primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public static void main(String[] args) {
		Databaza.init();
		Databaza.test();
		launch(args);
	}
	
}
