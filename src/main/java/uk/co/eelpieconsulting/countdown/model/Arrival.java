package uk.co.eelpieconsulting.countdown.model;

public class Arrival {
	
	private String routeName;
	private String estimatedWait;
	private String destination;
	
	public Arrival(String routeName, String estimatedWait, String destination) {
		this.routeName = routeName;
		this.estimatedWait = estimatedWait;
		this.destination = destination;
	}

	public String getRouteName() {
		return routeName;
	}

	public String getEstimatedWait() {
		return estimatedWait;
	}

	public String getDestination() {
		return destination;
	}

	@Override
	public String toString() {
		return "Arrival [routeName=" + routeName + ", estimatedWait=" + estimatedWait + ", destination=" + destination + "]";
	}
	
}
