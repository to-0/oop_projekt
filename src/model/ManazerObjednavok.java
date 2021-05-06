package model;

import databaza.Databaza;
import pouzivatelia.*;
import tovar.Fotka;
import tovar.Obalka;
import tovar.Tovar;
import tovar.Zosit;

import java.io.Serializable;
import java.util.ArrayList;


/**
*Trieda singleton, ktora ma na starosti manazment objednavok, nie je to fyzicka osoba ale automatizovany proces, singleton.
*Priraduje objednavky pracovnikom, ked su vyrobene skladnikom a zaroven pozoruje vsetky objednavky
pretoze pri kazdej zmene kontroluje ci uz je vyrobeny vsetok tovar a ci sa ma presunut skladnikovi.
 */
public class ManazerObjednavok implements Serializable {
    private ArrayList<Pracovnik> pracovnici;
    private ArrayList<Skladnik> skladnici;
    public static ManazerObjednavok instancia = null;
    private ManazerObjednavok(){
        this.pracovnici = Databaza.vrat_vyrobu(); //ukladam si separatne pracovnikov pre lahsie priradovanie objednavok pracovnikom
       // this.skladnici = Databaza.vrat_skladnikov();
    }
    public static ManazerObjednavok getInstance(){
        if(instancia==null)
            instancia  = new ManazerObjednavok();
        return instancia;
    }

    /**
     * Priradí objednávku pracovníkom, ktorí majú na starosti výrobu špecifického tovaru, podľa počtu ich objednávok a poďla typu tovaru, v objednávke.
     * @param o objednávka, ktorú priraďuje pracovníkom
     */
    public void prirad_objednavku_pracovnikom(Objednavka o){
        //pomocne premnenne v pripade ze tam mam napr 2x rozne fotky aby som objednavku
        //nepriradil 2 roznym pracovnikom fotiek...
        int p1=0; //uz som priradil pracovnika na fotky
        int p2=0; // uz som priradil pracovnika na zosit
        int p3=0; // uz som priradil pracovnika na obalku
        Pracovnik pracovnik = null;
        //pre kazdy tovar priradim pravcovnika, ak je tam nejaky tovar viac krat priradim to vsetko iba jednemu pracovnikovi
        for(Tovar t: o.getTovar()){
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
                pracovnik.getObjednavky().add(o); //ak som nasiel vyhovujuceho pracovnika tak priradim objednavku
                System.out.println("Priradil som "+pracovnik.getMeno());
                pracovnik.inc_pocet_obj();
                pracovnik = null;
            }
        }
    }
    //

    /**
     * najde najvhodnejsieho kandidata (ma najmensi pocet objednavok) ktoremu pracovnikovi priradit objednavku v zavislosti od tovaru t v objednavke
     * @param t
     * @return pracovník s minimálnym počtom objednávok
     */
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

    /**
     * Vytvorí novú objednávku, aktualizuje to s databázou a priradí danú objednávku pracovníkom.
     * @param t
     * @param k
     * @return
     */
    public boolean nova_objednavka(ArrayList<Tovar> t, Klient k){ //ako argumenty vsetok tovar a klient
        if(k==null) return false;
        int id = Databaza.getObjednavky().size()+1;
        Objednavka o = new Objednavka(id+1,t,k);
        k.pridaj_objednavku(o);
        Databaza.getObjednavky().add(o);
        prirad_objednavku_pracovnikom(o);
        return true;
    }
    /**
     * Skontroluje stav objednávky
     * @param o
     * @return
     */
    private boolean kontrola_stavu_obj(Objednavka o){
        for(Tovar t: o.getTovar()){
            if(t.getStav()==false) return false;
        }
        return true;
    }

    /**
     * Spracovanie upozornenia od objednávky. Skontroluje stav a ak je všetok tovar vyrobený, pripaví ju na odoslanie a priradí skladníkovi.
     * @param o objednávka, ktorú kontrolujem
     */
    public void upozorni(Objednavka o){
        if(this.kontrola_stavu_obj(o)){ //ak je hotova
            o.priprav_odoslanie();
            this.prirad_skladnika(o);
            //this.odstran_obj_pracovnikom(o);
        }
    }

    /**
     * Priradí vyrobenú objednávku skladníkovi na odoslanie.
     * @param o
     */
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
        this.zniz_aktivne_obj_pracovnikom(o);
    }
    private void zniz_aktivne_obj_pracovnikom(Objednavka o){
        for(Tovar t: o.getTovar()){
            for(Pouzivatel p: Databaza.getUsers())
            {
                if( (p instanceof  Pracovnik) && (p.getObjednavky().contains(t))){
                    p.dec_pocet_obj();
                }
            }
        }

    }
}
