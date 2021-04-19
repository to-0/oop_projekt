package gui.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Objednavka;
import model.PozorovatelSprav;
import pouzivatelia.Pouzivatel;
import pouzivatelia.Pracovnik;
import pouzivatelia.Skladnik;
import pouzivatelia.ObjSpracovanie;
import tovar.Tovar;

import java.util.ArrayList;
/**
 * <p>Controller pre scénu detail objednávky.
 * </p>
 *
 */
public class DetailObjednavky extends AController implements PozorovatelSprav {
    /**
     * Objednávka ktorej detail zobrazujeme.
     */
    Objednavka o;
    ObservableList<String> myList = FXCollections.<String>observableArrayList();
    ArrayList<String> spravy;
    TextArea spravy_home;
    @FXML
    Label klient;
    @FXML
    TextArea tovar;
    @FXML
    Button vyrob_b;
    @FXML
    Label sprava;
    /**
     *Metóda sa volá pri prepínaní scény na detail objednávky. Inicializuje celú scénu.
    *@param o konkrétna objednávka
   * @param p pouzivatel
     *@param spravy TextArea, ktorý bude zobrazovať správy na domovskej obrazovke.
     **/
    public void zobraz_detail(Objednavka o, Pouzivatel p, TextArea spravy){
        this.p=p;
        this.o=o;
        klient.setText(o.getKlient().toString());
        String s="";
        this.spravy_home = spravy;
        for(Tovar t: o.getTovar()){
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
            if(p  instanceof Pracovnik)
                sprava.setText("Tvoja cast tovaru uz bola vyrobena, cakaj na dalsie spracovanie");
            vyrob_b.setVisible(false);
        }
        if(p instanceof Skladnik && o.getPripravenost() && !o.get_stav()){ //je pripravena na odoslanie ale este nebola odoslana
            vyrob_b.setVisible(true);
            vyrob_b.setText("Odosli objednavku");
        }
    }

    /**
     * Prekonaná metóda zobraz detail, ktorú volám keď si chcem pozrieť detail vybavenej objednávky.
     * @param o objednavka
     * @param p pouzivatel
     */
    public void zobraz_detail(Objednavka o, Pouzivatel p){ //toto volam pri vybavenych objednavkach
        this.p=p;
        this.o=o;
        klient.setText(o.getKlient().toString());
        StringBuilder s= new StringBuilder();
        for(Tovar t: o.getTovar()){
            s.append(" ").append(t.toString()).append("\n");
        }
        tovar.setText(s.toString());
        //ked je objednavka vybavene nebudem zobrazovat ziadny button...
        vyrob_b.setVisible(false);
    }
    public void vyrob_button(){
        ((ObjSpracovanie)this.p).spracuj_obj(this.o);
    }
    public void odosli_button(){
        ((Skladnik)this.p).odosli_objednavku(this.o);
    }

    @Override
    public void notify(String sprava) {
        this.spravy_home.appendText(sprava);
    }
}
