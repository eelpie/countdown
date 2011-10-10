package uk.co.eelpieconsulting.countdown.model;

public class Stop {
	
	private String id;
	private String name;
	private double latitude;
	private double longitude;
	private String stopIndicator;
	private String towards;
	
	public Stop(String id, String name, double latitude, double longitude) {
		this.id = id;
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public void setStopIndicator(String stopIndicator) {
		this.stopIndicator = stopIndicator;
	}
	
	public void setTowards(String towards) {
		this.towards = towards;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public String getStopIndicator() {
		return stopIndicator;
	}

	public String getTowards() {
		return towards;
	}

	@Override
	public String toString() {
		return "Stop [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude + ", stopIndicator=" + stopIndicator + ", towards=" + towards + "]";
	}
	
}
