package Lab4;


import java.sql.Time;

/**
 * @author nebela
 * @version 1.0
 * @created 16-Apr-2020 12:34:59 PM
 */
public class StopTime {

	private Time arrival_time;
	private Time departure_time;
	private int drop_off_type;
	private int pickup_type;
	private int stop_headsign;
	private String stop_id;
	private int stop_sequence;
	private String trip_id;

	public StopTime(){

	}

	public int getDrop_off_type() {
		return drop_off_type;
	}

	public void setDrop_off_type(int drop_off_type) {
		this.drop_off_type = drop_off_type;
	}

	public int getPickup_type() {
		return pickup_type;
	}

	public void setPickup_type(int pickup_type) {
		this.pickup_type = pickup_type;
	}

	public int getStop_headsign() {
		return stop_headsign;
	}

	public void setStop_headsign(int stop_headsign) {
		this.stop_headsign = stop_headsign;
	}

	public String getStop_id() {
		return stop_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	public int getStop_sequence() {
		return stop_sequence;
	}

	public void setStop_sequence(int stop_sequence) {
		this.stop_sequence = stop_sequence;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}


	public void finalize() throws Throwable {

	}

	public Time getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(Time arrival_time) {
		this.arrival_time = arrival_time;
	}

	public Time getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(Time departure_time) {
		this.departure_time = departure_time;
	}
}