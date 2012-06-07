package uk.co.eelpieconsulting.countdown.urls;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CountdownApiUrlBuilderTest {

	private static final String API_HOST = "http://countdown.api.tfl.gov.uk";
	private CountdownApiUrlBuilder urlBuilder;
	
	@Before
	public void setup() {
		urlBuilder = new CountdownApiUrlBuilder(API_HOST);
	}
	
	@Test
	public void canConstructUrlForStopBoardJSONRequest() {				
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?StopCode1=53550&ReturnList=LineName,DestinationText,EstimatedTime", urlBuilder.getStopBoardUrl(53550));
	}
	
	@Test
	public void canConstructUrlForPlaceSearch() throws Exception {
		assertEquals("http://countdown.tfl.gov.uk/search?searchTerm=Twickenham", urlBuilder.getPlaceSearchUrl("Twickenham"));
	}
	
	@Test
	public void canConstructUrlForMarkerSearch() throws Exception {
		assertEquals("http://countdown.tfl.gov.uk/markers/swLat/51.454/swLng/-0.351/neLat/51.481/neLng/-0.307/", urlBuilder.getMarkerSearchUrl(51.454, -0.351, 51.481, -0.307));		
	}

}
