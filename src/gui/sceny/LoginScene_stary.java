package gui.sceny;

import gui.controllers.LoginController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Login;

public class LoginScene_stary {

    public void startLoginScenu(Stage primaryStage){
        LoginController control = new LoginController();
        Label l1 = new Label("Meno");
        Label l2 = new Label("Heslo");
        Label message = new Label();
        TextField meno = new TextField();
        PasswordField heslo = new PasswordField();
        Button button = new Button("Login");
        VBox layout = new VBox();
        button.setOnAction(e->{
            try {
                control.tryLogin(e,meno.getText(),heslo.getText(),message);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        Scene scene = new Scene(layout,400,400);
        layout.getChildren().addAll(l1,meno,l2,heslo,button,message);
        //scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("TomOffice");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
