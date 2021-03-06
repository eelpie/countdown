package uk.co.eelpieconsulting.countdown.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.eelpieconsulting.busroutes.model.Stop;
import uk.co.eelpieconsulting.common.http.HttpFetchException;
import uk.co.eelpieconsulting.common.http.HttpFetcher;
import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;
import uk.co.eelpieconsulting.countdown.parsers.StopMessageParser;
import uk.co.eelpieconsulting.countdown.parsers.StopSearchParser;
import uk.co.eelpieconsulting.countdown.urls.CountdownApiUrlBuilder;

public class CountdownApiTest {
	
	private static final int STOPBOARD_ID = 123;
	private static final String STOPBOARD_URL = "http://stopboard/123";
	private static final String STOPBOARD_JSON = "{some json}";
	private static final String STOP_SEARCH_URL = "http://stopsearch/bounding/box";
	private static final String STOP_SEARCH_JSON = "{some json with stops in it}";
	private static final double LAT = 1;
	private static final double LNG = -1;
	private static final int RADIUS = 250;
	
	@Mock CountdownApiUrlBuilder countdownApiUrlBuilder;
	@Mock HttpFetcher httpFetcher;	
	@Mock StopBoardParser stopBoardParser;
	@Mock StopSearchParser stopSearchParser;
	@Mock StopMessageParser stopMessageParser;
	
	@Mock StopBoard stopBoard;
	@Mock List<Stop> stops;
	
	private CountdownApi api;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		api = new CountdownApi(countdownApiUrlBuilder, httpFetcher, stopBoardParser, stopSearchParser, stopMessageParser);
	}
	
	@Test
	public void canFetchStopboardFromLiveApiEndpoints() throws Exception {		
		when(countdownApiUrlBuilder.getStopBoardUrl(STOPBOARD_ID)).thenReturn(STOPBOARD_URL);
		when(httpFetcher.get(STOPBOARD_URL)).thenReturn(STOPBOARD_JSON);
		when(stopBoardParser.parse(STOPBOARD_JSON)).thenReturn(stopBoard);
		
		StopBoard returnedStopBoard = api.getStopBoard(STOPBOARD_ID);
		
		assertEquals(stopBoard, returnedStopBoard);		
	}
	
	@Test(expected = HttpFetchException.class)
	public void shouldThrowInformativeExceptionIfHttpFetchFails() throws Exception {
		when(countdownApiUrlBuilder.getStopBoardUrl(STOPBOARD_ID)).thenReturn(STOPBOARD_URL);
		when(httpFetcher.get(STOPBOARD_URL)).thenThrow(new HttpFetchException(new RuntimeException("Something unexpected")));
		
		api.getStopBoard(STOPBOARD_ID);
	}
	
	@Test(expected = ParsingException.class)
	public void shouldThrowInformativeExceptionIfParsingFails() throws Exception {
		when(countdownApiUrlBuilder.getStopBoardUrl(STOPBOARD_ID)).thenReturn(STOPBOARD_URL);
		when(httpFetcher.get(STOPBOARD_URL)).thenReturn(STOPBOARD_JSON);
		when(stopBoardParser.parse(STOPBOARD_JSON)).thenThrow(new ParsingException());
		
		api.getStopBoard(STOPBOARD_ID);
	}
	
	@Test
	public void canSearchForStopsWithinBoundingBox() throws Exception {
		when(countdownApiUrlBuilder.getStopSearchUrl(LAT, LNG, RADIUS)).thenReturn(STOP_SEARCH_URL);
		when(httpFetcher.get(STOP_SEARCH_URL)).thenReturn(STOP_SEARCH_JSON);
		when(stopSearchParser.parse(STOP_SEARCH_JSON)).thenReturn(stops);
		
		List<Stop> returnedStops = api.findStopsWithin(LAT, LNG, RADIUS);
		
		assertEquals(stops, returnedStops);
	}
	
}
