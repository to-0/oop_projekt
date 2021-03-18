package databaza;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import model.Login;
import model.Objednavka;
import tovar.*;
import pouzivatelia.*;

public class Databaza {
	private  static ArrayList<Pouzivatel> users = new ArrayList<Pouzivatel>();
	private static ArrayList<Objednavka> objednavky = new ArrayList<Objednavka>();
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
	public static ArrayList<Pouzivatel> getUsers(){
		return users;
	}
	public static void init() {
		users = Reader.nacitaj_pouz();
		objednavky = Reader.nacitaj_objednavky();
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
					//u.get
				}
			}
		}
	}
	public static void serializuj_pouzivatelov() {
		 ObjectOutputStream outputStream;
			try {
				outputStream = new ObjectOutputStream(new FileOutputStream("pouzivatelia.out"));
				for(Pouzivatel p:users) { //tu asi nemusi byt cast na specifickeho pouzivatela i guess
					outputStream.writeObject(p);
				    outputStream.close();
				}
				
				
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
				for(Objednavka o:objednavky) { //tu asi nemusi byt cast na specifickeho pouzivatela i guess
					outputStream.writeObject(o);
				    outputStream.close();
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	public static void deserializuj_pouzivatelov() {
		Pouzivatel k;
		 try {
	            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("pouzivatelia.out"));
	            users.add((Pouzivatel) inputStream.readObject());
	            inputStream.close();
	           // System.out.println("Mal si ulozenu obec " + ((Klient) k).getAdresa().getObec());
	        } catch (Exception e) {
	            System.out.println("Pri citani nastal nejaky problemek.");
	        }
	}
	public static void deserializuj_objednavky() {
		
		 try {
	            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("objednavky.out"));
	            objednavky.add((Objednavka) inputStream.readObject());
	            inputStream.close();
	           // System.out.println("Mal si ulozenu obec " + ((Klient) k).getAdresa().getObec());
	        } catch (Exception e) {
	            System.out.println("Pri citani nastal nejaky problemek.");
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
}
