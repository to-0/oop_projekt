package model;

import pouzivatelia.Pracovnik;
import pouzivatelia.Vyroba;
import tovar.Tovar;
import javafx.scene.control.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Trieda Stroj, vyrába tovar.
 */
public class Stroj implements Serializable {
    private  TypStroja typ;
    String nazov;
    /**
     * Pracovník, ktorý ma na starosti stroj.
     */
    Pracovnik pracovnik;
    public Stroj(TypStroja t, String nazov, Pracovnik pracovnik){
        this.typ = t;
        this.nazov = nazov;
        this.pracovnik = pracovnik;
    }

    /**
     * Metóda spustí proces výroby v novom threade.
     * @param t tovar, ktorý ide na výrobu
     * @param o objednávka v ktorej je tovar
     */
    public void spusti_proces(Tovar t,Objednavka o){ //tu vytvorim novu nit a zacnem proces vyroby
        //NESTED CLASS
        class ProcesVyroby extends Thread{
            Stroj stroj;
            public ProcesVyroby(Stroj  stroj){
                this.stroj = stroj;
            }
            @Override
            public void run() {
                try{
                    this.stroj.zacni_vyrabat(t,o);
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Spustam proces vyroby");
        ProcesVyroby proces= new ProcesVyroby(this);
        proces.start();
    }
    /**
     * Metóda ktorá sa volá z nového vlákna. Vyrobí tovar a, nachvíľu zastaví thread a pridá správu o vyrobení pracovníkovi.
     * @param t tovar
     * @param o objednavka
     * @throws InterruptedException
     */
    public synchronized void zacni_vyrabat(Tovar t,Objednavka o) throws InterruptedException {
        System.out.println("Vyrabam tovar...");
        this.pracovnik.getSpravy().pridaj_spravu("Vyrabam tovar...");
        if(t.getMnozstvo()<=10)
            Thread.sleep(5000); //pockam 5sekund, neskor asi zmenim podla poctu tovaru
        else{
            Thread.sleep(500*t.getMnozstvo());
        }
        t.vybav();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Tovar bol vytvoreny"+formatter.format(date));
        this.pracovnik.getSpravy().pridaj_spravu("Tovar bol vyrobeny "+formatter.format(date)); //pridam spravu pracovnikovi cim spustim cely proces vypisania tejto spravy do gui pomocou notify
        //spravy_stroja.appendText("Tovar bol vyrobeny "+formatter.format(date));
        o.upozorni_pozorovatela();
        //ProcesVyroby_old proces = new ProcesVyroby_old(t);
       // proces.run();
    }
}
