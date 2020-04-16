package Lab4;


/**
 * @author nebela
 * @version 1.0
 * @created 16-Apr-2020 12:34:58 PM
 */
public class Route {

	private String agency_id;
	private String route_color;
	private String route_desc;
	private String route_id;
	private String route_long_name;
	private String route_short_name;
	private String route_text_color;
	private Int route_type;
	private String route_url;
	private ArrayList stops;

	public Route(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param stop_id
	 */
	public boolean containsStop(String stop_id){
		return false;
	}

	public ArrayList getStops(){
		return null;
	}

}