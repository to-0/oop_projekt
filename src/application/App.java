package application;

import model.Objednavka;
import pouzivatelia.Pouzivatel;

import java.util.ArrayList;

public class App {
    public static Pouzivatel user;
    public static ArrayList<Objednavka> objednavky;
    public static void setUser(Pouzivatel p){
        user = p;
    }
    public static void setObjednavky(ArrayList<Objednavka> obj){
        objednavky = new ArrayList<Objednavka>(objednavky);
    }
}
