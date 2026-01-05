package Ghostpackage;

import java.io.Serializable;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

@Named("ghostnetBergung")
@ViewScoped
public class GhostnetBergung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Ghostnetliste ghostnetliste;
    
    @Inject
    private LoginController loginController;

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("GhostnetAppPersistenceUnit");

    private Ghostnet ausgewaehltesGhostnet;

    
    private Person bergendePerson = new Person();

    
    public void vorbereitenBergung(Ghostnet ghostnet) {
        this.ausgewaehltesGhostnet = ghostnet;
        this.bergendePerson = new Person();
        this.bergendePerson.setName(loginController.getName());
    }

    /** Speichert die bergende Person und setzt den Status */
    public String bergungSpeichern() {

        if (ausgewaehltesGhostnet == null || ausgewaehltesGhostnet.getID() == null) {
            return null;
        }

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Ghostnet managed = em.find(Ghostnet.class, ausgewaehltesGhostnet.getID());

            if (managed.getBergendePerson() != null
            	    && managed.getBergendePerson().getName() != null
            	    && !managed.getBergendePerson().getName().isBlank()){
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Bergung bereits übernommen",
                                "Für dieses Netz ist bereits eine bergende Person eingetragen.")
                );
                tx.rollback();
                return null;
            }

            
            if (managed.getStatus() != Status.gemeldet) {
                FacesContext.getCurrentInstance().addMessage(
                        null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Bergung nicht möglich",
                                "Dieses Netz befindet sich nicht im Status 'gemeldet'.")
                );
                tx.rollback();
                return null;
            }

            String user = loginController.getName();
            if (user == null || user.isBlank()) {
                FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Nicht eingeloggt",
                        "Bitte erneut einloggen."));
                tx.rollback();
                return null;
            }
            
            //if (bergendePerson == null) bergendePerson = new Person();
            bergendePerson.setName(user.trim()); // <-- KEY FIX

            managed.setBergendePerson(bergendePerson);
            managed.setStatus(Status.Bergung_bevostehend);

            em.merge(managed);
            tx.commit();

            // Liste im Speicher aktualisieren
            for (Ghostnet g : ghostnetliste.getListe()) {
                if (g.getID().equals(managed.getID())) {
                    g.setBergendePerson(bergendePerson);
                    g.setStatus(Status.Bergung_bevostehend);
                    break;
                }
            }

        } finally {
            if (tx.isActive()) tx.rollback();
            em.close();
        }

        return null;
    }
    
    

    // Getter/Setter
    public Ghostnet getAusgewaehltesGhostnet() {
        return ausgewaehltesGhostnet;
    }

    public void setAusgewaehltesGhostnet(Ghostnet ausgewaehltesGhostnet) {
        this.ausgewaehltesGhostnet = ausgewaehltesGhostnet;
    }

    public Person getBergendePerson() {
        return bergendePerson;
    }

    public void setBergendePerson(Person bergendePerson) {
        this.bergendePerson = bergendePerson;
    }
}