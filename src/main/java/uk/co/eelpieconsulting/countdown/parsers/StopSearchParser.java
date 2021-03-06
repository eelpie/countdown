package uk.co.eelpieconsulting.countdown.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import uk.co.eelpieconsulting.busroutes.model.Stop;
import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;

public class StopSearchParser {

	private static final int STOP_NAME = 1;
	private static final int STOP_ID = 2;
	private static final int TOWARDS = 3;
	private static final int STOP_INDICATOR = 4;
	private static final int LATITUDE = 5;
	private static final int LONGITUDE = 6;
	
	private static final String NEW_LINE = "\n";
	
	public List<Stop> parse(String json) throws ParsingException {
		try {
			final String[] lines = json.split(NEW_LINE);	// The input looks non-standard; parse each line one by one	
			if (lines.length < 1) {
				throw new ParsingException();
			}
			
			final List<Stop> stops = new ArrayList<Stop>();
			for (int i = 1; i < lines.length; i++) {
				final JSONArray stopJson =  new JSONArray(lines[i]);
				if (!stopJson.isNull(STOP_ID)) {
					stops.add(new Stop(Integer.parseInt(stopJson.getString(STOP_ID)),
							stopJson.getString(STOP_NAME), 
							stopJson.isNull(TOWARDS) ? null : stopJson.getString(TOWARDS),
							stopJson.isNull(STOP_INDICATOR) ? null : stopJson.getString(STOP_INDICATOR),
							stopJson.getDouble(LATITUDE),
							stopJson.getDouble(LONGITUDE), null, null, null));
				}
			}
			return stops;
			
		} catch (JSONException e) {	
			throw new ParsingException();
		}
	}

}
