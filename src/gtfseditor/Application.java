package Lab4;


/**
 * @author nebela
 * @version 1.0
 * @created 16-Apr-2020 12:34:58 PM
 */
public class Application {

	protected HashTable<Route> routes;
	protected HashTable<Stop> stops;
	protected HashTable<StopTime> stopTimes;
	protected HashTable<Trip> trips;
	public Trip m_Trip;
	public StopTime m_StopTime;
	public Stop m_Stop;
	public Route m_Route;

	public Application(){

	}

	public void finalize() throws Throwable {

	}

	/**
	 * 
	 * @param trip_id
	 */
	public doube, double calculateBusPosition(String trip_id){
		return null;
	}

	public double displayAllTripDistance(){
		return 0;
	}

	/**
	 * 
	 * @param stop_id
	 */
	public int displayTripCount(String stop_id){
		return 0;
	}

	/**
	 * 
	 * @param stops
	 * @param trips
	 * @param stop_times
	 * @param routes
	 */
	public boolean export(String stops, String trips, String stop_times, String routes){
		return false;
	}

	/**
	 * 
	 * @param trip_id
	 */
	public double getAverageTripSpeed(String trip_id){
		return 0;
	}

	/**
	 * 
	 * @param stops
	 * @param trips
	 * @param stop_times
	 * @param routes
	 */
	public boolean import(String stops, String trips, String stop_times, String routes){
		return false;
	}

	/**
	 * 
	 * @param route_id
	 */
	public List searchRoute(String route_id){
		return null;
	}

	/**
	 * 
	 * @param stop_id
	 */
	public List searchStop(String stop_id){
		return null;
	}

	/**
	 * 
	 * @param stop_id
	 */
	public String searchStopNextTrip(String stop_id){
		return "";
	}

	/**
	 * 
	 * @param trip_id
	 */
	public List searchTrip(String trip_id){
		return null;
	}

	/**
	 * 
	 * @param id
	 * @param type
	 * @param attribute
	 * @param value
	 */
	public boolean updateAttribute(String id, int type, int attribute, String value){
		return false;
	}

	/**
	 * 
	 * @param stop_times
	 * @param attribute
	 * @param value
	 */
	public boolean updateGroupStopTime(List stop_times, int attribute, String value){
		return false;
	}

}