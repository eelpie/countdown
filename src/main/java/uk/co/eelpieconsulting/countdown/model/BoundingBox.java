package uk.co.eelpieconsulting.countdown.model;

public class BoundingBox {
	
	private final double swLat;
	private final double swLng;
	private final double neLat;
	private final double neLng;
	
	public BoundingBox(double swLat, double swLng, double neLat, double nwLng) {
		this.swLat = swLat;
		this.swLng = swLng;
		this.neLat = neLat;
		this.neLng = nwLng;
	}

	public double getSwLat() {
		return swLat;
	}

	public double getSwLng() {
		return swLng;
	}

	public double getNeLat() {
		return neLat;
	}

	public double getNeLng() {
		return neLng;
	}

	@Override
	public String toString() {
		return "BoundingBox [swLat=" + swLat + ", swLng=" + swLng + ", neLat=" + neLat + ", neLng=" + neLng + "]";
	}
	
}
