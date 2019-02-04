package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.Flight;
import com.travix.medusa.busyflights.service.FlightService;
import com.travix.medusa.common.constant.BusyFlightPaths;
import com.travix.medusa.common.dto.FlightSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * REST endpoint for busy flight search API.
 *
 * @author grmsnaresh
 *
 */
@RestController
@RequestMapping(BusyFlightPaths.BASE)
public class BusyFlightController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private FlightService flightService;

	@GetMapping(BusyFlightPaths.SEARCH)
	public List<Flight> searchFlight(@Valid final FlightSearch flightSearch) {
		LOGGER.debug("Get request searchFlight with parameters [{}]", flightSearch);
		return flightService.searchFlights(flightSearch);
	}
}
