package gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ManazerObjednavok;
import model.Objednavka;
import model.Vynimka;
import pouzivatelia.Klient;
import pouzivatelia.Pouzivatel;
import tovar.Fotka;
import tovar.Obalka;
import tovar.Tovar;
import tovar.Zosit;

import java.util.ArrayList;

public class NovaObjController  extends AController{
    //private Pouzivatel p;
    @FXML
    ChoiceBox druh_tovaru;
    @FXML
    Label farebnost;
    @FXML
    ChoiceBox farebnost_choicebox;
    @FXML
    TextField mnozstvo_tovaru;
    @FXML
    ChoiceBox typ_choicebox;
    @FXML
    TextArea stav;

    Stage stage;
    private Objednavka objednavka;
    private ArrayList<Tovar> tovar;
    public void start(Pouzivatel p,Stage s){
        tovar = new ArrayList<Tovar>();
        this.p = p;
        this.stage = s;
    }
    //pridavam iba tovar este predtym nez vytvorim objednavku
    public void pridaj_tovarButt() {
        try {
            String druh = (String) druh_tovaru.getValue();
            int mnozstvo = Integer.parseInt(mnozstvo_tovaru.getText());
            boolean farebnost;
            int typ_tovaru = Integer.parseInt((String) typ_choicebox.getValue());
            if (farebnost_choicebox.getValue().equals("ano"))
                farebnost = true;
            else farebnost = false;
            switch (druh) { //zistim si aky druh mam vytvoreny
                case "Fotka":
                    this.tovar.add(new Fotka(mnozstvo, typ_tovaru, farebnost));
                    break;
                case "Zosit":
                    this.tovar.add(new Zosit(mnozstvo, typ_tovaru));
                    break;
                case "Obalka":
                    this.tovar.add(new Obalka(mnozstvo, typ_tovaru));
                    break;
            }
            stav.setText(this.tovar.toString());
        }
        catch(RuntimeException e){
            throw new Vynimka("Nespravny vstup");
        }
    }
    //spracujem akciu vytvorenia objednavok
    public void vytvor_objednavku_butt(ActionEvent e) throws Exception{
        ManazerObjednavok m = ManazerObjednavok.getInstance();
        //this.p.pridaj_objednavku(m.nova_objednavka(this.tovar, (Klient) this.p));
        m.nova_objednavka(this.tovar, (Klient) this.p);
        this.vrat_domov();
    }
    public void vrat_domov() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/home.fxml"));
        Parent root = loader.load();
        HomeController controller = loader.<HomeController>getController();
        controller.setData(this.p,this.p.getObjednavky());
        this.stage.setTitle("TomOffice");
        this.stage.setScene(new Scene(root,600,500));
    }

}
