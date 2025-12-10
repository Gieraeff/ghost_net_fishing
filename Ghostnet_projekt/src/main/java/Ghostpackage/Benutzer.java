package Ghostpackage;

import java.util.Objects;

public class Benutzer {

    private String name;
    private String passwort;

    public Benutzer() {
    }

    public Benutzer(String name, String passwort) {
        this.name = name;
        this.passwort = passwort;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Benutzer)) return false;
        Benutzer that = (Benutzer) o;
        return Objects.equals(name, that.name)
                && Objects.equals(passwort, that.passwort);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, passwort);
    }
}
