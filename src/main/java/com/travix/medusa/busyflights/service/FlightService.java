package com.travix.medusa.busyflights.service;

import java.util.List;

import com.travix.medusa.busyflights.domain.Flight;
import com.travix.medusa.common.dto.FlightSearch;

/**
 * Interface for flights related services.
 *
 * @author grmsnaresh
 *
 */
public interface FlightService {

	/**
	 * Searches flights with the given criteria expressed in {@code FlightSearch}.
	 *
	 * <p>
	 * 	Note that price of flights is rounded to 2 decimals.
	 * </p>
	 *
	 * @param flightSearch
	 *
	 * @return a list of flights ordered by fare ascending or empty list when no result
	 */
	List<Flight> searchFlights(FlightSearch flightSearch);

}
