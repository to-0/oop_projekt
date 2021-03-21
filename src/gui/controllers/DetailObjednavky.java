package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Objednavka;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Vyroba;
import tovar.Tovar;

public class DetailObjednavky {
    Pouzivatel p;
    @FXML
    Label klient;
    @FXML
    Label tovar;

    @FXML
    Button vyrob_b;

    public void zobraz_detail(Objednavka o,Pouzivatel p){
        klient.setText(o.getKlient().toString());
        String s="";
        for(Tovar t: o.tovar){
            s += " "+t.toString() +"\n";
        }
        tovar.setText(s);
        if(p instanceof Vyroba){
            vyrob_b.setVisible(true);
        }
        else vyrob_b.setVisible(false);
    }

}
