package com.travix.medusa.common.supplier;

import java.util.List;

import org.springframework.web.client.RestClientException;

import com.travix.medusa.busyflights.domain.Flight;
import com.travix.medusa.common.dto.FlightSearch;

/**
 * Interface to be implemented by flights search suppliers.
 *
 * @author grmsnaresh
 *
 */
public interface SupplierService {

	/**
	 * Searches a flight using the criteria expressed in {@code FlightSearch}.
	 *
	 * @param flightSearch
	 *
	 * @return a list of flights or empty list when no result
	 *
	 * @throws RestClientException in case the supplier service is retrieved from an endpoint
	 */
	List<Flight> searchFlights(FlightSearch flightSearch);

}
