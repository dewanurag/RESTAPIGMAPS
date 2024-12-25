package utilty;

public enum APIResources {
	
	addPlaceAPI("/maps/api/place/add/json")
	, getPlaceAPI("/maps/api/place/get/json")
	,deletePlaceAPI("/maps/api/place/delete/json")
	;
	private String resource;
	APIResources(String string) {
		// TODO Auto-generated constructor stub
		this.resource = string;
	}
	
	public String getResource() {
		return resource;
	}
}
