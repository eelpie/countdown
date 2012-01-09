package uk.co.eelpieconsulting.countdown.api;

import java.util.List;

import uk.co.eelpieconsulting.countdown.exceptions.HttpFetchException;
import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.BoundingBox;
import uk.co.eelpieconsulting.countdown.model.PlaceSearchResult;
import uk.co.eelpieconsulting.countdown.model.Stop;
import uk.co.eelpieconsulting.countdown.model.StopBoard;
import uk.co.eelpieconsulting.countdown.parsers.PlaceSearchParser;
import uk.co.eelpieconsulting.countdown.parsers.StopBoardParser;
import uk.co.eelpieconsulting.countdown.parsers.StopSearchParser;
import uk.co.eelpieconsulting.countdown.urls.CountdownApiUrlBuilder;
import uk.co.eelpieconsulting.countdown.util.HttpFetcher;

public class CountdownApi {
	
	private static final double RADIUS_OF_THE_EARTH_IN_KILOMETERS = 6370;
	private static final double CIRCUMFERENCE_OF_THE_EARTH_IN_METERS = RADIUS_OF_THE_EARTH_IN_KILOMETERS * 1000 * Math.PI;
	
	final private CountdownApiUrlBuilder countdownApiUrlBuilder;
	final private HttpFetcher httpFetcher;
	final private StopBoardParser stopBoardParser;
	final private StopSearchParser stopSearchParser;
	final private PlaceSearchParser placeSearchParser;

	public CountdownApi(String apiUrl) {
		this.countdownApiUrlBuilder = new CountdownApiUrlBuilder(apiUrl);
		this.httpFetcher = new HttpFetcher();
		this.stopBoardParser = new StopBoardParser();
		this.stopSearchParser = new StopSearchParser();
		this.placeSearchParser = new PlaceSearchParser();
	}
	
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

	public List<Stop> findStopsWithin(BoundingBox boundingBox) throws HttpFetchException, ParsingException {
		return stopSearchParser.parse(httpFetcher.fetchContent(countdownApiUrlBuilder.getMarkerSearchUrl(boundingBox.getSwLat(), boundingBox.getSwLng(), boundingBox.getNeLat(), boundingBox.getNeLng()), "UTF-8"));
	}
	
	public List<Stop> findStopsWithinApproximateRadiusOf(double latitide, double longitude, double radius) throws HttpFetchException, ParsingException {		
		BoundingBox boundingBox = createApproximateBoundingBoxFor(latitide, longitude, radius);
		return findStopsWithin(boundingBox);
	}

	public PlaceSearchResult searchForPlaces(String searchTerm) throws HttpFetchException, ParsingException {
		return placeSearchParser.parse(httpFetcher.fetchContent(countdownApiUrlBuilder.getPlaceSearchUrl(searchTerm), "UTF-8"));
	}

	private BoundingBox createApproximateBoundingBoxFor(double latitide, double longitude, double radius) {
		double offset = (radius / CIRCUMFERENCE_OF_THE_EARTH_IN_METERS) * 360;		
		BoundingBox boundingBox = new BoundingBox(latitide - offset, longitude - offset, latitide + offset, longitude + offset);
		return boundingBox;
	}
	
}
