package gtfseditor;


import gtfseditor.Route;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @authors becknellj, andrew nebel
 * @version 1.0
 * @created 16-Apr-2020 12:34:58 PM
 */
public class Application {

	protected Hashtable<String, Route> routes;
	protected Hashtable<String,Stop> stops;
	protected Hashtable<String, LinkedList<StopTime>> stopTimes;
	protected Hashtable<String, Trip> trips;

	public Application(){
		this.trips = new Hashtable<String, Trip>();
		this.stops = new Hashtable<String, Stop>();
		this.stopTimes = new Hashtable<String, LinkedList<StopTime>>();
		this.routes = new Hashtable<String, Route>();
	}

	/**
	 * 
	 * @param trip_id
	 */
	public double calculateBusPosition(String trip_id){
		return 0.0;
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
	public boolean importFiles(String stops, String trips, String stop_times, String routes){
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


	/**This method takes all of the trips currently in the trips hash
	 * table and calculates the average speed of the trip
	 * @return String of all the trip ids and their avg speed
	 * @throws ParseException
	 */
	public String displayAllTripSpeed() throws ParseException{
		//first stop time
		String timeFirst;
		//last stop time;
		String timeLast;
		//trip distance
		double tripDistance;
		//current tripid
		String currentTripid;
		//current stopid
		String currentStopid;
		//past stopid
		String pastStopid;
		//currentTripStopList
		LinkedList currentTripStopList;
		//string to put in textArea
		String speedList = "";
		//time difference;
		double timeDifference;
		//used for key storage
		String str;
		//get keys
		Set<String> keys = trips.keySet();
		//get iterator for hashtable
		Iterator<String> itr = keys.iterator();
		//for each trip
		while (itr.hasNext()) {
			//reset trip distance;
			tripDistance = 0;
			//get key
			str = itr.next();
			//get tripid
			currentTripid = trips.get(str).getTrip_id();
			//get list of stops and their times
			currentTripStopList = stopTimes.get(currentTripid);
			//get first stop and its time
			timeFirst = ((StopTime)(currentTripStopList.get(0))).getArrival_time();
			pastStopid = ((StopTime)(currentTripStopList.get(0))).getStop_id();
			//for each stop after the first
			for(int i = 1; i < currentTripStopList.size(); i++){
				currentStopid = ((StopTime)(currentTripStopList.get(i))).getStop_id();
				//get distance between past and current stop
				tripDistance += distance(stops.get(pastStopid).getStop_lat(), stops.get(currentStopid).getStop_lat(), stops.get(pastStopid).getStop_long(), stops.get(currentStopid).getStop_long());
				//set past stop as current stop
				pastStopid = currentStopid;
			}
			//get time of last stop
			timeLast = ((StopTime)(currentTripStopList.getLast())).getArrival_time();
			//get time difference
			timeDifference = getTimeDifferenceHours(timeLast, timeFirst);
			//add speed to string of speeds
			speedList += "Trip: " + currentTripid + " Avg. Speed: " + String.format("%.2f",tripDistance/timeDifference) + " mph\n";
		}
		return speedList;
	}









	// This code is contributed by Prasad Kshirsagar
	// Code from geeksforgeeks.org that calculates
	//distance between two points lat and long
	public static double distance(double lat1,
								  double lat2, double lon1,
								  double lon2)
	{

		// The math module contains a function
		// named toRadians which converts from
		// degrees to radians.
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);

		// Haversine formula
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2)
				+ Math.cos(lat1) * Math.cos(lat2)
				* Math.pow(Math.sin(dlon / 2),2);

		double c = 2 * Math.asin(Math.sqrt(a));

		// Radius of earth in kilometers. Use 3956
		// for miles
		double r = 3956;

		// calculate the result
		return(c * r);
	}


	public static double getTimeDifferenceHours(String time1, String time2) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		double difference = Math.abs(date2.getTime() - date1.getTime());
		//convert to seconds
		difference = difference/1000;
		//convert to min
		difference = difference/60;
		//convert to hours
		difference = difference/60;
		return difference;
	}


}