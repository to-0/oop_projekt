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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Login;
import model.Objednavka;
import pouzivatelia.Pouzivatel;

import java.util.ArrayList;

public class LoginController {
	/*@FXML
	Label message;
	Application view;
	@FXML
	TextField loginfield;
	@FXML
	PasswordField passfield;*/
	Scene login_scene;
	Stage stage;
	Pouzivatel  user;
	public void tryLogin(ActionEvent e,String nick, String pass, Label message) throws Exception{
		user = Databaza.find_user(nick,pass);
		if (user==null){
			message.setText("Zle heslo");
			return;
		}
		ArrayList<Objednavka> objednavky = user.getObjednavky();
		App.setUser(user);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/home.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
		Parent root = loader.load();
		HomeController controller = loader.<HomeController>getController();
		controller.setData(user,objednavky);
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setTitle("TomOffice");
		stage.setScene(new Scene(root, 400,500));
		message.setText(user.getMeno());
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
	public void handleLogin(){

	}
    

}