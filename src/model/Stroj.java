package model;

import pouzivatelia.Pracovnik;
import pouzivatelia.Vyroba;
import tovar.Tovar;
import javafx.scene.control.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/*

 */
public class Stroj implements Serializable {
    private  TypStroja typ;
    String nazov;
    Pracovnik pracovnik;
    public Stroj(TypStroja t, String nazov, Pracovnik pracovnik){
        this.typ = t;
        this.nazov = nazov;
        this.pracovnik = pracovnik;
    }
    public void spusti_proces(Tovar t,Objednavka o){ //tu vytvorim novu nit a zacnem proces vyroby
        System.out.println("Spustam proces vyroby");
        ProcesVyroby proces= new ProcesVyroby(this,t,o);
        proces.start();
    }
    //metoda ktora je volana z ineho vlakna
    public synchronized void zacni_vyrabat(Tovar t,Objednavka o) throws InterruptedException {
        System.out.println("Vyrabam tovar...");
        this.pracovnik.getSpravy().pridaj_spravu("Vyrabam tovar...");
        Thread.sleep(5000); //pockam 5sekund, neskor asi zmenim podla poctu tovaru
        t.vybav();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Tovar bol vytvoreny"+formatter.format(date));
        this.pracovnik.getSpravy().pridaj_spravu("Tovar bol vyrobeny "+formatter.format(date)); //pridam spravu pracovnikovi cim spustim cely proces vypisania tejto spravy do gui pomocou notify
        //spravy_stroja.appendText("Tovar bol vyrobeny "+formatter.format(date));
        o.upozorni_pozorovatela();
        //ProcesVyroby proces = new ProcesVyroby(t);
       // proces.run();
    }
}
