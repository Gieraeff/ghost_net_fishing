package Ghostpackage;

import jakarta.persistence.*;

@Entity
public class Ghostnet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ID;
	
	@Embedded
	private Standort standort;
	private Float größe;
	
	@Enumerated(EnumType.STRING)
	Status status;
	
	@Embedded
	Person bergendePerson;

	public Ghostnet() {

	}

	
	public Ghostnet(Standort standort, Float größe,Status status, Person bergendePerson) {
		this.standort = standort;
		this.größe = größe;
		this.status = status;
		this.bergendePerson = bergendePerson;

	}
	
	public Long getID() {
        return ID;
    }

    public void setID(Long id) {
        this.ID = id;
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

	public void setGröße(Float größe) {
		this.größe = größe;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
    public Person getBergendePerson() {
        return bergendePerson;
    }

    public void setBergendePerson(Person bergendePerson) {
        this.bergendePerson = bergendePerson;
    }
	
}
