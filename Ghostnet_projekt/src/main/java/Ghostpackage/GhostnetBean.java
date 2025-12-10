package Ghostpackage;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class GhostnetBean {

    private Ghostnet ghostnet = new Ghostnet();
    private Standort standort = new Standort();

    @Inject
    private Ghostnetliste ghostnetListe;   // << DER FEHLTE!

    public Ghostnet getGhostnet() {
        return ghostnet;
    }

    public Standort getStandort() {
        return standort;
    }

    public void setStandort(Standort standort) {
        this.standort = standort;
    }

    public String save() {

        ghostnet.setStandort(standort);  // Standort übernehmen

        // In globale Liste einfügen:
        ghostnetListe.getListe().add(ghostnet);

        return "Ghostnetliste.xhtml?faces-redirect=true";
    }
}