package uk.co.eelpieconsulting.countdown.urls;

public class CountdownApiUrlBuilder {

	private String apiHost;

	public CountdownApiUrlBuilder(String apiHost) {
		this.apiHost = apiHost;
	}
	
	public String getStopBoardUrl(int stopId) {
		return apiHost + "/stopBoard/" + stopId;
	}
	
}
