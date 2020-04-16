package Lab4;


/**
 * @author nebela
 * @version 1.0
 * @created 16-Apr-2020 12:34:59 PM
 */
public class Trip {

	private int block_id;
	private int direction_id;
	private double distance;
	private Time endTime;
	private String route_id;
	private String service_id;
	private String shape_id;
	private Time startTime;
	private String trip_headsign;
	private String trip_id;

	public Trip(){

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
	public void updateStartStopTime(Time start, Time stop){

	}

}