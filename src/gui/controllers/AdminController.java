package gui.controllers;

import databaza.Databaza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Login;
import model.Objednavka;
import model.Vynimka;
import pouzivatelia.*;

import javafx.event.ActionEvent;

public class AdminController extends AController{
   @FXML
   TextArea zoznam_zamestnancov;
   @FXML
    ListView zoznam;
   @FXML
   TextField meno;
    @FXML
    TextField email;
    @FXML
    TextField telefon;
    @FXML
    TextField pr_m;
    @FXML
    PasswordField heslo;
    @FXML
    ChoiceBox typ_zamestnanca;

   Manazer m;
   public void inicializuj(Manazer p){
        ObservableList<Zamestnanec> list =  FXCollections.observableArrayList();
        p.nastav_zamestnancov();
        this.m = p;
        for(Zamestnanec z:p.getZamestnanci()){
            zoznam.getItems().add(Integer.toString(z.getId())+" "+z.getMeno());
        }
    }
    public void detail_zamestnanca(ActionEvent e) throws Exception{
        int i = zoznam.getSelectionModel().getSelectedIndex();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/detail_zamestnanca.fxml"));
        Parent root = loader.load();
        DetailZamestnancaController controller = loader.<DetailZamestnancaController>getController();
        controller.inicializuj(m.getZamestnanci().get(i));
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        zobraz_okno(root,500,500,stage);
    }
    public void pridaj_zamestnanca(){
       try{
           String moznost = (String) typ_zamestnanca.getValue(); //ktoru moznost si vybral
           int id = (int)(Math.random()*(200-11)+11);
           Login login = new Login(pr_m.getText(),heslo.getText());
           switch (moznost){
               case "Skladnik":
                   Skladnik s = new Skladnik(id,meno.getText(),login,telefon.getText(),email.getText());
                   Databaza.getUsers().add(s);
                   break;
               case "PracovnikF":
                   Databaza.getUsers().add(new PracovnikFotky(id,meno.getText(),login,telefon.getText(),email.getText()));
                   break;
               case "PracovnikZ":
                   Databaza.getUsers().add(new PracovnikZosit(id,meno.getText(),login,telefon.getText(),email.getText()));
                   break;
               case "PracovnikO":
                   Databaza.getUsers().add(new PracovnikObalka(id,meno.getText(),login,telefon.getText(),email.getText()));
                   break;
           }
           zoznam_zamestnancov.appendText("Zamestnanec pridany: "+id + meno.getText());
       }
       catch(RuntimeException e){
           throw new Vynimka("Nespravny vstup");
       }
    }
}
