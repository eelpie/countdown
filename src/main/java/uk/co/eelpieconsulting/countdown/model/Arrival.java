package uk.co.eelpieconsulting.countdown.model;

public class Arrival implements Comparable<Arrival> {
	
	private String routeName;
	private int direction;
	private String destination;
	private long estimatedWait;
	
	public Arrival(String routeName, int direction, String destination, long estimatedWait) {
		this.routeName = routeName;
		this.direction = direction;
		this.estimatedWait = estimatedWait;
		this.destination = destination;
	}

	public String getRouteName() {
		return routeName;
	}
	
	public int getDirection() {
		return direction;
	}

	public String getDestination() {
		return destination;
	}
	
	public long getEstimatedWait() {
		return estimatedWait;
	}
	
	@Override
	public String toString() {
		return "Arrival [destination=" + destination + ", direction=" + direction + ", estimatedWait=" + estimatedWait + ", routeName=" + routeName + "]";
	}

	@Override
	public int compareTo(Arrival o) {
		long diff = estimatedWait - o.getEstimatedWait();
		return diff < 0 ? -1 : 1;
	}
	
}
