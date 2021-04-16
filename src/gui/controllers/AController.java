package gui.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pouzivatelia.Pouzivatel;
//pretazenie v abstraktnej class a to v metode zobraz_okno

/**
 * Abstraktná trieda pre všetky ostatné controllery.
 */
public abstract class AController {
    protected Pouzivatel p;
    /**
     * <p>Metóda zobraz okno s týmito parametrami, zobrazí nové okno na novom Stage. Čiže vyskočí nové okno navyše.
     * </p>
     * @param root typu Parent
     * @param x šírka
     * @param y výšla
     */
    public void zobraz_okno(Parent root,int x,int y){ //iba nove okno navyse, neprepnem scenu
        Stage stage = new Stage();
        stage.setTitle("TomOffice");
        stage.setScene(new Scene(root,x,y));
        stage.show();
    }
    /**
     * <p>Metóda zobraz okno s týmito parametrami, prepne scénu, zmení obsah hlavného okna.
     * </p>
     * @param root typu Parent
     * @param x šírka
     * @param y výška
     * @param stage pôvvodný stage, aby som mohol zmeniť celú scnén
     */
    public void zobraz_okno(Parent root,int x, int y,Stage stage){ //prepnem celu scenu
        stage.setTitle("TomOffice");
        stage.setScene(new Scene(root,500,500));
        stage.show();
    }
    /**
     * <p>Metóda odhlás zmení scénu na úvodnú obrazovku.
     * </p>
     */
    public void odhlas(ActionEvent e)throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../sceny/uvod.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        zobraz_okno(root,400,500,stage);
    }
}
