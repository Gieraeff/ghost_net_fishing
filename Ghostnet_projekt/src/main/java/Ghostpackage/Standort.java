package Ghostpackage;

public class Standort {

	
	 private double latitude;   // Breitengrad (DD)
	 private double longitude;  // Längengrad (DD)

	    public Standort() {}

	    public Standort(double latitude, double longitude) {
	        this.latitude = latitude;
	        this.longitude = longitude;
	    }
	    
	    // Getter & Setter
	    public double getLatitude() {
	        return latitude;
	    }

	    public void setLatitude(double latitude) {
	        this.latitude = latitude;
	    }

	    public double getLongitude() {
	        return longitude;
	    }

	    public void setLongitude(double longitude) {
	        this.longitude = longitude;
	    }
}