package uk.co.eelpieconsulting.countdown.parsers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.co.eelpieconsulting.countdown.model.Stop;

public class StopSearchParserTest {

	private StopSearchParser stopSearchParser;

	@Before
	public void setup() {
		stopSearchParser = new StopSearchParser();
	}
		
	@Test
	public void canParseMarkerSearchResultsIntoListOfStops() throws Exception {
		final List<Stop> stops = stopSearchParser.parse(ContentLoader.loadContent("marker_search.json"));
		
		assertEquals(39, stops.size());
		final Stop second = stops.get(0);
		assertEquals(55688, second.getId());
		assertEquals("15566", second.getPublicIdentifier());
		assertEquals("Tayben Avenue", second.getName());
		assertEquals("Fulwell or Isleworth", second.getTowards());
		assertEquals("B", second.getStopIndicator());
		assertEquals(51.454234, second.getLatitude(), 0);
		assertEquals(-0.342136, second.getLongitude(), 0);
	}
	
	@Test
	public void noStopIndidicatorShouldBeIndicatedByNull() throws Exception {
		final List<Stop> stops = stopSearchParser.parse(ContentLoader.loadContent("marker_search.json"));
		
		final Stop knellerRoad = stops.get(4);
		assertNull(knellerRoad.getStopIndicator());		
	}

}
