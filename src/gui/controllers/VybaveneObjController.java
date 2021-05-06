package gui.controllers;

import com.sun.corba.se.spi.activation.BadServerDefinition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Objednavka;
import pouzivatelia.Pouzivatel;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;

public class VybaveneObjController extends AController{
    private ObservableList<Objednavka> list;
    private ArrayList<Objednavka> vybavene_klon; //aby ked klikne pouzivatel na detail sedeli indexy asi...
    @FXML
    ListView<Objednavka> objednavky_list = new ListView<Objednavka>();
    //nastartujem controller, posuniem si pouzivatela a spravim list vybavenych objednavok
    public void startuj_cont(Pouzivatel p){
        this.vybavene_klon = new ArrayList<Objednavka>();
        this.list = FXCollections.observableArrayList();
        this.p = p;
        for(Objednavka o:p.getObjednavky()){
            if(o.get_stav()){ //uz je vybavena
                this.list.add(o);
                this.vybavene_klon.add(o);
            }
        }
        objednavky_list.getItems().addAll(this.list);
    }
    //detail ten objednavky
    public void zobrazObjednavku(){
        try{
            int index = objednavky_list.getSelectionModel().getSelectedIndex();
            System.out.println("Vybral " + index);
            Objednavka o  = this.vybavene_klon.get(index);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/detail_objednavky.fxml"));
            Parent root = loader.load();
            DetailObjednavky controller = loader.<DetailObjednavky>getController();
            controller.zobraz_detail(o,p);
            zobraz_okno(root,300,200);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
    }
    public void vrat_domov(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.<HomeController>getController();
        controller.setData(this.p,this.p.getObjednavky());
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        zobraz_okno(root,900,500,stage);
    }
}
