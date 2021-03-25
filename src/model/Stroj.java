package model;

import pouzivatelia.Pracovnik;
import pouzivatelia.Vyroba;
import tovar.Tovar;

import java.text.SimpleDateFormat;
import java.util.Date;

//TODO tu bude viacero vlakien
public class Stroj {
    private  TypStroja typ;
    String nazov;
    Pracovnik pracovnik;
    public Stroj(TypStroja t, String nazov, Pracovnik pracovnik){
        this.typ = t;
        this.nazov = nazov;
        this.pracovnik = pracovnik;
    }
    public void spusti_proces(Tovar t,Objednavka o){
        System.out.println("Spustam proces vyroby");
        ProcesVyroby proces= new ProcesVyroby(this,t,o);
        proces.start();
    }
    public synchronized void zacni_vyrabat(Tovar t,Objednavka o) throws InterruptedException {
        System.out.println("Vyrabam tovar...");
        Thread.sleep(5000);
        t.vybav();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Tovar bol vytvoreny"+formatter.format(date));
        o.upozorni_pozorovatela();
        //ProcesVyroby proces = new ProcesVyroby(t);
       // proces.run();
    }
}
