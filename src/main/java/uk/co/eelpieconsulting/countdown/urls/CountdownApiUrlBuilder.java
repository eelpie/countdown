package uk.co.eelpieconsulting.countdown.urls;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CountdownApiUrlBuilder {

	private String apiUrl;

	public CountdownApiUrlBuilder(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public String getStopBoardUrl(int stopId) {
		return apiUrl + "/interfaces/ura/instant_V1?StopCode1=" + stopId + "&ReturnList=LineName,DestinationText,EstimatedTime";
	}

	public String getPlaceSearchUrl(String searchTerm) {
		try {
			return apiUrl + "/search?searchTerm=" + URLEncoder.encode(searchTerm, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public String getMarkerSearchUrl(double latitude, double longitude, int radius) {
		return apiUrl + "/interfaces/ura/instant_V1?Circle=" + latitude + "," + longitude + "," + radius + "&ReturnList=StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude";
	}
	
}
