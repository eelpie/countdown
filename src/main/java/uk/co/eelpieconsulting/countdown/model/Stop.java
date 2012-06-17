package uk.co.eelpieconsulting.countdown.model;

import java.io.Serializable;

public class Stop implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private String towards;
	private String stopIndicator;
	private double latitude;
	private double longitude;
	
	public Stop(int id, String name, String towards, String stopIndicator, double latitude, double longitude) {
		this.id = id;
		this.name = name;
		this.towards = towards;
		this.stopIndicator = stopIndicator;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getTowards() {
		return towards;
	}

	public String getStopIndicator() {
		return stopIndicator;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "Stop [id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + ", name=" + name + ", stopIndicator="
				+ stopIndicator + ", towards=" + towards + "]";
	}
	
}
