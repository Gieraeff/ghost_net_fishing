package Ghostpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;



@Named("ghostnetListe")
@ApplicationScoped
public class Ghostnetliste implements Serializable {
	@Inject
	private LoginController loginController;
	
	private static Ghostnetliste instance = new Ghostnetliste();
	private List<Ghostnet> liste = new ArrayList<Ghostnet>();
	
	//SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	//todo: koordinationen format ergänzen
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("GhostnetAppPersistenceUnit");
	
	public Ghostnetliste() {
	}
	
	public List<Ghostnet> getAllGhostnets() {
	    EntityManager em = emf.createEntityManager();
	    try {
	        return em.createQuery("SELECT g FROM Ghostnet g ORDER BY g.id DESC", Ghostnet.class)
	                 .getResultList();
	    } finally {
	        em.close();
	    }
	}
	
	public List<Ghostnet> getMeineBergungen() {
	    EntityManager em = emf.createEntityManager();
	    try {
	        return em.createQuery(
	            "SELECT g FROM Ghostnet g " +
	            "WHERE g.bergendePerson.name = :name",
	            Ghostnet.class)
	            .setParameter("name", loginController.getName())
	            .getResultList();
	    } finally {
	        em.close();
	    }
	}

	public static Ghostnetliste getInstance() {
		return instance;
	}

	public List<Ghostnet> getListe() {
		return liste;
	}
}
