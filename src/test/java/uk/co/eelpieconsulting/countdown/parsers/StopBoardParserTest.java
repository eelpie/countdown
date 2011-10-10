package uk.co.eelpieconsulting.countdown.parsers;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.co.eelpieconsulting.countdown.model.Arrival;
import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;

public class StopBoardParserTest {

	private StopBoardParser stopBoardParser;

	@Test
	public void canParseMarkerSearchResultsIntoListOfStops() throws Exception {
		stopBoardParser = new StopBoardParser();
		
		StopBoard stopBoard = stopBoardParser.parse(ContentLoader.loadContent("stopboard.json"));
	
		assertEquals("09:12", stopBoard.getLastUpdated());
		assertEquals(19, stopBoard.getArrivals().size());		
		Arrival firstArrival = stopBoard.getArrivals().get(0);
		assertEquals("267", firstArrival.getRouteName());
		assertEquals("Fulwell", firstArrival.getDestination());
		assertEquals("due", firstArrival.getEstimatedWait());
	}
	
}
