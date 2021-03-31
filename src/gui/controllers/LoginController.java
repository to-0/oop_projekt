package gui.controllers;


import application.App;
import databaza.Databaza;
import gui.sceny.LoginScene;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Objednavka;
import pouzivatelia.Pouzivatel;

import java.util.ArrayList;

public class LoginController  extends AController {
	@FXML
	Label message;
	//Application view;
	@FXML
	TextField loginfield;
	@FXML
	PasswordField passfield;
	Scene login_scene;
	Stage stage;
	//Pouzivatel p;
	public void tryLogin(ActionEvent e,String nick, String pass, Label message) throws Exception{
		p = Databaza.find_user(nick,pass);
		if (p ==null){
			message.setText("Zle heslo");
			return;
		}
		ArrayList<Objednavka> objednavky = p.getObjednavky();
		App.setUser(p);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/home.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
		Parent root = loader.load();
		HomeController controller = loader.<HomeController>getController();
		controller.setData(p,objednavky);
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setTitle("TomOffice");
		stage.setScene(new Scene(root, 400,500));
		message.setText(p.getMeno());
		System.out.println("OK");
	}
	public void showLogin(ActionEvent e) throws Exception{
		LoginScene l = new LoginScene();
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		l.startLoginScenu(stage);
	}
	public void setScene_Stage(Stage s, Scene scene){
		this.login_scene = scene;
		this.stage = s;
	}
    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
    }*/
	public void handleLogin(ActionEvent e) throws Exception{
		p = Databaza.find_user(loginfield.getText(),passfield.getText());
		if (p ==null){
			message.setText("Zle heslo");
			return;
		}
		ArrayList<Objednavka> objednavky = p.getObjednavky();
		App.setUser(p);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/home.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
		Parent root = loader.load();
		HomeController controller = loader.<HomeController>getController();
		controller.setData(p,objednavky);
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setTitle("TomOffice");
		stage.setScene(new Scene(root, 800,500));
		message.setText(p.getMeno());
		System.out.println("OK");
	}
    

}