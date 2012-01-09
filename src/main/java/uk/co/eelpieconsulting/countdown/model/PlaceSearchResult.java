package uk.co.eelpieconsulting.countdown.model;

import java.util.List;

public class PlaceSearchResult {

	final private BoundingBox boundingBox;
	final private List<Place> places;
	
	public PlaceSearchResult(BoundingBox boundingBox, List<Place> places) {
		this.boundingBox = boundingBox;
		this.places = places;
	}

	public BoundingBox getBoundingBox() {
		return boundingBox;
	}

	public List<Place> getPlaces() {
		return places;
	}
	
}
