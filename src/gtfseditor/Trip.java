package gtfseditor;


import java.time.ZonedDateTime;

/**
 * @authors griffithd@msoe.edu, andrew nebel
 * @version 1.0
 * @created 16-Apr-2020 12:34:59 PM
 */
public class Trip {

	private String block_id;
	private String direction_id;
	private double distance;
	private ZonedDateTime endTime;
	private String route_id;
	private String service_id;
	private String shape_id;
	private ZonedDateTime startTime;
	private String trip_headsign;
	private String trip_id;

	public Trip(String route_id, String service_id, String trip_id, String trip_headsign, String direction_id,
				String block_id, String shape_id) {
		this.block_id = block_id;
		this.direction_id = direction_id;
		this.route_id = route_id;
		this.service_id = service_id;
		this.shape_id = shape_id;
		this.trip_headsign = trip_headsign;
		this.trip_id = trip_id;
	}

	public String getBlock_id() {
		return block_id;
	}

	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}

	public String getDirection_id() {
		return direction_id;
	}

	public void setDirection_id(String direction_id) {
		this.direction_id = direction_id;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public ZonedDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(ZonedDateTime endTime) {
		this.endTime = endTime;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getShape_id() {
		return shape_id;
	}

	public void setShape_id(String shape_id) {
		this.shape_id = shape_id;
	}

	public ZonedDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(ZonedDateTime startTime) {
		this.startTime = startTime;
	}

	public String getTrip_headsign() {
		return trip_headsign;
	}

	public void setTrip_headsign(String trip_headsign) {
		this.trip_headsign = trip_headsign;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}


	/**
	 * Checks if trup contains route
	 * @param route_id
	 */
	public boolean containsRoute(String route_id){
		return this.route_id.equals(route_id);
	}

	public double getAverageSpeed(){
		return 0;
	}

	public double getDistance(){
		return 0;
	}

	/**
	 * 
	 * @param start
	 * @param stop
	 */
	public void updateStartStopTime(ZonedDateTime start, ZonedDateTime stop){

	}

}