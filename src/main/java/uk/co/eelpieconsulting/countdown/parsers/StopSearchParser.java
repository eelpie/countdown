package uk.co.eelpieconsulting.countdown.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.Stop;

public class StopSearchParser {

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
				if (!stopJson.isNull(3)) {
					stops.add(new Stop(Integer.parseInt(stopJson.getString(3)),
							stopJson.getString(1), stopJson.getString(4), stopJson.getString(2),
							stopJson.isNull(5) ? null : stopJson.getString(5), stopJson.getDouble(6),
							stopJson.getDouble(7)));
				}
			}
			return stops;
			
		} catch (JSONException e) {	
			throw new ParsingException();
		}
	}

}
