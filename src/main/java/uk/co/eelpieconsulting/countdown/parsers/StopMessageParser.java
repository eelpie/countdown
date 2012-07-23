package uk.co.eelpieconsulting.countdown.parsers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import uk.co.eelpieconsulting.busroutes.model.Message;
import uk.co.eelpieconsulting.countdown.exceptions.ParsingException;

public class StopMessageParser {
	
	private static final String NEW_LINE = "\n";

	public List<Message> parse(String json) throws ParsingException {
		final String[] lines = json.split(NEW_LINE);	// The input looks non-standard; parse each line one by one			
		if (lines.length < 1) {
			throw new ParsingException();
		}
		
		try {
			final List<Message> messages = new ArrayList<Message>();
			for (int i = 1; i < lines.length; i++) {
				final JSONArray messageJson =  new JSONArray(lines[i]);
				final Message message = new Message(messageJson.getString(2), 
						Integer.parseInt(messageJson.getString(1)),
						messageJson.getString(4).trim(),
						Integer.parseInt(messageJson.getString(3)),
						messageJson.getLong(5),
						messageJson.getLong(6));					
				messages.add(message);
			}
			return messages;
			
		} catch (JSONException e) {
			e.printStackTrace();
			throw new ParsingException();
		}
	}

}
