package com.travix.medusa.helper;

import java.time.LocalDate;

import com.travix.medusa.common.dto.FlightSearch;
import com.travix.medusa.common.type.AirportCodes;

public class FlightSearchTestHelper {

	public static final String DEPARTURE_TIME_STR = "2017-11-10T02:00:00";
	public static final String ARRIVAL_TIME_STR = "2017-11-10T06:30:00";

	public static final String DEPARTURE_DATE_STR = "2017-11-10";
	public static final String ARRIVAL_DATE_STR = "2018-02-15";

	public static final FlightSearch FLIGHT_SEARCH = new FlightSearch(AirportCodes.AMSTERDAM_AIRPORT_SCHIPHOL, AirportCodes.HEATHROW_AIRPORT,
			LocalDate.parse(DEPARTURE_DATE_STR),
			LocalDate.parse(ARRIVAL_DATE_STR), 1);

}
