package uk.co.eelpieconsulting.countdown.parsers;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import uk.co.eelpieconsulting.countdown.model.BoundingBox;
import uk.co.eelpieconsulting.countdown.model.Place;
import uk.co.eelpieconsulting.countdown.model.PlaceSearchResult;

public class PlaceSearchParserTest {

	private PlaceSearchParser placeSearchParser;

	@Before
	public void setup() {
		placeSearchParser = new PlaceSearchParser();
	}
	
	@Test
	public void canParseBoundingBoxFromResult() throws Exception {		
		PlaceSearchResult result = placeSearchParser.parse(ContentLoader.loadContent("placename_search.json"));
		
		final BoundingBox boundingBox = result.getBoundingBox();
		assertEquals(51.37137, boundingBox.getSwLat(), 0);
		assertEquals(-0.38594, boundingBox.getSwLng(), 0);
		assertEquals(51.60717, boundingBox.getNeLat(), 0);
		assertEquals(-0.00135, boundingBox.getNeLng(), 0);	
	}

	@Test
	public void canParsePlacesFromResult() throws Exception {
		PlaceSearchResult result = placeSearchParser.parse(ContentLoader.loadContent("placename_search.json"));

		assertNotNull(result.getPlaces());
		assertEquals(13, result.getPlaces().size());
		
		Place firstPlace = result.getPlaces().get(0);
		assertEquals("Twickenham", firstPlace.getName());
		assertEquals("London", firstPlace.getPlace());
		assertEquals(51.44688, firstPlace.getLat(), 0);
		assertEquals(-0.33434, firstPlace.getLng(), 0);
	}
	
}
