package uk.co.eelpieconsulting.countdown.urls;


public class CountdownApiUrlBuilder {

	private String apiUrl;

	public CountdownApiUrlBuilder(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	
	public String getStopBoardUrl(int stopId) {
		return apiUrl + "/interfaces/ura/instant_V1?StopCode1=" + stopId + "&ReturnList=LineName,DestinationText,EstimatedTime";
	}
	
	public String getMarkerSearchUrl(double latitude, double longitude, int radius) {
		return apiUrl + "/interfaces/ura/instant_V1?Circle=" + latitude + "," + longitude + "," + radius + "&ReturnList=StopID,StopCode1,StopPointName,Towards,StopPointIndicator,Latitude,Longitude";
	}
	
}
