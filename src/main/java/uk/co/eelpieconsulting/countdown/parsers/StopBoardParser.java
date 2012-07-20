package uk.co.eelpieconsulting.countdown.parsers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import uk.co.eelpieconsulting.busroutes.model.Route;
import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;
import uk.co.eelpieconsulting.countdown.model.Arrival;
import uk.co.eelpieconsulting.countdown.model.StopBoard;

public class StopBoardParser {

	private static final int ROUTE = 1;
	private static final int DIRECTION = 0;
	private static final int DESTINATION = 2;
	
	private static final String NEW_LINE = "\n";
	
	public StopBoard parse(final String json) throws ParsingException {
		try {			
			final String[] lines = json.split(NEW_LINE);	// The input looks non-standard; parse each line one by one			
			if (lines.length < 1) {
				throw new ParsingException();
			}
			
			final JSONArray headerJson = new JSONArray(lines[0]);						
			final long timestamp = headerJson.getLong(2);
			
			final List<Arrival> arrivals = new ArrayList<Arrival>();
			for (int i = 1; i < lines.length; i++) {
				final JSONArray arrivalJson =  new JSONArray(lines[i]);
				long estimatedWait = (Long.parseLong(arrivalJson.getString(3)) - timestamp) / 1000;
				
				final Route route = new Route(arrivalJson.getString(ROUTE), arrivalJson.getInt(DIRECTION), arrivalJson.getString(DESTINATION));				
				arrivals.add(new Arrival(route, estimatedWait));
			}
			Collections.sort(arrivals);			
			return new StopBoard(timestamp, arrivals);
			
		} catch (JSONException e) {	
			throw new ParsingException();
		}
	}

}
