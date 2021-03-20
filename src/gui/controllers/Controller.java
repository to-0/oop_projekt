package gui.controllers;


import application.App;
import databaza.Databaza;
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
import model.Objednavka;
import pouzivatelia.Pouzivatel;

import java.util.ArrayList;

public class Controller{
	/*@FXML
	Label message;
	Application view;
	@FXML
	TextField loginfield;
	@FXML
	PasswordField passfield;*/
	Pouzivatel  user;
	/*@FXML
	public void handleLoginButton(ActionEvent e) throws Exception{
		user = Databaza.find_user(loginfield.getText(),passfield.getText());
		if (user==null){
			message.setText("Zle heslo");
			return;
		}
		Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root, 400,500));
		System.out.println("OK");
	}*/
	public void tryLogin(ActionEvent e,String nick, String pass, Label message) throws Exception{
		user = Databaza.find_user(nick,pass);
		ArrayList<Objednavka> objednavky = user.getObjednavky();
		if (user==null){
			message.setText("Zle heslo");
			return;
		}
		App.setUser(user);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/home.fxml"));
		//Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
		Parent root = loader.load();
		HomeController controller = loader.<HomeController>getController();
		controller.setData(user,objednavky);
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root, 400,500));
		message.setText(user.getMeno());
		System.out.println("OK");
	}
    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("View is now loaded!");
    }*/
    

}