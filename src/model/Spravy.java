package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Spravy implements Serializable { //pozorovatel je jeden controller
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
    public void vycistiSpravy(){
        this.spravy  = new ArrayList<>();
    }
    public String toString(){
        StringBuilder s = new  StringBuilder();
        //lambda
        this.spravy.forEach((m)->{
            s.append("\n"+m);
            System.out.println(m);
        });
        return s.toString();
    }


}
