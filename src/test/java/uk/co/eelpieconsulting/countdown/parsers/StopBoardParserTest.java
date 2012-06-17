package uk.co.eelpieconsulting.countdown.parsers;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.co.eelpieconsulting.countdown.model.Arrival;
import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;

public class StopBoardParserTest {

	private StopBoardParser stopBoardParser;

	@Test
	public void canParseStopBoardJsonIntoListOfArrivals() throws Exception {
		stopBoardParser = new StopBoardParser();
		
		final StopBoard stopBoard = stopBoardParser.parse(ContentLoader.loadContent("stopboard.json"));

		assertEquals(1339071391287L, stopBoard.getLastUpdated());
		assertEquals(15, stopBoard.getArrivals().size());		
		Arrival firstArrival = stopBoard.getArrivals().get(0);
		assertEquals("R70", firstArrival.getRouteName());
		assertEquals("Richmond", firstArrival.getDestination());
		assertEquals(286, firstArrival.getEstimatedWait());
	}
	
}
