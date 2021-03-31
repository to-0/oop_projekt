package gui.controllers;

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

public class HomeController   extends AController {
    //private Pouzivatel p;
    //private FXCollections
    private ArrayList<Objednavka> p_objednavky;
    private ObservableList<Objednavka> doList;
    public ArrayList<String> spravy;
    @FXML
    TextArea spravy_akcii;
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
        System.out.println(this.p.toString());
        message.setText(this.p.toString());
        spravy_akcii.setText(this.p.getSpravy().toString());
        for(Objednavka o:p.getObjednavky()){
            if(!o.getPripravenost()) //zobrazim ju iba ked neni vybavena
                this.doList.add(o);
            //objednavky_list.getItems().add(o.toList());
        }
        sklad_butt.setVisible(p instanceof PristupSklad);
        nova_obj.setVisible(p instanceof Klient);
        objednavky_list.getItems().addAll(this.doList);
        spravy = new ArrayList<>(); //potrebujem vynulovat spravy
    }
    public void zobraz_nova_objednavka(ActionEvent e){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/nova_objednavka.fxml"));
            Parent root = loader.load();
            NovaObjController controller = loader.<NovaObjController>getController();
            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            controller.start(this.p,stage);
            zobraz_okno(root,500,500,stage);
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
           controller.zobraz_detail(o,p,spravy_akcii);
           zobraz_okno(root,300,200);
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
    public void zobrazSklad() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/sklad.fxml"));
        Parent root = loader.load();
        SkladController controller = loader.<SkladController>getController();
        controller.start_sklad(this.p,spravy_akcii);
        zobraz_okno(root,500,300);

    }
    public void vycisti(){
        this.p.getSpravy().vycistiSpravy();
        this.spravy_akcii.setText("");
    }
}
