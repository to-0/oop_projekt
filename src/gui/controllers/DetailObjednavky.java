package gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Objednavka;
import model.PozorovatelSprav;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Pracovnik;
import pouzivatelia.Vyroba;
import tovar.Tovar;

import java.util.ArrayList;

public class DetailObjednavky extends AController implements PozorovatelSprav {
    //Pouzivatel p;
    Objednavka o;
    ObservableList<String> myList = FXCollections.<String>observableArrayList();
    ArrayList<String> spravy;
    TextArea spravy_home;
    @FXML
    Label klient;
    @FXML
    Label tovar;

    @FXML
    Button vyrob_b;
    @FXML
    Label sprava;
    public void zobraz_detail(Objednavka o, Pouzivatel p, TextArea spravy){
        this.p=p;
        this.o=o;
        klient.setText(o.getKlient().toString());
        String s="";
        this.spravy_home = spravy;
        for(Tovar t: o.tovar){
            s += " "+t.toString() +"\n";
        }
        tovar.setText(s);
        if(p instanceof Pracovnik && ((Pracovnik) p).skontroluj_stav_tovaru(o)){
            ((Pracovnik)this.p).getSpravy().setPozorovatel(this);
            vyrob_b.setVisible(true);
            sprava.setVisible(false);
        }
        else {
            sprava.setVisible(true);
            sprava.setText("Tvoja cast tovaru uz bola vyrobena, cakaj na dalsie spracovanie");
            vyrob_b.setVisible(false);
        }

    }
    public void vyrob_button(){
        ((Vyroba)this.p).vyrob_tovar(this.o);
    }

    @Override
    public void notify(String sprava) {
        this.spravy_home.appendText(sprava);
    }
}
