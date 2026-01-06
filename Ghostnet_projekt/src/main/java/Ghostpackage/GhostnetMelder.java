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

	private Ghostnet ghostnet = new Ghostnet(); 
    
	@Inject 
	private Ghostnetliste ghostnetliste;


    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("GhostnetAppPersistenceUnit");

    public GhostnetMelder() {
        // WICHTIG: Objekte müssen existieren!
        ghostnet = new Ghostnet();
        ghostnet.setStandort(new Standort());
        
        ghostnet.setBergendePerson(null);
        ghostnet.setStatus(Status.gemeldet);
    }
    
    private boolean isBlankPerson(Person p) {
        if (p == null) return true;

        String name = p.getName();
        String handy = p.getHandynummer();

        boolean nameBlank = (name == null) || name.trim().isEmpty();
        boolean handyBlank = (handy == null) || handy.trim().isEmpty();

        return nameBlank && handyBlank;
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
            
            ghostnet.setBergendePerson(null);
            ghostnet.setStatus(Status.gemeldet);
            
            if (isBlankPerson(ghostnet.getMeldendePerson())) {
                ghostnet.setMeldendePerson(null);
            } else {
                
                if (ghostnet.getMeldendePerson().getName() != null) {
                    ghostnet.getMeldendePerson().setName(ghostnet.getMeldendePerson().getName().trim());
                }
                if (ghostnet.getMeldendePerson().getHandynummer() != null) {
                    ghostnet.getMeldendePerson().setHandynummer(ghostnet.getMeldendePerson().getHandynummer().trim());
                }
            }
            em.persist(ghostnet);   //Persist nicht merge, neues objekt erzeugen
            t.commit();

            ghostnetliste.getListe().add(ghostnet);

            System.out.println("Ghostnet gespeichert: " + ghostnet);    
        
        } catch (Exception e) {
        	if (t != null && t.isActive()) t.rollback();
        	throw e;
        }
        
        finally {
            if (em != null);
            em.close();
        }

        return "Ghostnetliste.xhtml?faces-redirect=true";
    }
    
    public Ghostnet getGhostnet() {
        return ghostnet;
    }

    public void setGhostnet(Ghostnet ghostnet) {
        this.ghostnet = ghostnet;
    }
    
    public Person getMeldendePersonForForm() {
        if (ghostnet.getMeldendePerson() == null) {
            ghostnet.setMeldendePerson(new Person());
        }
        return ghostnet.getMeldendePerson();
    }


}
