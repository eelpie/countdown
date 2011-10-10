package uk.co.eelpieconsulting.countdown.parsers;
import static org.junit.Assert.assertEquals;

import java.util.List;


import org.junit.Test;

import uk.co.eelpieconsulting.countdown.model.Stop;
import uk.co.eelpieconsulting.countdown.parsers.StopSearchParser;

public class StopSearchParserTest {

	private StopSearchParser stopSearchParser;

	@Test
	public void canParseMarkerSearchResultsIntoListOfStops() throws Exception {
		stopSearchParser = new StopSearchParser();
		
		List<Stop> stops = stopSearchParser.parse(ContentLoader.loadContent("marker_search.json"));

		assertEquals(88, stops.size());
		Stop first = stops.get(0);
		assertEquals("51260", first.getId());
		assertEquals("Bandon Hill Cemetery", first.getName());
		assertEquals(51.36607832812416, first.getLatitude(), 0);
		assertEquals(-0.13217236388347467, first.getLongitude(), 0);
		assertEquals("A", first.getStopIndicator());
		assertEquals("CROYDON", first.getTowards());
	}

}
