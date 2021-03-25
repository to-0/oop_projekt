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
	public void generate() {
		Objednavka o;
		users.add(new Klient(1,"Anton Bernolak",new Login("anton","123"),"0915453","anton@anton.sk","Duhova","Bratislava","3"));
		users.add(new Klient(2,"Janko Hrasko",new Login("janko","123"),"091515321","janko@janko.sk","Dlha","Nitra","52"));
		users.add(new Klient(3,"Beata Pancuchova",new Login("beata","123"),"091785321","be@be.sk","Kratka","Zilina","785"));

		users.add(new Manazer(5,"Ujo Silny",new Login("ujo","123"),"0917789","big@boss.sk"));
		users.add(new Skladnik(6,"Skladnik Jozo",new Login("jozo","123"),"0911429","jozo@jozovo.com"));
		users.add(new PracovnikFotky(7,"Ferko Tichy",new Login("ferko","123"),"08897899","ferko@ferko.sk"));
		users.add(new PracovnikZosit(8,"Zositovec Ujo",new Login("zosit","123"),"09123456","zosit@ferko.sk"));
		users.add(new PracovnikObalka(9,"Jon Snow",new Login("jon","123"),"095487986","obalka@tomoffice.sk"));
		ArrayList<Tovar> tovar = new ArrayList<Tovar>();

	}
	public static void init() {
		if(!Databaza.deserializuj_pouzivatelov() || !Databaza.deserializuj_objednavky()){

		}
		users = Reader.nacitaj_pouz();
		objednavky = Reader.nacitaj_objednavky();
		System.out.println("skoncil som init");
		prirad_objednavky();
		Sklad.sklad_init();
		ManazerObjednavok m = ManazerObjednavok.getInstance();
		for(Objednavka o: objednavky){
			m.prirad_objednavku_pracovnikom(o);
		}
	}

	//toto mozno vdaka tomu serealize nebudem potrebovat...
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
	public static void serializuj_pouzivatelov() {
		 ObjectOutputStream outputStream;
			try {
				outputStream = new ObjectOutputStream(new FileOutputStream("pouzivatelia.out"));
				outputStream.writeObject(users);
				outputStream.close();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	public static void serializuj_objednavky() {
		 ObjectOutputStream outputStream;
			try {
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
	public static boolean deserializuj_pouzivatelov() {
		 try {
	            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pouzivatelia.out"));
	            users = (ArrayList<Pouzivatel>) inputStream.readObject();
	            inputStream.close();
	           return true;
	        } catch (Exception e) {
	            System.out.println("Pri citani nastal nejaky problemek.");
	            return false;
	        }
	}
	public static boolean deserializuj_objednavky() {
		
		 try {
	            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objednavky.out"));
	            objednavky = (ArrayList<Objednavka>) inputStream.readObject();
	            inputStream.close();
	           	return true;
	        } catch (Exception e) {
			 System.out.println("Pri citani nastal nejaky problemek.");
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
            System.out.println("Pri citani nastal nejaky problemek.");
        }
	}
	public static ArrayList<Pracovnik> vrat_vyrobu(){
		ArrayList<Pracovnik> pracovnici = new ArrayList<>();
		for(Pouzivatel p: users){
			if(p instanceof Pracovnik){
				Pracovnik temp = (Pracovnik) p;
				pracovnici.add(temp);
			}
		}
		return pracovnici;
	}
	public static Pouzivatel najdi_pouzivatela(int id){
		for(Pouzivatel p2: users){
			if(id==p2.getId())
				return p2;
		}
		return null;
	}
	public static ArrayList<Objednavka> getObjednavky(){
		return objednavky;
	}

}
