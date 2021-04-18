package gui.controllers;
import databaza.Databaza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import model.Objednavka;
import pouzivatelia.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Controller domovskej obrazovky.
 */
public class HomeController   extends AController {
    //private Pouzivatel p;
    //private FXCollections
    /**
     * Objednávky prihlaseného používateľa.
     */
    private ArrayList<Objednavka> p_objednavky;
    private ObservableList<Objednavka> doList;
    //public ArrayList<String> spravy;
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
    Button admin_b;
    @FXML
    public void initialize() {

    }

    /**
     * Inicializuje scénu, naplní list objednávok, nastaví atribúty triedy, zobrazí tlačidlá v súvislosti s typom používateľa.
     * @param u prihlásený používateľ
     * @param objednavky_u objednávky používateľa
     */
    public void setData(Pouzivatel u, ArrayList<Objednavka> objednavky_u){
        this.p = u;
        this.p_objednavky = new ArrayList<Objednavka>(objednavky_u);
        this.doList =  FXCollections.observableArrayList(); //dolist je list nevyrobenych objednavok
        System.out.println(this.p.toString());
        message.setText(this.p.toString()); //vypisem informacie o pouzivatelovi do domovskej obrazovky
        spravy_akcii.setText(this.p.getSpravy().toString()); //nastavim spravy
        for(Objednavka o:p.getObjednavky()){ //prechadzam jeho objednavky
            if(!(this.p instanceof Skladnik) && !o.getPripravenost()) //zobrazim ju iba ked neni vybavena, ale skladnikovi sa zobrazuju tie co su pripravene na odoslanie
                this.doList.add(o);
            else if (this.p instanceof Skladnik && o.getPripravenost()) this.doList.add(o); //ak je skladnik tak mu chcem zobrazit tie co su pripravene ale neboli vybavene este
            //objednavky_list.getItems().add(o.toList());
        }
        sklad_butt.setVisible(p instanceof Zamestnanec);
        nova_obj.setVisible(p instanceof Klient);
        objednavky_list.getItems().addAll(this.doList);
        admin_b.setVisible(this.p instanceof Manazer);
        //spravy = new ArrayList<>(); //potrebujem vynulovat spravy
    }

    /**
     * Spracovanie akcie kliknutia na button pre novú objednávku. Načíta scénu novej objednávky a prepne controller. Zmení sa celá scéna.
     * @param e ActionEvent cez ktorý v metóde získavam stage.
     */
    public void zobraz_nova_objednavka(ActionEvent e){ //nacitanie sceny nova objednavkaa prepnutie do tejto sceny
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

    /**
     * Sppracovanie akcie kliknutia na zobrazenie detailu vybranej objednávky.
     */
    public void zobrazObjednavku(){ //zobrazenie detailov objednavky ale taktiez cez tieto detaily pracovnici vyrabaju dany tovar pre objednavku
       try{
           int index = objednavky_list.getSelectionModel().getSelectedIndex();
           Objednavka o  = this.p_objednavky.get(index);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/detail_objednavky.fxml"));
           Parent root = loader.load();
           DetailObjednavky controller = loader.<DetailObjednavky>getController(); //nacitam si controller
           controller.zobraz_detail(o,p,spravy_akcii); //poslem si donho pouzivatela, objednavku a text_area na spravy akcii
           zobraz_okno(root,300,200);
       } catch (IOException e) {
           e.printStackTrace();
       }
        //Parent root = FXMLLoader.load(getClass().getResource("../sceny/home.fxml"));
    }

    /**
     * Sppracovanie akcie kliknutia na zobrazenie skladu.
     * @throws IOException
     */
    public void zobrazSklad() throws IOException{ //zobrazenie stavu skladu a tiez na doplnenie skladu
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/sklad.fxml"));
        Parent root = loader.load();
        SkladController controller = loader.<SkladController>getController();
        controller.start_sklad(this.p,spravy_akcii);
        zobraz_okno(root,500,300);

    }

    /**
     * Vyčistí správy používateľa a zároven textarea ktorý tieto správy zobrazuje.
     */
    public void vycisti(){ //iba na vycistenie sprav a textarea sprav
        this.p.getSpravy().vycistiSpravy();
        this.spravy_akcii.setText("");
    }

    /**
     * Sppracovanie akcie kliknutia na zobrazenie vybavených objenávok..
     * @param e ActionEvent cez ktorý v metóde získavam stage.
     * @throws Exception
     */
    public void vybavene_objednavky(ActionEvent e) throws Exception{ //handle na kliknutie buttonu vybavene objednavky, kde prepnem scenu a zobrazim list uz vybavenych objednavok
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/vybavene_objednavky_scena.fxml"));
        Parent root = loader.load();
        VybaveneObjController controller = loader.<VybaveneObjController>getController();
        controller.startuj_cont(this.p);
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        zobraz_okno(root,400,500,stage);
    }

    /**
     * Serializácia používateľov, objednávok a skladu.
     */
    public void uloz_akcia(){
        Databaza.serializuj();
    }
    public void admin_akcia() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/admin_scena.fxml"));
        Parent root = loader.load();
        AdminController controller = loader.<AdminController>getController();
        controller.inicializuj((Manazer)this.p);
        zobraz_okno(root,500,500);
    }
}
