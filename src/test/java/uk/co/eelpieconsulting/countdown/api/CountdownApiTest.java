package uk.co.eelpieconsulting.countdown.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;
import uk.co.eelpieconsulting.countdown.urls.CountdownApiUrlBuilder;
import uk.co.eelpieconsulting.countdown.util.HttpFetcher;

public class CountdownApiTest {
	
	private static final int STOPBOARD_ID = 123;
	private static final String STOPBOARD_URL = "http://stopboard/123";
	private static final String STOPBOARD_JSON = "{some json}";
	
	@Mock StopBoard stopBoard;
	
	@Mock CountdownApiUrlBuilder countdownApiUrlBuilder;
	@Mock HttpFetcher httpFetcher;	
	@Mock StopBoardParser stopBoardParser;
	
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void canFetchStopboardFromLiveApiEndpoints() throws Exception {		
		CountdownApi api = new CountdownApi(countdownApiUrlBuilder, httpFetcher, stopBoardParser);
		when(countdownApiUrlBuilder.getStopBoardUrl(123)).thenReturn(STOPBOARD_URL);
		when(httpFetcher.fetchContent(STOPBOARD_URL, "UTF-8")).thenReturn(STOPBOARD_JSON);
		when(stopBoardParser.parse(STOPBOARD_JSON)).thenReturn(stopBoard);
		
		StopBoard returnedStopBoard = api.getStopBoard(STOPBOARD_ID);
		
		assertEquals(stopBoard, returnedStopBoard);		
	}
	
}
