package model;

import databaza.Databaza;
import pouzivatelia.*;
import tovar.Fotka;
import tovar.Obalka;
import tovar.Tovar;
import tovar.Zosit;

import java.io.Serializable;
import java.util.ArrayList;

public class ManazerObjednavok implements Serializable {
    private ArrayList<Pracovnik> pracovnici;
    private ArrayList<Skladnik> skladnici;
    public static ManazerObjednavok instancia = null;
    private ManazerObjednavok(){
        this.pracovnici = Databaza.vrat_vyrobu();
       // this.skladnici = Databaza.vrat_skladnikov();
    }
    public static ManazerObjednavok getInstance(){
        if(instancia==null)
            instancia  = new ManazerObjednavok();
        return instancia;
    }
    //priradi pracovnikom objednavku
    public void prirad_objednavku_pracovnikom(Objednavka o){
        //pomocne premnenne v pripade ze tam mam napr 2x rozne fotky aby som objednavku
        //nepriradil 2 roznym pracovnikom fotiek...
        int p1=0; //uz som priradil pracovnika na fotky
        int p2=0; // uz som priradil pracovnika na zosit
        int p3=0; // uz som priradil pracovnika na obalku
        Pracovnik pracovnik = null;
        //pre kazdy tovar priradim pravcovnika
        for(Tovar t: o.tovar){
            if(t instanceof Fotka && p1!=1){
                pracovnik = this.najdi_min_vyroba(t);
                p1=1;
            }
            else if(t instanceof Zosit && p2!=1){
                pracovnik = this.najdi_min_vyroba(t);
                p2=1;
            }
            else if(t instanceof Obalka && p3!=1){
                pracovnik = this.najdi_min_vyroba(t);
                p3 = 1;
            }
            if(pracovnik !=null){
                pracovnik.getObjednavky().add(o);
            }
        }
    }
    //najde najvhodnejsieho kandidata ktoremu pracovnikovi priradit objednavku v zavislosti od tovaru t v objednavke
    public Pracovnik najdi_min_vyroba(Tovar t) {
        Pracovnik min = null;
        int min_pocet = -1;
        for (Pouzivatel v : Databaza.getUsers()) {
            if (t instanceof Fotka) {
                if (v instanceof PracovnikFotky) {
                    if (min != null && min_pocet > ((PracovnikFotky) v).get_pocetobj()) {
                        min = (Pracovnik)v;
                    } else if (min == null) {
                        min_pocet = ((PracovnikFotky) v).get_pocetobj();
                        min = (Pracovnik)v;
                    }
                }
            } else if (t instanceof Zosit) {
                if (v instanceof PracovnikZosit) {
                    if (min != null && min_pocet > ((PracovnikZosit) v).get_pocetobj()) {
                        min =(Pracovnik)v;
                    } else if (min == null) {
                        min_pocet = ((PracovnikZosit) v).get_pocetobj();
                        min =  (Pracovnik)v;
                    }
                }
            } else if (t instanceof Obalka) {
                if (v instanceof PracovnikObalka) {
                    if (min != null && min_pocet > ((PracovnikObalka) v).get_pocetobj()) {
                        min =(Pracovnik)v;
                    } else if (min == null) {
                        min_pocet = ((PracovnikObalka) v).get_pocetobj();
                        min = (Pracovnik)v;
                    }
                }
            }
        }
        return min;
    }
    //vytvori novu objednavku a aktualizuje to s databazou vrati obejdnavku
    public boolean nova_objednavka(ArrayList<Tovar> t, Klient k){
        if(k==null) return false;
        int id = Databaza.getObjednavky().size()+1;
        Objednavka o = new Objednavka(id+1,t,k);
        k.pridaj_objednavku(o);
        Databaza.getObjednavky().add(o);
        prirad_objednavku_pracovnikom(o);
        return true;
    }
    //skontrolujem ci uz je objednavka cela vybavena ak je vybavena cela return true
    private boolean kontrola_stavu_obj(Objednavka o){
        for(Tovar t: o.tovar){
            if(t.getStav()==false) return false;
        }
        return true;
    }
    //spracuj upozornenie od objednavky
    public void upozorni(Objednavka o){
        if(this.kontrola_stavu_obj(o)){ //ak je hotova
            o.priprav_odoslanie();
            this.prirad_skladnika(o);
            //this.odstran_obj_pracovnikom(o);
        }
    }
    public void prirad_skladnika(Objednavka o){
        Skladnik min_skl=null;
        int pocet = -1;
        for(Pouzivatel p: Databaza.getUsers()){
            if(p instanceof Skladnik){
                if(min_skl==null) {
                    min_skl = (Skladnik) p;
                    pocet = p.get_pocetobj();
                    continue;
                }
                if(pocet > p.get_pocetobj()){
                    min_skl = (Skladnik) p;
                    pocet = p.get_pocetobj();
                }
            }
        }
        if(min_skl==null)
            return;
        min_skl.pridaj_objednavku(o);
    }
    public void odstran_obj_pracovnikom(Objednavka o){
        for(Pracovnik p: this.pracovnici){
            p.getObjednavky().remove(o); //ak tam je tak to odstrani ak nie nic sa nestane
        }
    }
}