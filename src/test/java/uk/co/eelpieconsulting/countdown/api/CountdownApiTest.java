package uk.co.eelpieconsulting.countdown.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import uk.co.eelpieconsulting.countdown.exceptions.HttpFetchException;
import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
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
	
	private CountdownApi api;
		
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		api = new CountdownApi(countdownApiUrlBuilder, httpFetcher, stopBoardParser);
	}
	
	@Test
	public void canFetchStopboardFromLiveApiEndpoints() throws Exception {		
		when(countdownApiUrlBuilder.getStopBoardUrl(STOPBOARD_ID)).thenReturn(STOPBOARD_URL);
		when(httpFetcher.fetchContent(STOPBOARD_URL, "UTF-8")).thenReturn(STOPBOARD_JSON);
		when(stopBoardParser.parse(STOPBOARD_JSON)).thenReturn(stopBoard);
		
		StopBoard returnedStopBoard = api.getStopBoard(STOPBOARD_ID);
		
		assertEquals(stopBoard, returnedStopBoard);		
	}
	
	@Test(expected = HttpFetchException.class)
	public void shouldThrowInformativeExceptionIfHttpFetchFails() throws Exception {
		when(countdownApiUrlBuilder.getStopBoardUrl(STOPBOARD_ID)).thenReturn(STOPBOARD_URL);
		when(httpFetcher.fetchContent(STOPBOARD_URL, "UTF-8")).thenThrow(new HttpFetchException());
		
		api.getStopBoard(STOPBOARD_ID);
	}
	
	@Test(expected = ParsingException.class)
	public void shouldThrowInformativeExceptionIfParsingFails() throws Exception {
		when(countdownApiUrlBuilder.getStopBoardUrl(STOPBOARD_ID)).thenReturn(STOPBOARD_URL);
		when(httpFetcher.fetchContent(STOPBOARD_URL, "UTF-8")).thenReturn(STOPBOARD_JSON);
		when(stopBoardParser.parse(STOPBOARD_JSON)).thenThrow(new ParsingException());
		
		api.getStopBoard(STOPBOARD_ID);
	}
		
}
