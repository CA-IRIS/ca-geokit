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
 * A UTM position consists of the UTM zone, plus easting and northing.
 *
 * @author Douglas Lau
 */
public class UTMPosition {

	/** False Easting is at central meridian of zone */
	static protected final int FALSE_EASTING = 500000;

	/** False Northing of 10,000 Km is used for the Southern hemisphere */
	static protected final int FALSE_NORTHING = 10000000;

	/** Scale at meridian */
	static protected final double K0 = 0.9996;

	/** Convert a (lat/lon) position to UTM */
	static public UTMPosition convert(GeodeticDatum gd, Position pos) {
		double a = gd.getEquatorialRadius();
		double e2 = gd.getEccentricitySquared();
		double ep2 = e2 / (1 - e2);
		double lat = Math.toRadians(pos.getLatitude());
		double lon = Math.toRadians(pos.getLongitude());
		double sin_lat = Math.sin(lat);
		double cos_lat = Math.cos(lat);
		double tan_lat = Math.tan(lat);
		UTMZone zone = new UTMZone(pos);
		// nu is the distance to the polar axis
		double nu = a / Math.sqrt(1 - e2 * Math.pow(sin_lat, 2));
		double p = lon - zone.meridian();
		double T2 = Math.pow(tan_lat, 2);
		double T4 = Math.pow(tan_lat, 4);
		double C = ep2 * Math.pow(cos_lat, 2);
		double A = p * cos_lat;
		double M = gd.getMeridionalArc(lat);
		double easting = K0 * nu * (A
			+ (1 - T2 + C)
			* Math.pow(A, 3) / 6
			+ (5 - 18 * T2 + T4 + 72 * C - 58 * ep2)
			* Math.pow(A, 5) / 120
		) + FALSE_EASTING;
		double northing = K0 * (M + nu * tan_lat * (
			Math.pow(A, 2) / 2
			+ (5 - T2 + 9 * C + 4 * Math.pow(C, 2))
			* Math.pow(A, 4) / 24
			+ (61 - 58 * T2 + T4 + 600 * C - 330 * ep2)
			* Math.pow(A, 6) / 720
		));
		// In Southern hemisphere, start from the South pole
		if(pos.getLatitude() < 0)
			northing += FALSE_NORTHING;
		return new UTMPosition(zone, easting, northing);
	}

	/** UTM zone */
	protected final UTMZone zone;

	/** Easting (meters) */
	protected final double easting;

	/** Northing (meters) */
	protected final double northing;

	/** Create a new UTM position */
	public UTMPosition(UTMZone z, double e, double n) {
		zone = z;
		easting = e;
		northing = n;
	}

/*
	def lat_lon_from_utm(self, zone, northing, easting):
		'Convert UTM coordinates to lat/lon (degrees)'

		a = equatorial_radius
		e2 = eccentricity ** 2
		ep2 = e2 / (1 - e2)

		e1 = (1 - math.sqrt(1 - e2)) / (1 + math.sqrt(1 - e2))

		x = easting - FALSE_EASTING
		y = northing
		if not zone.northern_hemisphere():
			y -= FALSE_NORTHING

		M = y / K0
		mu = M / (a * (1 - e2 / 4 - 3 * e2 * e2 / 64
			- 5 * e2 * e2 * e2 / 256))

		phi = (mu +
			(3 * e1 / 2 - 27 * e1 ** 3 / 32) *
				math.sin(2 * mu) +
			(21 * e1 ** 2 / 16 - 55 * e1 ** 4 / 32) *
				math.sin(4 * mu) +
			(151 * e1 ** 3 / 96) *
				math.sin(6 * mu)
		)

		N1 = a / math.sqrt(1 - e2 * math.sin(phi) * math.sin(phi))

		T1 = math.tan(phi) ** 2
		T2 = T1 ** 2
		C1 = ep2 * math.cos(phi) ** 2
		C2 = C1 ** 2
		R1 = a * (1 - e2) / (1 - e2 * math.sin(phi) ** 2) ** 1.5
		D = x / (N1 * K0)

		lat = phi - (N1 * math.tan(phi) / R1) * (
			D ** 2 / 2 -
			(5 + 3 * T1 + 10 * C1 - 4 * C2 - 9 * ep2)
			* D ** 4 / 24 +
			(61 + 90 * T1 + 298 * C1 + 45 * T2 - 252 * ep2 - 3 * C2)
			* D ** 6 / 720
		)
		lat_deg = math.degrees(lat)

		lon = (D - (1 + 2 * T1 + C1) * D ** 3 / 6 +
			(5 - 2 * C1 + 28 * T1 - 3 * C2 + 8 * ep2 + 24 * T2)
			* D ** 5 / 120) / math.cos(phi)
		lon += zone.meridian()
		lon_deg = math.degrees(lon)
		return (lat_deg, lon_deg)
*/
}
