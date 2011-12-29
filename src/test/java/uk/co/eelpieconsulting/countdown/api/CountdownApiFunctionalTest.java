package uk.co.eelpieconsulting.countdown.api;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import uk.co.eelpieconsulting.countdown.model.Arrival;
import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;
import uk.co.eelpieconsulting.countdown.urls.CountdownApiUrlBuilder;
import uk.co.eelpieconsulting.countdown.util.HttpFetcher;

public class CountdownApiFunctionalTest {
	
	@Test
	public void stopBoardTest() throws Exception {
		CountdownApiUrlBuilder countdownApiUrlBuilder = new CountdownApiUrlBuilder("http://countdown.tfl.gov.uk");
		HttpFetcher httpFetcher = new HttpFetcher();		
		StopBoardParser stopBoardParser = new StopBoardParser();
		
		CountdownApi api = new CountdownApi(countdownApiUrlBuilder, httpFetcher, stopBoardParser);
		
		StopBoard stopBoard = api.getStopBoard(53550);
		
		assertNotNull(stopBoard);		
		for (Arrival arrival : stopBoard.getArrivals()) {
			System.out.println(arrival);
		}
	}

}
