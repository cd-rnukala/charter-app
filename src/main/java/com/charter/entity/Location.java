package com.charter.entity;

public enum Location {
	// 0
	SFO("SFO", new Point(37.6213, 122.3790)),
	// 1
	PHX("PHX", new Point(33.4484, 112.0740)),
	// 2
	JFK("JFK", new Point(40.6413, 73.7781)),
	// 3
	LAS("LAS", new Point(36.0840, 115.1537)),
	// 4
	PDX("PDX", new Point(45.5898, 122.5951));

	private Point point;
	private String locationName;

	public Point getPoint() {
		return this.point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	private Location(String locationName, Point point) {
		this.point = point;
		this.locationName = locationName;
	}
}
