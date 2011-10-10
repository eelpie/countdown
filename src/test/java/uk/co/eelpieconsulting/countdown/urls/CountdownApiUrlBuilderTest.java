package uk.co.eelpieconsulting.countdown.urls;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CountdownApiUrlBuilderTest {

	private static final String API_HOST = "http://countdown.tfl.gov.uk";
	
	@Test
	public void canConstructUrlForStopBoardJSONRequest() {		
		CountdownApiUrlBuilder urlBuilder = new CountdownApiUrlBuilder(API_HOST);
		
		assertEquals("http://countdown.tfl.gov.uk/stopBoard/53550", urlBuilder.getStopBoardUrl("53550"));
	}

}
