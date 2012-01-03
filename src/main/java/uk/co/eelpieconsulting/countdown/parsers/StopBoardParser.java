package uk.co.eelpieconsulting.countdown.parsers;

import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.Arrival;
import uk.co.eelpieconsulting.countdown.model.StopBoard;

public class StopBoardParser {

	public StopBoard parse(String json) throws ParsingException {
		try {			
			JSONObject stopBoardJSON = new JSONObject(json);			
			if (stopBoardJSON.has("arrivals")) {
				List<Arrival> arrivals = new ArrayList<Arrival>();
				
				JSONArray jsonArrivals = stopBoardJSON.getJSONArray("arrivals");
				for (int i = 0; i < jsonArrivals.length(); i++) {
					JSONObject arrival = jsonArrivals.getJSONObject(i);					
					arrivals.add(new Arrival(arrival.getString("routeName"), arrival.getString("estimatedWait"), arrival.getString("destination")));
				}
				StopBoard stopBoard = new StopBoard(stopBoardJSON.getString("lastUpdated"), arrivals);
				return stopBoard;
			}			
			
		} catch (JSONException e) {	
			e.printStackTrace();
			throw new ParsingException();
		}
		
		return null;
	}

}
