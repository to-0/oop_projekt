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
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Objednavka;
import pouzivatelia.Pouzivatel;

import java.util.ArrayList;

/**
 * Controller pre login scenu.
 */

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
	//toto nepouzivam uz nahradene nizsie handlelogin, iba ked robil gui manualne cez class nie cez fxml subor
	public void tryLogin(ActionEvent e,String nick, String pass, Label message) throws Exception{ //handlovanie buttonu ked kliknem login
		p = Databaza.find_user(nick,pass); //skusim najst takeho pouzivatela
		if (p ==null){ //ak neexistuje
			message.setText("Zle heslo alebo meno");
			return;
		}
		ArrayList<Objednavka> objednavky = p.getObjednavky(); //zoberiem aj jeh objednavky
		//App.setUser(p);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/home.fxml")); //nacitam home scenu
		//Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
		Parent root = loader.load();
		HomeController controller = loader.<HomeController>getController();
		controller.setData(p,objednavky); //poslem pouzivatela a objednavky controlleru cez set data funkciu
		Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
		stage.setTitle("TomOffice");
		stage.setScene(new Scene(root, 400,500));
		message.setText(p.getMeno());
		System.out.println("OK");
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