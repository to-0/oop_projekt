package gui.controllers;

import application.App;
import gui.sceny.LoginScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Objednavka;
import model.PristupSklad;
import pouzivatelia.Klient;
import pouzivatelia.Pouzivatel;

import java.io.IOException;
import java.util.ArrayList;

public class HomeController {
    private Pouzivatel p;
    private ArrayList<Objednavka> p_objednavky;
    private ObservableList<Objednavka> doList;
    public static String spravy;
    @FXML
    ListView<Objednavka> objednavky_list = new ListView<Objednavka>();
    @FXML
    Label message;
    @FXML
    Button sklad_butt;
    @FXML
    Button nova_obj;
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
        sklad_butt.setVisible(p instanceof PristupSklad);
        nova_obj.setVisible(p instanceof Klient);
        objednavky_list.getItems().addAll(this.doList);
        spravy = new String(); //potrebujem vynulovat spravy
    }
    public void zobraz_nova_objednavka(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/nova_objednavka.fxml"));
            Parent root = loader.load();
            NovaObjController controller = loader.<NovaObjController>getController();
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            controller.start(this.p,stage);
            stage.setTitle("TomOffice");
            stage.setScene(new Scene(root, 400,500));
        }
        catch(IOException eve) {
            eve.printStackTrace();
        }

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
    public void zobrazSklad(){

    }
}
