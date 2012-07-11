package uk.co.eelpieconsulting.countdown.urls;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class CountdownApiUrlBuilder {

	private static final String STOP_SEARCH_RETURN_CODES = "StopID,StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude";
	
	private String apiUrl;

	public CountdownApiUrlBuilder(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public String getStopBoardUrl(int stopId) {
		return apiUrl + "/interfaces/ura/instant_V1?StopCode1=" + stopId + "&ReturnList=LineName,DestinationText,EstimatedTime";
	}
	
	public String getMarkerSearchUrl(double latitude, double longitude, int radius) {
		return apiUrl + "/interfaces/ura/instant_V1?Circle=" + latitude + "," + longitude + "," + radius + "&ReturnList=" + STOP_SEARCH_RETURN_CODES;
	}

	public String getMarkerSearchUrl(String publicIdentifier) {
		return apiUrl + "/interfaces/ura/instant_V1?StopID=" + urlEncode(publicIdentifier) + "&ReturnList=" + STOP_SEARCH_RETURN_CODES;						
	}

	private String urlEncode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
}
