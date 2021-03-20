package gui.controllers;

import application.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Objednavka;
import pouzivatelia.Pouzivatel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private Pouzivatel p;
    private ArrayList<Objednavka> p_objednavky;
    private ObservableList<String> doList;
    @FXML
    ListView<String> objednavky_list = new ListView<String>();
    @FXML
    Label message;
    @FXML
    public void initialize() {

    }
    public void setData(Pouzivatel u, ArrayList<Objednavka> objednavky_u){
        this.p = u;
        System.out.println("Som v set data argument user je "+u.getMeno());
        this.p_objednavky = new ArrayList<Objednavka>(objednavky_u);
        this.doList =  FXCollections.observableArrayList();
        message.setText(App.user.getMeno()); // Perfectly Ok here, as FXMLLoader already populated all @FXML annotated members.
        for(Objednavka o:p.getObjednavky()){
           this.doList.add(o.toList());
            objednavky_list.getItems().add(o.toList());
        }
       // objednavky_list  = new ListView<String>();
        //objednavky_list.getItems().addAll(this.doList);


    }
}
