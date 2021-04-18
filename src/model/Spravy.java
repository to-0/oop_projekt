package model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
/**
*Trieda spravy sluzi pre pouzivatelov a na zobrazovanie sprav pre pouzivatelov do gui.
 */
public class Spravy implements Serializable { //pozorovatel je jeden controller
    /**
     * Správy v ArrayList
     */
    ArrayList<String> spravy; //vsetky spravy
    /**
     * Pozorovatel správ
     */
    transient PozorovatelSprav pozorovatel; //jeden pozorovatel vzdy
    public Spravy(){
        this.spravy = new ArrayList<>();
        this.pozorovatel = null;
    }

    /**
     * Pridá správi do zoznamu správ a upozorní pozorovateľa.
     * @param sprava
     */
    public void pridaj_spravu(String sprava){
        this.spravy.add(sprava);
        System.out.println("Pridal som spravu");
        if (this.pozorovatel!=null)this.pozorovatel.notify(sprava);
    }

    /**
     * Nastaví pozorovatela
     * @param pozorovatel
     */
    public void setPozorovatel(PozorovatelSprav pozorovatel){
        this.pozorovatel = pozorovatel;
    }

    /**
     * Vyčistí všetky správy a vytvorí nový ArrayList
     */
    public void vycistiSpravy(){
        this.spravy  = new ArrayList<>();
    }
    //vsetky spravy chcem dat do jedneho stringu a vratit
    /*
    Metóda toString vytvorí string so všetkými správami.
     */
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
