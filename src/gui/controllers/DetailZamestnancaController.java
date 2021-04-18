package gui.controllers;

import databaza.Databaza;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.ManazerObjednavok;
import model.Objednavka;
import pouzivatelia.Manazer;
import pouzivatelia.Pouzivatel;

import java.util.ArrayList;

public class DetailZamestnancaController extends AController{
    @FXML
    TextArea detail;
    public void inicializuj(Pouzivatel p){
        this.p = p;
        detail.appendText(p.toString());
    }

    public void vyhod(){
        Databaza.getUsers().remove(this.p);
        for(Objednavka o: this.p.getObjednavky()){
            if(!o.get_stav()){
                ManazerObjednavok manazer = ManazerObjednavok.getInstance();
                manazer.prirad_objednavku_pracovnikom(o);
            }
        }
    }
}
