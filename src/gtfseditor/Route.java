package gtfseditor;


import java.util.ArrayList;

/**
 * @author schultzjaco
 * @version 1.1
 * @created 16-Apr-2020 12:50:58 PM
 */
public class Route {

	private String agency_id;
	private String route_color;
	private String route_desc;
	private String route_id;
	private String route_long_name;
	private String route_short_name;
	private String route_text_color;
	private int route_type;
	private String route_url;
	private ArrayList stops;

	public Route(){

	}

	/**
	 * 
	 * @param stop_id
	 */
	public boolean containsStop(String stop_id){
		return false;
	}

	public String getAgency_id() {
		return agency_id;
	}

	public void setAgency_id(String agency_id) {
		this.agency_id = agency_id;
	}

	public String getRoute_color() {
		return route_color;
	}

	public void setRoute_color(String route_color) {
		this.route_color = route_color;
	}

	public String getRoute_desc() {
		return route_desc;
	}

	public void setRoute_desc(String route_desc) {
		this.route_desc = route_desc;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getRoute_long_name() {
		return route_long_name;
	}

	public void setRoute_long_name(String route_long_name) {
		this.route_long_name = route_long_name;
	}

	public String getRoute_short_name() {
		return route_short_name;
	}

	public void setRoute_short_name(String route_short_name) {
		this.route_short_name = route_short_name;
	}

	public String getRoute_text_color() {
		return route_text_color;
	}

	public void setRoute_text_color(String route_text_color) {
		this.route_text_color = route_text_color;
	}

	public int getRoute_type() {
		return route_type;
	}

	public void setRoute_type(int route_type) {
		this.route_type = route_type;
	}

	public String getRoute_url() {
		return route_url;
	}

	public void setRoute_url(String route_url) {
		this.route_url = route_url;
	}

	public void setStops(ArrayList stops) {
		this.stops = stops;
	}
}