package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Objednavka;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Pracovnik;
import pouzivatelia.Vyroba;
import tovar.Tovar;

public class DetailObjednavky {
    Pouzivatel p;
    Objednavka o;
    @FXML
    Label klient;
    @FXML
    Label tovar;

    @FXML
    Button vyrob_b;
    @FXML
    Label sprava;
    public void zobraz_detail(Objednavka o,Pouzivatel p){
        this.p=p;
        this.o=o;
        klient.setText(o.getKlient().toString());
        String s="";
        for(Tovar t: o.tovar){
            s += " "+t.toString() +"\n";
        }
        tovar.setText(s);
        if(p instanceof Pracovnik && ((Pracovnik) p).skontroluj_stav_tovaru(o)){
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
}
