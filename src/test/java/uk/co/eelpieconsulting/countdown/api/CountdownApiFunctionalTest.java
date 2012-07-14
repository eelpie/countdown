package uk.co.eelpieconsulting.countdown.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.co.eelpieconsulting.countdown.model.Arrival;
import uk.co.eelpieconsulting.countdown.model.Stop;
import uk.co.eelpieconsulting.countdown.model.StopBoard;

public class CountdownApiFunctionalTest {
	
	private static final String LIVE_API_URL = "http://countdown.api.tfl.gov.uk";
	
	private CountdownApi api;

	@Before
	public void setup() {		
		api = new CountdownApi(LIVE_API_URL);
	}

	@Test
	public void stopBoardTest() throws Exception {		
		StopBoard stopBoard = api.getStopBoard(53550);
		
		assertNotNull(stopBoard);		
		for (Arrival arrival : stopBoard.getArrivals()) {
			System.out.println(arrival);
		}
	}
	
	@Test
	public void stopSearchTest() throws Exception {
		List<Stop> stops = api.findStopsWithin(51.454, -0.351, 1000);
		
		assertNotNull(stops);
		assertFalse(stops.isEmpty());
		for (Stop stop : stops) {
			System.out.println(stop);
		}
	}
	
	@Test
	public void canLocateStopById() throws Exception {
		final List<Stop> matches = api.findStopById(53550);
		
		assertEquals(1, matches.size());
		assertEquals(53550, matches.get(0).getId());
		assertEquals("York Street / Twickenham", matches.get(0).getName());
	}
	
	@Test
	public void canLocateStopByPublicIdentifier() throws Exception {
		final List<Stop> matches = api.findStopByPublicIdentifier("15566");
		
		assertEquals(1, matches.size());
		assertEquals(55688, matches.get(0).getId());
		assertEquals("Tayben Avenue", matches.get(0).getName());
	}
	
	// Example usage for README file
	@Test
	public void exampleUsage() throws Exception {
		final CountdownApi api = new CountdownApi("http://countdown.api.tfl.gov.uk");
		
		// Load a list of stops within a circle
		System.out.println("Searching for stops within circle");
		List<Stop> stops = api.findStopsWithin(51.448, -0.3269, 250);
		System.out.println("Found " + stops.size() + " stops");
		
		Stop firstStop = stops.get(0);
		System.out.println("This first one is: " + firstStop.getName() + " (" + firstStop.getId() + ") towards " + firstStop.getTowards() + " at " + firstStop.getLatitude() + ", " + firstStop.getLongitude());
		
		// Load a list of expected arrivals for a stop
		System.out.println("Loading arrivals for stop: " + firstStop.getId());
		StopBoard stopBoard = api.getStopBoard(firstStop.getId());
		Arrival firstArrival = stopBoard.getArrivals().get(0);
		System.out.println("Next arrival is " + firstArrival.getRouteName() + " to " + firstArrival.getDestination() + ": " + firstArrival.getEstimatedWait());
		System.out.println("Last updated: " + stopBoard.getLastUpdated());				
	}
	
}
