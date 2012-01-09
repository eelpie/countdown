package uk.co.eelpieconsulting.countdown.model;

public class Place {
	
	private final String name;
	private final String place;
	private final double lat;
	private final double lng;
	
	public Place(String name, String place, double lat, double lng) {
		this.name = name;
		this.place = place;
		this.lat = lat;
		this.lng = lng;
	}

	public String getName() {
		return name;
	}

	public String getPlace() {
		return place;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	@Override
	public String toString() {
		return "Place [lat=" + lat + ", lng=" + lng + ", name=" + name + ", place=" + place + "]";
	}
	
}
