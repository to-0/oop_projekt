package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
/*
Trieda spravy sluzi pre pouzivatelov a na zobrazovanie sprav pre pouzivatelov do gui.
 */
public class Spravy implements Serializable { //pozorovatel je jeden controller
    ArrayList<String> spravy; //vsetky spravy
    transient PozorovatelSprav pozorovatel; //jeden pozorovatel vzdy
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
    //vsetky spravy chcem dat do jedneho stringu a vratit
    public String toString(){
        StringBuilder s = new  StringBuilder();
        //lambda
        this.spravy.forEach((m)->{
            s.append("\n"+m);
            System.out.println(m);
        });
        return s.toString();
    }
    public void vycistiPozorovatela(){
        this.pozorovatel=null;
    }


}
