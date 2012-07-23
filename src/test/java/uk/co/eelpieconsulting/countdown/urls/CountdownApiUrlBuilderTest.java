package uk.co.eelpieconsulting.countdown.urls;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

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
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?StopCode1=53550&ReturnList=LineName,DestinationText,DirectionID,EstimatedTime", urlBuilder.getStopBoardUrl(53550));
	}
	
	@Test
	public void canConstructUrlForStopSearch() throws Exception {
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?Circle=51.454,-0.351,250&ReturnList=StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude", urlBuilder.getStopSearchUrl(51.454, -0.351, 250));		
	}
	
	@Test
	public void canConstructUrlForStopIdSearch() throws Exception {
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?StopCode1=53550&ReturnList=StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude", urlBuilder.getStopIdSearchUrl(53550));
	}
	
	@Test
	public void canConstructMultipleStopDetailsQueryUrl() throws Exception {
		List<Integer> stopIds = Arrays.asList(53550,53551,53552);
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?StopCode1=53550,53551,53552&ReturnList=StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude", urlBuilder.getStopDetailsUrl(stopIds));
	}
	
	@Test
	public void canConstructStopMessagesUrl() throws Exception {
		assertEquals("http://countdown.api.tfl.gov.uk/interfaces/ura/instant_V1?StopCode1=53550&ReturnList=StopCode1,MessageUUID,MessageText,MessagePriority,StartTime,ExpireTime", urlBuilder.getStopMessagesUrl(53550));
	}

}
