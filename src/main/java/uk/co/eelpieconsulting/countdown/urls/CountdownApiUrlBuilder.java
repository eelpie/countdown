package uk.co.eelpieconsulting.countdown.urls;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CountdownApiUrlBuilder {

	private String apiHost;

	public CountdownApiUrlBuilder(String apiHost) {
		this.apiHost = apiHost;
	}
	
	public String getStopBoardUrl(int stopId) {
		return apiHost + "/stopBoard/" + stopId;
	}

	public String getPlaceSearchUrl(String searchTerm) {
		try {
			return apiHost + "/search?searchTerm=" + URLEncoder.encode(searchTerm, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public String getMarkerSearchUrl(double swLat, double swLng, double neLat, double neLng) {
		return apiHost + "/markers/swLat/" + swLat + "/swLng/" + swLng + "/neLat/" + neLat + "/neLng/" + neLng + "/";
	}
	
}
