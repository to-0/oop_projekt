package databaza;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Login;
import model.ManazerObjednavok;
import model.Objednavka;
import sklad.Sklad;
import tovar.*;
import pouzivatelia.*;

public class Databaza {
	private  static ArrayList<Pouzivatel> users = new ArrayList<Pouzivatel>();
	private static ArrayList<Objednavka> objednavky = new ArrayList<Objednavka>();
	public static ArrayList<Pouzivatel> getUsers(){
		return users;
	}
	public static Sklad sklad;
	public static void init() {
		if(!Databaza.deserializuj()){ //ked sa mi nepodari deserializovat vsetko
			users = Reader.nacitaj_pouz();
			objednavky = Reader.nacitaj_objednavky();
			prirad_objednavky();
			ManazerObjednavok m = ManazerObjednavok.getInstance();
			for(Objednavka o: objednavky){
				m.prirad_objednavku_pracovnikom(o);
			}
			Sklad.getInstance().nastav_pozorovatelov();
		}
		//ak sklad nema ziadnych pozorovatelov tak nastavim
		if(Sklad.getInstance().pocet_pozorovatelov()==0)
			Sklad.getInstance().nastav_pozorovatelov();
		/*users = Reader.nacitaj_pouz();
			objednavky = Reader.nacitaj_objednavky();
			System.out.println("skoncil som init");
			prirad_objednavky();
			Sklad.getInstance();
			ManazerObjednavok m = ManazerObjednavok.getInstance();
			for(Objednavka o: objednavky){
				m.prirad_objednavku_pracovnikom(o);
			}*/
		//prirad_objednavky();

	}
	public static void serializuj(){
		ObjectOutputStream outputStream;
		System.out.println("Serializujem");
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("pouzivatelia.out"));
			outputStream.writeObject(users);
			outputStream.close();

			Sklad sklad = Sklad.getInstance();
			outputStream = new ObjectOutputStream(new FileOutputStream("sklad.out"));
			outputStream.writeObject(sklad);
			outputStream.close();

			outputStream = new ObjectOutputStream(new FileOutputStream("objednavky.out"));
			outputStream.writeObject(objednavky);
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//priradim objednavky spravnym kleintom a manazerovovi dam vsetky, volam iba ked sa mi nepodari deserializovat
	public static void prirad_objednavky() {
		for(Pouzivatel u:users) {
			for(Objednavka o: objednavky) {
				if(u instanceof Klient) { //ked je to klient
					if(o.get_Klient_id()==u.getId()) {
						u.pridaj_objednavku(o);
					}
				}
				if(u instanceof Manazer) {
					u.pridaj_objednavku(o);
				}
			}
		}
	}
	public static Pouzivatel find_user(String nick, String pass){
		for(Pouzivatel u: users){
			if(u.validuj(nick,pass))  return u;
		}
		return null;
	}
	public static boolean deserializuj() {
		try {
			ObjectInputStream inputStream3 = new ObjectInputStream(new FileInputStream("sklad.out"));
			Sklad sklad;
			sklad = (Sklad) inputStream3.readObject();
			Sklad.setInstance(sklad);
			inputStream3.close();

			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pouzivatelia.out"));
			users = (ArrayList<Pouzivatel>) inputStream.readObject();
			inputStream.close();
			ObjectInputStream inputStream2 = new ObjectInputStream(new FileInputStream("objednavky.out"));
			objednavky = (ArrayList<Objednavka>) inputStream2.readObject();
			inputStream2.close();


			return true;
		} catch (Exception e) {
			System.out.println("Pri citani nastal problem.");
			return false;
		}
	}

	public static void test() {
		Pouzivatel k;
		//SERIALIZACIA
        k=  users.get(1);
        ObjectOutputStream outputStream;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("test.out"));
			outputStream.writeObject(k);
		    outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//DESERIALIZACIA
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("test.out"));
            k = (Klient) inputStream.readObject();
            inputStream.close();
            System.out.println("Mal si ulozenu obec " + ((Klient) k).getAdresa().getObec());
           // System.out.println("Tovar":(Klient k))
        } catch (Exception e) {
            System.out.println("Pri citani nastal nejaky problem.");
        }
	}
	public static ArrayList<Pracovnik> vrat_vyrobu(){ //vrati iba pracovnikov vyroby
		ArrayList<Pracovnik> pracovnici = new ArrayList<>();
		for(Pouzivatel p: users){
			if(p instanceof Pracovnik){
				Pracovnik temp = (Pracovnik) p;
				pracovnici.add(temp);
			}
		}
		return pracovnici;
	}
	public static ArrayList<Objednavka> getObjednavky(){
		return objednavky;
	}
}
