package gtfseditor;


import java.sql.Time;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.LinkedList;

/**
 * @version 1.0
 * @authors becknellj, andrew nebel
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
        Integer.parseInt(stop_sequence); //to check for invalid input
        this.stop_sequence = stop_sequence;
    }

    public String getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(String trip_id) {
        checkIdInput(trip_id);
        this.trip_id = trip_id;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        LocalTime.parse(arrival_time);
        this.arrival_time = arrival_time;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        LocalTime.parse(departure_time);
        this.departure_time = departure_time;
    }

    protected static void timeShift(String userInput, LinkedList<StopTime> times_with_common_trip, String timeType, String direction, int index) {

        try {
            LocalTime.parse(userInput); //throws exception to check format

            long hours = Integer.parseInt(userInput.substring(0, userInput.indexOf(":")));
            long minutes = Integer.parseInt(userInput.substring(3, 5));
            long seconds = Integer.parseInt(userInput.substring(6));
            LocalTime b;

            //There are four different combos of shifting here
            //if going moving time up
            if (direction.equals("f")) {
                //if moving arrival time
                if (timeType.equals("arrival")) {
                    b = LocalTime.parse(times_with_common_trip.get(index).getArrival_time());
                    b = b.plusSeconds(seconds);
                    b = b.plusMinutes(minutes);
                    b = b.plusHours(hours);
                    times_with_common_trip.get(index).setArrival_time(b.toString());
                }
                //if moving departure time
                else if (timeType.equals("departure")) {
                    b = LocalTime.parse(times_with_common_trip.get(index).getDeparture_time());
                    b = b.plusSeconds(seconds);
                    b = b.plusMinutes(minutes);
                    b = b.plusHours(hours);
                    times_with_common_trip.get(index).setDeparture_time(b.toString());
                }
            }
            //if moving time back
            else if (direction.equals("b")) {
                //departure
                if (timeType.equals("departure")) {
                    b = LocalTime.parse(times_with_common_trip.get(index).getDeparture_time());
                    b = b.minusSeconds(seconds);
                    b = b.minusMinutes(minutes);
                    b = b.minusHours(hours);
                    times_with_common_trip.get(index).setDeparture_time(b.toString());

                }
                //arrival
                else if (timeType.equals("arrival")) {
                    b = LocalTime.parse(times_with_common_trip.get(index).getArrival_time());
                    b = b.minusSeconds(seconds);
                    b = b.minusMinutes(minutes);
                    b = b.minusHours(hours);
                    times_with_common_trip.get(index).setArrival_time(b.toString());
                }
            }

        } catch (NullPointerException e) {
            Controller.throwInfoAlert("NullPointerException", "Invalid");

        } catch (NumberFormatException e) {
            Controller.throwInfoAlert("NumberFormatException", "Invalid input");
        }
    }

    void checkIdInput(String userInput) {
        if (userInput.length() != 13) {
            throw new NullPointerException();
        }
        if (!userInput.contains("_")) {
            throw new NullPointerException();
        }
        if (userInput.charAt(8) != '_') {
            throw new NullPointerException();
        }
    }
}