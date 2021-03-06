package uk.co.eelpieconsulting.countdown.api;

import java.util.List;

import uk.co.eelpieconsulting.busroutes.model.Message;
import uk.co.eelpieconsulting.busroutes.model.Stop;
import uk.co.eelpieconsulting.common.http.HttpFetchException;
import uk.co.eelpieconsulting.common.http.HttpFetcher;
import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;
import uk.co.eelpieconsulting.countdown.parsers.StopMessageParser;
import uk.co.eelpieconsulting.countdown.parsers.StopSearchParser;
import uk.co.eelpieconsulting.countdown.urls.CountdownApiUrlBuilder;

public class CountdownApi {
	
	private final CountdownApiUrlBuilder countdownApiUrlBuilder;
	private final StopBoardParser stopBoardParser;
	private final StopSearchParser stopSearchParser;
	private final StopMessageParser stopMessageParser;
	private final HttpFetcher httpFetcher;

	public CountdownApi(String apiUrl) {
		this.countdownApiUrlBuilder = new CountdownApiUrlBuilder(apiUrl);
		this.stopBoardParser = new StopBoardParser();
		this.stopSearchParser = new StopSearchParser();
		this.stopMessageParser = new StopMessageParser();
		this.httpFetcher = new HttpFetcher();
	}
	
	public CountdownApi(CountdownApiUrlBuilder countdownApiUrlBuilder, HttpFetcher httpFetcher, StopBoardParser stopBoardParser, StopSearchParser stopSearchParser, StopMessageParser stopMessageParser) {
		this.countdownApiUrlBuilder = countdownApiUrlBuilder;
		this.stopBoardParser = stopBoardParser;
		this.stopSearchParser = stopSearchParser;
		this.stopMessageParser = stopMessageParser;
		this.httpFetcher = httpFetcher;
	}
	
	public StopBoard getStopBoard(int stopId) throws HttpFetchException, ParsingException {
		return stopBoardParser.parse(httpFetcher.get(countdownApiUrlBuilder.getStopBoardUrl(stopId)));
	}

	public List<Stop> findStopsWithin(double latitude, double longitude, int radius) throws HttpFetchException, ParsingException {
		return stopSearchParser.parse(httpFetcher.get(countdownApiUrlBuilder.getStopSearchUrl(latitude, longitude, radius)));
	}
	
	public List<Stop> getStopDetails(List<Integer> stopIds) throws HttpFetchException, ParsingException {
		return stopSearchParser.parse(httpFetcher.get(countdownApiUrlBuilder.getStopDetailsUrl(stopIds)));
	}
	
	public List<Message> getStopMessages(int stopId) throws HttpFetchException, ParsingException {
		return stopMessageParser.parse(httpFetcher.get(countdownApiUrlBuilder.getStopMessagesUrl(stopId)));
	}
	
	public List<Stop> findStopById(int id) throws HttpFetchException, ParsingException {
		return stopSearchParser.parse(httpFetcher.get(countdownApiUrlBuilder.getStopIdSearchUrl(id)));
	}
	
}
