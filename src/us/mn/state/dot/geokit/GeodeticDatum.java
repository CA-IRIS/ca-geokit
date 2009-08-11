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
 * A geodetic datum is an ellipsoid which is an approximation of the shape of
 * the Earth.
 *
 * @author Douglas Lau
 */
public class GeodeticDatum {

	/** Radius at the equator (in meters) */
	protected final double equatorial_radius;

	/** Get the equatorial radius (meters) */
	public double getEquatorialRadius() {
		return equatorial_radius;
	}

	/** Radius at the poles (in meters) */
	protected final double polar_radius;

	/** Get the polar radius */
	public double getPolarRadius() {
		return polar_radius;
	}

	/** Eccentricity */
	protected final double eccentricity;

	/** Get the eccentricity */
	public double getEccentricity() {
		return eccentricity;
	}

	/** Create a new geodetic datum */
	protected GeodeticDatum(double er, double pr) {
		equatorial_radius = er;
		polar_radius = pr;
		eccentricity = Math.sqrt(1 - pr * pr / er * er);
	}

	/** World Geodetic System of 1984 (used by GPS) */
	static public final GeodeticDatum WGS_84 =
		new GeodeticDatum(6378137, 6356752.3142);

	/** North Americaon Datum of 1983 */
	static public final GeodeticDatum NAD_83 = WGS_84;

	static public final GeodeticDatum GRS_80 =
		new GeodeticDatum(6378137, 6356752.3141);
	static public final GeodeticDatum WGS_72 =
		new GeodeticDatum(6378135, 6356750.5);
	static public final GeodeticDatum Australian_1965 =
		new GeodeticDatum(6378160, 6356774.7);
	static public final GeodeticDatum Krasovsky_1940 =
		new GeodeticDatum(6378245, 6356863);
	static public final GeodeticDatum International_1924 =
		new GeodeticDatum(6378388,6356911.9);
	static public final GeodeticDatum Clarke_1880 =
		new GeodeticDatum(6378249.1, 6356514.9);
	static public final GeodeticDatum Clarke_1866 =
		new GeodeticDatum(6378206.4, 6356583.8);
	static public final GeodeticDatum Airy_1830 =
		new GeodeticDatum(6377563.4, 6356256.9);
	static public final GeodeticDatum Bessel_1841 =
		new GeodeticDatum(6377397.2, 6356079);
	static public final GeodeticDatum Everest_1830 =
		new GeodeticDatum(6377276.3, 6356075.4);
}
