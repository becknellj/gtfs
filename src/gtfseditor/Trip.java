package gtfseditor;


import java.time.ZonedDateTime;

/**
 * @author griffithd@msoe.edu
 * @version 1.0
 * @created 16-Apr-2020 12:34:59 PM
 */
public class Trip {

	private int block_id;
	private int direction_id;
	private double distance;
	private ZonedDateTime endTime;
	private String route_id;
	private String service_id;
	private String shape_id;
	private ZonedDateTime startTime;
	private String trip_headsign;
	private String trip_id;

	public Trip(){

	}

	public int getBlock_id() {
		return block_id;
	}

	public void setBlock_id(int block_id) {
		this.block_id = block_id;
	}

	public int getDirection_id() {
		return direction_id;
	}

	public void setDirection_id(int direction_id) {
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

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param route_id
	 */
	public boolean containsRoute(String route_id){
		return false;
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