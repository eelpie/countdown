package uk.co.eelpieconsulting.countdown.parsers;
import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.Stop;

public class StopSearchParser {

	private static final String MARKERS = "markers";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String LNG = "lng";
	private static final String LAT = "lat";
	private static final String STOP_INDICATOR = "stopIndicator";
	private static final String TOWARDS = "towards";
	
	public List<Stop> parse(String stopSearchJSON) throws ParsingException {
		JSONObject stopSearch;
		try {
			stopSearch = new JSONObject(stopSearchJSON);
			if (stopSearch.has(MARKERS)) {				
				List<Stop> stops = new ArrayList<Stop>();				
				JSONArray markers = stopSearch.getJSONArray(MARKERS);
				for (int i = 0; i < markers.length(); i++) {
					stops.add(extractStopFromJson(markers.getJSONObject(i)));
				}				
				return stops;
				
			}
		} catch (JSONException e) {	
			e.printStackTrace();
			throw new ParsingException();
		}
		
		return null;
	}

	private Stop extractStopFromJson(JSONObject marker) throws JSONException {
		Stop stop = new Stop(marker.getInt(ID), marker.getString(NAME), marker.getDouble(LAT), marker.getDouble(LNG));
		if (marker.has(STOP_INDICATOR)) {
			stop.setStopIndicator(marker.getString(STOP_INDICATOR));
		}
		if (marker.has(TOWARDS)) {
			stop.setTowards(marker.getString(TOWARDS));
		}
		return stop;
	}

}
