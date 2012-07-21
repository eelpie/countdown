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
		
		assertEquals(1342904780865L, stopBoard.getLastUpdated());
		assertEquals(10, stopBoard.getArrivals().size());
		
		final Arrival firstArrival = stopBoard.getArrivals().get(0);
		assertEquals("H22", firstArrival.getRoute().getRoute());
		assertEquals(1, firstArrival.getRoute().getRun());
		assertEquals("Richmond", firstArrival.getRoute().getTowards());
		assertEquals(161, firstArrival.getEstimatedWait());
		
		final Arrival otherDirectionArrival = stopBoard.getArrivals().get(3);
		assertEquals("490", otherDirectionArrival.getRoute().getRoute());
		assertEquals(2, otherDirectionArrival.getRoute().getRun());
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
