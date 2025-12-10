package Ghostpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("ghostenetListe")
@ApplicationScoped
public class Ghostnetliste implements Serializable {
	private static Ghostnetliste instance = new Ghostnetliste();
	private List<Ghostnet> liste = new ArrayList<Ghostnet>();
	
	//SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	//todo: koordinationen format ergänzen
	
	public Ghostnetliste() {
		try {
			liste.add(new Ghostnet(
					new Standort(47.123456, 9.876543), 
					2.5f, 
					Status.gemeldet,
					new Person("Kevin", "+430660288")));
			//liste.add(new Aufgabe("tue das", "Nina", dateFormat.parse("10.09.2024"), false, 8));
			//liste.add(new Aufgabe("tue jenes", "Jessie", dateFormat.parse("17.09.2024"), false, 22));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Ghostnetliste getInstance() {
		return instance;
	}

	public List<Ghostnet> getListe() {
		return liste;
	}
}
