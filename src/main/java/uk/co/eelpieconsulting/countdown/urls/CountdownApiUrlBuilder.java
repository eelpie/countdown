package uk.co.eelpieconsulting.countdown.urls;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CountdownApiUrlBuilder {

	private String apiUrl;

	public CountdownApiUrlBuilder(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public String getStopBoardUrl(int stopId) {
		return apiUrl + "/stopBoard/" + stopId;
	}

	public String getPlaceSearchUrl(String searchTerm) {
		try {
			return apiUrl + "/search?searchTerm=" + URLEncoder.encode(searchTerm, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public String getMarkerSearchUrl(double swLat, double swLng, double neLat, double neLng) {
		return apiUrl + "/markers/swLat/" + swLat + "/swLng/" + swLng + "/neLat/" + neLat + "/neLng/" + neLng + "/";
	}
	
}
