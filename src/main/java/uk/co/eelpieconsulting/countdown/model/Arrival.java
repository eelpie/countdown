package uk.co.eelpieconsulting.countdown.model;

public class Arrival {
	
	private String routeName;
	private String destination;
	private long estimatedWait;
	
	public Arrival(String routeName, String destination, long estimatedWait) {
		this.routeName = routeName;
		this.estimatedWait = estimatedWait;
		this.destination = destination;
	}

	public String getRouteName() {
		return routeName;
	}

	public String getDestination() {
		return destination;
	}
	
	public long getEstimatedWait() {
		return estimatedWait;
	}

	@Override
	public String toString() {
		return "Arrival [destination=" + destination + ", estimatedWait=" + estimatedWait + ", routeName=" + routeName + "]";
	}
	
}
