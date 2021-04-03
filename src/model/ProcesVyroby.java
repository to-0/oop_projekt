package model;

import tovar.Tovar;

import java.text.SimpleDateFormat;
import java.util.Date;
/*
Trieda ktora sluzi na multithreading pri vyrobe
 */
public class ProcesVyroby extends Thread{
    Tovar t;
    Objednavka o;
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
    /*public String sprava;
    private String cas(){

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Tovar bol vytvoreny"+formatter.format(date));
        o.upozorni_pozorovatela();
        return "Tovar bol vytvoreny "+formatter.format(date);

    }*/
    //@Override
   /* public void run() {
        System.out.println("Vyrabam tovar...");
        try {
            Thread.sleep(5000);
            this.t.vybav();
            this.sprava = this.cas();
        } catch (InterruptedException e) {
            System.out.println("HALO spadol som");
            e.printStackTrace();
        }
        System.out.println("Skoncil som");
    }*/
    public void run() {
        try {
            this.stroj.zacni_vyrabat(t,this.o); //volam synchronized funkciu zacni vyrabat, dam vediet stroju ze ma vyrabat
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
