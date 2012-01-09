package uk.co.eelpieconsulting.countdown.api;

import java.util.List;

import uk.co.eelpieconsulting.countdown.exceptions.HttpFetchException;
import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.PlaceSearchResult;
import uk.co.eelpieconsulting.countdown.model.Stop;
import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.PlaceSearchParser;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;
import uk.co.eelpieconsulting.countdown.parsers.StopSearchParser;
import uk.co.eelpieconsulting.countdown.urls.CountdownApiUrlBuilder;
import uk.co.eelpieconsulting.countdown.util.HttpFetcher;

public class CountdownApi {
	
	private CountdownApiUrlBuilder countdownApiUrlBuilder;
	private HttpFetcher httpFetcher;
	private StopBoardParser stopBoardParser;
	private StopSearchParser stopSearchParser;
	private PlaceSearchParser placeSearchParser;
	
	public CountdownApi(CountdownApiUrlBuilder countdownApiUrlBuilder, HttpFetcher httpFetcher, StopBoardParser stopBoardParser, StopSearchParser stopSearchParser, PlaceSearchParser placeSearchParser) {
		this.countdownApiUrlBuilder = countdownApiUrlBuilder;
		this.httpFetcher = httpFetcher;
		this.stopBoardParser = stopBoardParser;
		this.stopSearchParser = stopSearchParser;
		this.placeSearchParser = placeSearchParser;
	}
	
	public StopBoard getStopBoard(int stopId) throws HttpFetchException, ParsingException {
		return stopBoardParser.parse(httpFetcher.fetchContent(countdownApiUrlBuilder.getStopBoardUrl(stopId), "UTF-8"));
	}

	public List<Stop> findStopsWithin(double swLat, double swLng, double neLat, double neLng) throws HttpFetchException, ParsingException {
		return stopSearchParser.parse(httpFetcher.fetchContent(countdownApiUrlBuilder.getMarkerSearchUrl(swLat, swLng, neLat, neLng), "UTF-8"));
	}

	public PlaceSearchResult searchForPlaces(String searchTerm) throws HttpFetchException, ParsingException {
		return placeSearchParser.parse(httpFetcher.fetchContent(countdownApiUrlBuilder.getPlaceSearchUrl(searchTerm), "UTF-8"));
	}

}
