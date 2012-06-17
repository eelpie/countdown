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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stop other = (Stop) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stop [id=" + id + ", latitude=" + latitude + ", longitude="
				+ longitude + ", name=" + name + ", stopIndicator="
				+ stopIndicator + ", towards=" + towards + "]";
	}
	
}
