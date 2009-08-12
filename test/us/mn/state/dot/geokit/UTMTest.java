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

import junit.framework.TestCase;

/** 
 * UTM convertion test cases.
 *
 * @author Doug Lau
 */
public class UTMTest extends TestCase {

	static protected final double EPSILON = 0.000000002;

	static boolean near(double v0, double v1) {
		return v0 - EPSILON <= v1 && v0 + EPSILON >= v1;
	}

	public UTMTest(String name) {
		super(name);
	}

	public void test() {
		UTMPosition utm = testPosition(45, -93);
		assertTrue(utm.getZone().equals(new UTMZone(15, true)));
		assertTrue(near(utm.getEasting(), 500000));
		assertTrue(near(utm.getNorthing(), 4982950.400429002));
		utm = testPosition(45, -94);
		assertTrue(utm.getZone().equals(new UTMZone(15, true)));
		assertTrue(near(utm.getEasting(), 421184.69708298746));
		assertTrue(near(utm.getNorthing(), 4983436.7685517482));
		utm = testPosition(39, -122);
		assertTrue(utm.getZone().equals(new UTMZone(10, true)));
		assertTrue(near(utm.getEasting(), 586592.67802760156));
		assertTrue(near(utm.getNorthing(), 4317252.164704174));
	}

	protected UTMPosition testPosition(double lat, double lon) {
		UTMPosition utm = UTMPosition.convert(GeodeticDatum.WGS_84,
			new Position(lat, lon));
		System.err.println(utm);
		Position p = utm.getPosition(GeodeticDatum.WGS_84);
		assertTrue(near(p.getLatitude(), lat));
		assertTrue(near(p.getLongitude(), lon));
		return utm;
	}
}
