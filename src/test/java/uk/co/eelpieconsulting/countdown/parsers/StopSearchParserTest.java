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
		
		final List<Stop> stops = stopSearchParser.parse(ContentLoader.loadContent("marker_search.json"));
		
		assertEquals(5, stops.size());
		final Stop second = stops.get(1);
		assertEquals(72682, second.getId());
		assertEquals("Kneller Hall", second.getName());
		assertEquals("Fulwell or Isleworth", second.getTowards());
		assertEquals("A", second.getStopIndicator());
		assertEquals(51.453813, second.getLatitude(), 0);
		assertEquals(-0.347995, second.getLongitude(), 0);
	}

}
