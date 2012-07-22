package uk.co.eelpieconsulting.countdown.urls;

import java.util.List;


public class CountdownApiUrlBuilder {

	private static final String STOP_BOARD_RETURN_CODES = "LineName,DestinationText,DirectionID,EstimatedTime";
	private static final String STOP_SEARCH_RETURN_CODES = "StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude";
	
	private String apiUrl;

	public CountdownApiUrlBuilder(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public String getStopBoardUrl(int stopId) {
		return apiUrl + "/interfaces/ura/instant_V1?StopCode1=" + stopId + "&ReturnList=" + STOP_BOARD_RETURN_CODES;
	}
	
	public String getMarkerSearchUrl(double latitude, double longitude, int radius) {
		return apiUrl + "/interfaces/ura/instant_V1?Circle=" + latitude + "," + longitude + "," + radius + "&ReturnList=" + STOP_SEARCH_RETURN_CODES;
	}
	
	public String getStopIdSearchUrl(int id) {
		return apiUrl + "/interfaces/ura/instant_V1?StopCode1=" + id + "&ReturnList=" + STOP_SEARCH_RETURN_CODES;						
	}

	public String getStopDetailsUrl(List<Integer> stopIds) {
		return apiUrl + "/interfaces/ura/instant_V1?StopCode1=" + join(stopIds) + "&ReturnList=" + STOP_SEARCH_RETURN_CODES;
	}

	private String join(List<Integer> stopIds) {
		final StringBuilder output = new StringBuilder();
		for (int i : stopIds) {
			output.append(Integer.toString(i) + ",");
		}
		final String out = output.toString();
		return out.substring(0, out.length() -1);
	}

}
