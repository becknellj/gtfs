# General Transit Feed Specification Tool

# Authors:
* Jade Becknell
* Daniel Griffith
* Andrew Nebel
* Jacob Schultz
* Josiah Yoder

# About this project
General Transit Feed Specification (GTFS) Static is a flat file format for specifying transit(aka bus)schedule information.  More details and example files are available here: https://developers.google.com/transit/gtfs/.  This formatis used by Google Transit and mosttransit applications around the world.  Creating and maintaining GTFS files can be challenging for a transit organization as bus schedules change, stops change, and routes change. The final product will be able to do the following:
1. Import a set of GTFS files into the program 
    1. stops.txt
    2. trips.txt
    3. stop_times.txt
    4. routes.txt
2. Display the distance of each trip
3. Display the average speed based on the start and end times of a trip.  
4. Display the number of trips each stop is found on.
5. Search for a stop by stop_id and display all route_id(s)that contain the stop
6. Search for a route by route_id and display all the stop_id(s)on the route
7. Search for a route by route_id and display the trip_id for any trips happening in the future
8. Search for a stop by stop_id and display the next trip_id(s)(closest to the current time)
9. Plot the GPS coordinates for all the stops on a given route on a Google map (static is OK)
    1. Different routes should be different colors
    2. A legend should be shown
10. Plot the current location of anybus on anyroute inferred from the timing in the trips
11. Update any attributes of a stop_time, stop, route, or trip
12. Update any attributes of agroup of stop_times simultaneously
    1. Attributes of a trip should be possible to apply to “all similar trips”
13. Click and drag a stop on a route to change the stoplocation
    1. Note: This should apply the change to all tripsusing the stop
14. Click on a stop and change the times that the individual trips arrive and depart at the stop.
15. Export the GTFS files in the correct format from the data structure
    1. stops.txt
    2. trips.txt
    3. stop_times.txt
    4. routes.tx

# Change Log

| Lab |Dates| Changes Made|  
|-----|------|:---------------------------------------------------------------------------------|
| 6   | 4/23 - 4/28|<ul><li>JUnit testing added with summaries</li><li>Implemented feature 3</li><li>Implemented feature 12<ul></li><li>Updated data structure for file import</li></ul><li>Added input validation functionality</li></ul>
| 7   | 4/30 - 5/5|<ul><li>Creating Dev and Fixes Branch</li><li>Implemented feature 2</li><li>Implemented feature 8<ul></li></ul><li>Implemented feature 5</li></ul>
