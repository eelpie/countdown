package uk.co.eelpieconsulting.countdown.api;

import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;
import uk.co.eelpieconsulting.countdown.urls.CountdownApiUrlBuilder;
import uk.co.eelpieconsulting.countdown.util.HttpFetcher;

public class CountdownApi {
	
	private CountdownApiUrlBuilder countdownApiUrlBuilder;
	private HttpFetcher httpFetcher;
	private StopBoardParser stopBoardParser;
	
	public CountdownApi(CountdownApiUrlBuilder countdownApiUrlBuilder, HttpFetcher httpFetcher, StopBoardParser stopBoardParser) {
		this.countdownApiUrlBuilder = countdownApiUrlBuilder;
		this.httpFetcher = httpFetcher;
		this.stopBoardParser = stopBoardParser;
	}
	
	public StopBoard getStopBoard(int stopId) {
		return stopBoardParser.parse(httpFetcher.fetchContent(countdownApiUrlBuilder.getStopBoardUrl(stopId), "UTF-8"));
	}

}
