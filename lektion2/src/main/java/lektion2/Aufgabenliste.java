package lektion2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@Named("aufgabenListe")
@ApplicationScoped
public class Aufgabenliste implements Serializable {
	private static Aufgabenliste instance = new Aufgabenliste();
	private List<Aufgabe> liste = new ArrayList<Aufgabe>();

	public Aufgabenliste() {
		liste.add(new Aufgabe("tue dies", "Jakob"));
		liste.add(new Aufgabe("tue das", "Nina"));
		liste.add(new Aufgabe("tue jenes", "Jessie"));
	}

	public static Aufgabenliste getInstance() {
		return instance;
	}

	public List<Aufgabe> getListe() {
		return liste;
	}
}
