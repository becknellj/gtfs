package gtfseditor;


/**
 * @authors Andrew Nebel
 * @version 1.0
 * @created 16-Apr-2020 12:34:58 PM
 */
public class Stop {

	private String level_id;
	private String stop_desc;
	private String stop_id;
	private double stop_lat;
	private double stop_long;
	private String stop_name;
	private int tripNumber;

	public Stop(String level_id, String stop_desc, String stop_id, double stop_lat,
				double stop_long, String stop_name, int tripNumber) {
		this.level_id = level_id;
		this.stop_desc = stop_desc;
		this.stop_id = stop_id;
		this.stop_lat = stop_lat;
		this.stop_long = stop_long;
		this.stop_name = stop_name;
		this.tripNumber = tripNumber;
	}

	public void finalize() throws Throwable {

	}

	public String getLevel_id() {
		return level_id;
	}

	public String getStop_desc() {
		return stop_desc;
	}

	public String getStop_id() {
		return stop_id;
	}

	public double getStop_lat() {
		return stop_lat;
	}

	public double getStop_long() {
		return stop_long;
	}

	public String getStop_name() {
		return stop_name;
	}

	public int getTripNumber() {
		return tripNumber;
	}

	public void setLevel_id(String level_id) {
		this.level_id = level_id;
	}

	public void setStop_desc(String stop_desc) {
		this.stop_desc = stop_desc;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	public void setStop_lat(double stop_lat) {
		this.stop_lat = stop_lat;
	}

	public void setStop_long(double stop_long) {
		this.stop_long = stop_long;
	}

	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	public void setTripNumber(int tripNumber) {
		this.tripNumber = tripNumber;
	}
}