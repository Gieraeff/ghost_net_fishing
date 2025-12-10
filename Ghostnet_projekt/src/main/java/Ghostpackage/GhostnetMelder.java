package Ghostpackage;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.*;
import Ghostpackage.Ghostnet;

@Named("ghostnetMelder")
@ViewScoped
public class GhostnetMelder implements Serializable {

	private Ghostnet ghostnet = new Ghostnet(); // Neues Ghostnet für das Formular
    
	@Inject 
	private Ghostnetliste ghostnetliste;


    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("GhostnetAppPersistenceUnit");

    public GhostnetMelder() {
        // WICHTIG: Objekte müssen existieren!
        ghostnet = new Ghostnet();
        ghostnet.setStandort(new Standort());
        
        ghostnet.setBergendePerson(new Person());
        ghostnet.setStatus(Status.gemeldet);
    }
        
    public Ghostnet getGhostnet() {
        return ghostnet;
    }

    public void setGhostnet(Ghostnet ghostnet) {
        this.ghostnet = ghostnet;
    }


    public String zurück() {
    	return "Ghostnetliste.xhtml";
    }

    // Speichern-Methode
    public String save() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();

        try {
            t.begin();
            em.persist(ghostnet);   // <- Dein neues Ghostnet speichern!
            t.commit();

            // zusätzlich in deine Liste einfügen
            ghostnetliste.getListe().add(ghostnet);

            System.out.println("Ghostnet gespeichert: " + ghostnet);

        } finally {
            if (t.isActive()) t.rollback();
            em.close();
        }

        return "Ghostnetliste.xhtml?faces-redirect=true";
    }
}