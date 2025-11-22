package lektion2;

public class Aufgabe {
	private String text;
	private String person;

	public Aufgabe(String text, String person) {
		this.text = text;
		this.person = person;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
