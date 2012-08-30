package uk.co.eelpieconsulting.countdown.model;

import uk.co.eelpieconsulting.busroutes.model.Route;

public class Arrival implements Comparable<Arrival> {
	
	private Route route;
	private long estimatedWait;
	
	public Arrival(Route route, long estimatedWait) {
		this.route = route;
		this.estimatedWait = estimatedWait;
	}
	
	public Route getRoute() {
		return route;
	}

	public long getEstimatedWait() {
		return estimatedWait;
	}
	
	@Override
	public int compareTo(Arrival o) {
		long diff = estimatedWait - o.getEstimatedWait();
		return diff < 0 ? -1 : 1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ (int) (estimatedWait ^ (estimatedWait >>> 32));
		result = prime * result + ((route == null) ? 0 : route.hashCode());
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
		Arrival other = (Arrival) obj;
		if (estimatedWait != other.estimatedWait)
			return false;
		if (route == null) {
			if (other.route != null)
				return false;
		} else if (!route.equals(other.route))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Arrival [route=" + route + ", estimatedWait=" + estimatedWait + "]";
	}

}
