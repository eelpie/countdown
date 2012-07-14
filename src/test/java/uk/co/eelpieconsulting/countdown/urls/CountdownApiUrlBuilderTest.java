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
	public void canConstructUrlForMarkerSearch() throws Exception {
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?Circle=51.454,-0.351,250&ReturnList=StopID,StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude", urlBuilder.getMarkerSearchUrl(51.454, -0.351, 250));		
	}
	
	@Test
	public void canConstructUrlForStopPublicIdentifierSearch() throws Exception {
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?StopID=12345&ReturnList=StopID,StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude", urlBuilder.getMarkerPublicIdentifierSearchUrl("12345"));
	}
	
	@Test
	public void canConstructUrlForStopIdSearch() throws Exception {
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?StopCode1=53550&ReturnList=StopID,StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude", urlBuilder.getStopIdSearchUrl(53550));
	}

}
