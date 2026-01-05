package Ghostpackage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String passwort;

    private List<Benutzer> benutzerListe = new ArrayList<>();
    private boolean eingeloggt;

    public LoginController() {
        // Beispiel-Anmeldeliste wie in deinem ToDo-Projekt
        benutzerListe.add(new Benutzer("Admin", "123"));
        benutzerListe.add(new Benutzer("Bergung1", "456"));
        benutzerListe.add(new Benutzer("Bergung2", "789"));
    }

    public String login() {

        Benutzer eingegeben = new Benutzer(name, passwort);

        for (Benutzer b : benutzerListe) {
            if (b.equals(eingegeben)) {
                eingeloggt = true;
               return "Ghostnetliste.xhtml?faces-redirect=true"; 
            }
        }

        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login fehlgeschlagen",
                        "Benutzername oder Passwort ist falsch.")
        );
        eingeloggt = false;
        return null;
    }

    public String logout() {
        eingeloggt = false;
        name = null;
        passwort = null;
        return "index.xhtml?faces-redirect=true";
    }

    // Getter / Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public boolean isEingeloggt() {
        return eingeloggt;
    }

    public void setEingeloggt(boolean eingeloggt) {
        this.eingeloggt = eingeloggt;
    }

    public List<Benutzer> getBenutzerListe() {
        return benutzerListe;
    }
}
