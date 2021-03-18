package databaza;
import pouzivatelia.*;
import tovar.Fotka;
import tovar.Obalka;
import tovar.Tovar;
import tovar.Zosit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Login;
import model.Objednavka;
public class Reader {
	static String uzivatelia_cesta =  System.getProperty("user.dir")+"\\src\\databaza\\pouzivatelia.txt";
	static String objednavky_cesta =  System.getProperty("user.dir")+"\\src\\databaza\\objednavky.txt";
	
	public static ArrayList<Pouzivatel> nacitaj_pouz(){
		ArrayList<Pouzivatel> list = new ArrayList<Pouzivatel>();
		File f;
		f = new File(uzivatelia_cesta);
		Scanner scan;
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				String riadok = scan.nextLine();
				String []arr = riadok.split(":");
				String typ = arr[0];
				System.out.println(typ);
				String nick = arr[1];
				String heslo = arr[2];
				Login login = new Login(nick,heslo);
				int id = Integer.parseInt(arr[3]);
				String meno = arr[4];
				String telefon = arr[5];
				String email = arr[6];
				switch(typ) {
				case "K":
					String ulica = arr[7];
					String obec = arr[8];
					String cislo = arr[9];
					list.add(new Klient(id,meno,login,telefon,email,ulica,obec,cislo));
					break;
				case "M":
					System.out.println("mam manazera");
					list.add(new Manazer(id,meno,login,telefon,email));
					break;
				case "S":
					list.add(new Skladnik(id,meno,login,telefon,email));
					break;
				case "F":
					list.add(new PracovnikFotky(id,meno,login,telefon,email));
					break;
				case "Z":
					list.add(new PracovnikZosit(id,meno,login,telefon,email));
					break;
				case "O":
					list.add(new PracovnikObalka(id,meno,login,telefon,email));
					break;
				}
				
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Nepodarilo sa otvorit subor");
			e.printStackTrace();
			return null;
		}
		scan.close();
		return list;
	}
	public static ArrayList<Objednavka> nacitaj_objednavky(){
		ArrayList<Objednavka> list = new ArrayList<Objednavka>();
		File f;
		f = new File(objednavky_cesta);
		Scanner scan;
		try {
			scan = new Scanner(f);
			while(scan.hasNextLine()) {
				String riadok = scan.nextLine();
				String []arr = riadok.split(":");
				int id_klient = Integer.parseInt(arr[0]);
				int pocet = Integer.parseInt(arr[1]);
				int id_obj = Integer.parseInt(arr[2]);
				ArrayList<Tovar> tovar = new ArrayList<Tovar>();
				Tovar t=null;
				int j=0;
				for(int i=0;i<pocet;i++) { //idem citat jednotlive polozky a ich atributy
					
					String typ_polozky = arr[3+j];
					j++;
					switch(typ_polozky) {
					case "F": //fotka
						int pocet_t = Integer.parseInt(arr[3+j]);
						j++;
						boolean p;
						String farebnost = arr[3+j];
						j++;
						if(farebnost.equals("T")) p=true;
						else p=false;
						int typ_fotky = Integer.parseInt(arr[3+j]);
						j++;
						t  = new Fotka(pocet_t,typ_fotky,p);
						break;
					case "Z":
						pocet_t = Integer.parseInt(arr[3+j]);
						j++;
						int typ_zosita = Integer.parseInt(arr[3+j]);
						j++;
						t  = new Zosit(pocet_t,typ_zosita);
						break;
					case "O":
						pocet_t = Integer.parseInt(arr[3+j]);
						j++;
						int typo = Integer.parseInt(arr[3+j]);
						j++;
						t  = new Obalka(pocet_t,typo);
						break;
						
				}
					tovar.add(t);	
			}
				int suma = Integer.parseInt(arr[3+j]);
			//este najst klienta
				Klient hk = null;
				for(Pouzivatel k: Databaza.getUsers()) {
					if(id_klient==k.getId()) {
						hk = (Klient)k;
					}
				}
				list.add(new Objednavka(id_obj,tovar,suma,hk));
			}
				
		} catch (FileNotFoundException e) {
			System.out.println("Nepodarilo sa otvorit subor");
			e.printStackTrace();
			return null;
		}
		scan.close();
		return list;
	}
}
