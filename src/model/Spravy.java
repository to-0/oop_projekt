package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Spravy { //pozorovatel je jeden controller
    ArrayList<String> spravy;
    PozorovatelSprav pozorovatel;
    public Spravy(){
        this.spravy = new ArrayList<>();
    }
    public void pridaj_spravu(String sprava){
        this.spravy.add(sprava);
        System.out.println("Pridal som spravu");
        this.pozorovatel.notify(sprava);
    }
    public void setPozorovatel(PozorovatelSprav pozorovatel){
        this.pozorovatel = pozorovatel;
    }



}
