package Ghostpackage;

import jakarta.persistence.*;

@Entity
public class Ghostnet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int ID;
	private Standort standort;
	private Float größe;
	Status status;
	Person BergendePerson;

	public Ghostnet() {

	}
	
	public Ghostnet(Standort standort, Float größe) {
		this.standort = standort;
		this.größe = größe;
	}
	
	public Ghostnet(Standort standort, Float größe,Status status, Person bergendePerson) {
		super();
		this.größe = größe;
		this.BergendePerson = bergendePerson;

	}

	public Standort getStandort() {
		return standort;
	}

	public void setStandort(Standort standort) {
		this.standort = standort;
	}

	public Float getGröße() {
		return größe;
	}

	public void setgröße(Float größe) {
		this.größe = größe;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setstatus(Status status) {
		this.status = status;
	}
}
