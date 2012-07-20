package uk.co.eelpieconsulting.countdown.parsers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uk.co.eelpieconsulting.countdown.model.Arrival;
import uk.co.eelpieconsulting.countdown.model.StopBoard;

public class StopBoardParserTest {

	private StopBoardParser stopBoardParser;

	@Before
	public void setup() {
		stopBoardParser = new StopBoardParser();
	}
	
	@Test
	public void canParseStopBoardJsonIntoListOfArrivals() throws Exception {
		final StopBoard stopBoard = stopBoardParser.parse(ContentLoader.loadContent("stopboard.json"));

		assertEquals(1339071391287L, stopBoard.getLastUpdated());
		assertEquals(15, stopBoard.getArrivals().size());		
		Arrival firstArrival = stopBoard.getArrivals().get(0);
		assertEquals("33", firstArrival.getRoute().getRoute());
		assertEquals(1, firstArrival.getRoute().getRun());
		assertEquals("Hammersmith", firstArrival.getRoute().getTowards());
		assertEquals(47, firstArrival.getEstimatedWait());
	}
	
	@Test
	public void arrivalsShouldBeSortedByEstimatedWait() throws Exception {
		final StopBoard stopBoard = stopBoardParser.parse(ContentLoader.loadContent("stopboard.json"));
		long previousWait = 0;
		stopBoard.getArrivals();
		for (Arrival arrival : stopBoard.getArrivals()) {
			assertTrue(arrival.getEstimatedWait() >= previousWait);
			previousWait = arrival.getEstimatedWait();
		}		
	}
	
}
