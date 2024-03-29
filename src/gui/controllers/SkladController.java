package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.PozorovatelSprav;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Skladnik;
import sklad.Sklad;

public class SkladController extends  AController implements PozorovatelSprav {
    @FXML
    ChoiceBox dopln_choicebox;
    @FXML
    TextField mnozstvo;
    @FXML
    TextArea sklad_text;
    TextArea spravy_home;
    private Sklad sklad;
    public void start_sklad(Pouzivatel p,TextArea spravy){
        this.p = p;
        this.spravy_home = spravy;
        this.p.getSpravy().setPozorovatel(this);
        sklad = Sklad.getInstance();
        //ak je pouzivatel skladnik zobrazim mu moznosti na doplnenie skladu
        mnozstvo.setVisible(p instanceof Skladnik);
        dopln_choicebox.setVisible(p instanceof Skladnik);
        sklad_text.setText(sklad.vrat_stav());
        System.out.println(Sklad.getInstance().vrat_stav());
    }
    public void dopln(){ //podla toho ktory index choiceboxu si skladnik vybral nastavim t a volam dopln sklad s mnozstvom a tymto argumentom
        int t=0;
        String moznost = (String) dopln_choicebox.getValue(); //ktory index moznosti si vybral
        switch (moznost){
            case "Papier(ks)":
                t=1;
                break;
            case "Fotopapier(ks)":
                t=2;
                break;
            case "Toner(ml)":
                t=3;
                break;
            case "Tvrdy papier":
                t=4;
                break;
        }
        int x = Integer.parseInt(mnozstvo.getText());
        ((Skladnik)this.p).dopln_sklad(t,x);
        sklad_text.appendText("\n"+sklad.vrat_stav());
        this.p.getSpravy().pridaj_spravu("Sklad bol doplneny");
    }
    //tento controller je zaroven pozorovatelom sprav pretoze potrebujem vypisat na domovsku obrazovku ked ho doplnim, cize pozoruje spravy skladnika
    @Override
    public void notify(String sprava) {
        this.spravy_home.appendText("\n"+sprava);
    }
}
