/*
 * IRIS -- Intelligent Roadway Information System
 * Copyright (C) 2009  Minnesota Department of Transportation
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */
package us.mn.state.dot.geokit;

/**
 * A position is a latitude / longitude pair.  With a geodetic datum, this
 * identifies a position on the Earth.
 *
 * @author Douglas Lau
 */
public class Position {

	/** Latitude (degrees) */
	protected final double latitude;

	/** Get the latitude (degrees) */
	public double getLatitude() {
		return latitude;
	}

	/** Longitude (degrees) */
	protected final double longitude;

	/** Get the longitude (degrees) */
	public double getLongitude() {
		return longitude;
	}

	/** Create a new position */
	public Position(double lat, double lon) {
		latitude = lat;
		longitude = lon;
	}
}
