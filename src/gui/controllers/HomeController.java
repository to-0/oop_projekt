package gui.controllers;

import application.App;
import gui.sceny.LoginScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Objednavka;
import pouzivatelia.Pouzivatel;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import application.Main;

public class HomeController {
    private Pouzivatel p;
    private ArrayList<Objednavka> p_objednavky;
    private ObservableList<Objednavka> doList;
    @FXML
    ListView<Objednavka> objednavky_list = new ListView<Objednavka>();
    @FXML
    Label message;
    @FXML
    public void initialize() {

    }
    public void setData(Pouzivatel u, ArrayList<Objednavka> objednavky_u){
        this.p = u;
        this.p_objednavky = new ArrayList<Objednavka>(objednavky_u);
        this.doList =  FXCollections.observableArrayList();
        message.setText(App.user.getMeno()); // Perfectly Ok here, as FXMLLoader already populated all @FXML annotated members.
        for(Objednavka o:p.getObjednavky()){
           this.doList.add(o);
            //objednavky_list.getItems().add(o.toList());
        }
       // objednavky_list  = new ListView<String>();
        objednavky_list.getItems().addAll(this.doList);
    }
    public void zobrazObjednavku(){
       try{
           int index = objednavky_list.getSelectionModel().getSelectedIndex();
           Objednavka o  = this.p_objednavky.get(index);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/detail_objednavky.fxml"));
           Parent root = loader.load();
           DetailObjednavky controller = loader.<DetailObjednavky>getController();
           controller.zobraz_detail(o,p);
           Stage stage = new Stage();
           stage.setTitle("TomOffice");
           stage.setScene(new Scene(root, 400,200));
           stage.show();
       } catch (IOException e) {
           e.printStackTrace();
       }
        //Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
    }
    public void odhlas(ActionEvent e)throws Exception{
        LoginScene l = new LoginScene();
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        l.startLoginScenu(stage);
    }
}
