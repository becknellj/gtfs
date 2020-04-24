package gtfseditor;


import java.sql.Time;

/**
 * @authors becknellj, andrew nebel
 * @version 1.0
 * @created 16-Apr-2020 12:34:59 PM
 */
public class StopTime {

	private String arrival_time;
	private String departure_time;
	private String drop_off_type;
	private String pickup_type;
	private String stop_headsign;
	private String stop_id;
	private String stop_sequence;
	private String trip_id;

	public StopTime(String trip_id, String arrival_time, String departure_time, String stop_id,
					String stop_sequence, String stop_headsign, String pickup_type, String drop_off_type) {
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.drop_off_type = drop_off_type;
		this.pickup_type = pickup_type;
		this.stop_headsign = stop_headsign;
		this.stop_id = stop_id;
		this.stop_sequence = stop_sequence;
		this.trip_id = trip_id;
	}

	public String getDrop_off_type() {
		return drop_off_type;
	}

	public void setDrop_off_type(String drop_off_type) {
		this.drop_off_type = drop_off_type;
	}

	public String getPickup_type() {
		return pickup_type;
	}

	public void setPickup_type(String pickup_type) {
		this.pickup_type = pickup_type;
	}

	public String getStop_headsign() {
		return stop_headsign;
	}

	public void setStop_headsign(String stop_headsign) {
		this.stop_headsign = stop_headsign;
	}

	public String getStop_id() {
		return stop_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	public String getStop_sequence() {
		return stop_sequence;
	}

	public void setStop_sequence(String stop_sequence) {
		this.stop_sequence = stop_sequence;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public String getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	public String getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
}