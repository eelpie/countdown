package uk.co.eelpieconsulting.countdown.model;

import java.util.List;


public class StopBoard {

	private String lastUpdated;
	private List<Arrival> arrivals;
	
	public StopBoard(String lastUpdated, List<Arrival> arrivals) {
		this.lastUpdated = lastUpdated;
		this.arrivals = arrivals;
	}
	
	public String getLastUpdated() {
		return lastUpdated;
	}
	
	public List<Arrival> getArrivals() {
		return arrivals;
	}

	@Override
	public String toString() {
		return "StopBoard [arrivals=" + arrivals + "]";
	}
	
}
