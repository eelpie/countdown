package uk.co.eelpieconsulting.countdown.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.BoundingBox;
import uk.co.eelpieconsulting.countdown.model.Place;
import uk.co.eelpieconsulting.countdown.model.PlaceSearchResult;

public class PlaceSearchParser {

	private static final String PLACE_RESULTS = "placeResults";
	private static final String RESULTS = "results";
	private static final String NE_LNG_BB = "neLngBB";
	private static final String NE_LAT_BB = "neLatBB";
	private static final String SW_LNG_BB = "swLngBB";
	private static final String SW_LAT_BB = "swLatBB";

	public PlaceSearchResult parse(String json) throws ParsingException {
		try {
			JSONObject resultJSON = new JSONObject(json);
			
			if (resultJSON.has(RESULTS)) {				
				JSONObject results = (JSONObject) resultJSON.get(RESULTS);
			
				final boolean resultHasBoundingBox = results.has(SW_LAT_BB) && results.has(SW_LNG_BB) && results.has(NE_LAT_BB) && results.has(NE_LNG_BB);
				final boolean resultHasPlacesList = results.has(PLACE_RESULTS);
				if (resultHasBoundingBox && resultHasPlacesList) {				
					final BoundingBox boundingBox = new BoundingBox(
							results.getDouble(SW_LAT_BB), results.getDouble(SW_LNG_BB),
							results.getDouble(NE_LAT_BB), results.getDouble(NE_LNG_BB));

					List<Place> places = new ArrayList<Place>();				
					JSONArray resultsPlaces = results.getJSONArray(PLACE_RESULTS);
					for (int i = 0; i < resultsPlaces.length(); i++) {
						JSONObject placeJSON = resultsPlaces.getJSONObject(i);
						places.add(new Place(placeJSON.getString("name"), placeJSON.getString("place"), placeJSON.getDouble("lat"), placeJSON.getDouble("lng")));
					}
					
					return new PlaceSearchResult(boundingBox, places);				
				}
			}
			
		} catch (JSONException e) {	
			e.printStackTrace();
			throw new ParsingException();
		}		
		return null;
	}

}
