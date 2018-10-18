package com.charter.utils;

import com.charter.entity.Location;
import com.charter.entity.Plane;
import com.charter.entity.Point;

import lombok.extern.slf4j.Slf4j;

/**
 * This is Utils class used for all the calculations
 * 
 * @author ravi.nukala
 *
 */
@Slf4j
public class CharterCalulationUtils {

	/*
	 * Given two points with latitudes and longitudes, this method determines we try determine the distance between those two in miles
	 * */
	public static double calculateDistance(Point p1, Point p2) {
		double theta = p1.getLongitude() - p2.getLongitude();
		double dist = Math.sin(deg2rad(p1.getLatitude())) * Math.sin(deg2rad(p2.getLatitude()))
				+ Math.cos(deg2rad(p1.getLatitude())) * Math.cos(deg2rad(p2.getLatitude())) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public static double calculateDuration(Plane planeObject, Location fromObject, Location toObject) {
		Double distanceBetweenPoints = calculateDistance(fromObject.getPoint(), toObject.getPoint());
		Double flightDuration = (planeObject.getAirSpeed() != 0.0 ? distanceBetweenPoints / planeObject.getAirSpeed()
				: 0.0);
		Double timeTakenForStops = ((planeObject.getRange() != 0.0) ? (distanceBetweenPoints / planeObject.getRange())
				: 0.0) * 0.5;
		Double totalTimeTaken = flightDuration + timeTakenForStops;
		log.info("TotalDuration for plane:{}, is: {}", planeObject.getName(), totalTimeTaken);
		return totalTimeTaken;
	}

}
