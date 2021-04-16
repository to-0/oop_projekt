package model;

import tovar.Tovar;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Trieda, ktorá slúži na vytvorenie nového threadu a volanie metódy stroja počas výroby.
 */
public class ProcesVyroby extends Thread{
    /**
     * Tovar, ktorý sa bude vyrábať.
     */
    Tovar t;
    Objednavka o;
    /**
    * Stroj ktorého metódu volám v metóde run.
     */
    Stroj stroj;
    public ProcesVyroby(Tovar t,Objednavka o){
        this.t = t;
        this.o = o;
    }
    //Ako argument berie stroj, tovar a objednavku
    public ProcesVyroby(Stroj  stroj,Tovar t,Objednavka o){
        this.stroj = stroj;
        this.t = t;
        this.o = o;
    }
    /**
    Zavolá metódu stroja, zacni_vyrabat.
     */
    public void run() {
        try {
            this.stroj.zacni_vyrabat(t,this.o); //volam synchronized funkciu zacni vyrabat, dam vediet stroju ze ma vyrabat
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
