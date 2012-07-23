package uk.co.eelpieconsulting.countdown.parsers;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uk.co.eelpieconsulting.busroutes.model.Message;

public class StopMessageParserTest {

	private StopMessageParser stopMessageParser;

	@Before
	public void setup() {
		stopMessageParser = new StopMessageParser();
	}
		
	@Test
	public void canParseMarkerSearchResultsIntoListOfStops() throws Exception {
		final List<Message> messages = stopMessageParser.parse(ContentLoader.loadContent("messages.json"));
		
		assertEquals(3, messages.size());
		
		final Message message = messages.get(2);
		assertEquals("8a56a0ab386fc495013870454e1e0216_BP2326", message.getId());
		assertEquals(53551, message.getStopId());
		assertEquals("Route 188 - Until 10 September route 188 is extended from Russell Square to Euston Bus Station via Woburn Place, Tavistock Square & Upper Woburn Place.", message.getMessage());
	}

}
