package uk.co.eelpieconsulting.countdown.urls;

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

}
