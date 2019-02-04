package com.travix.medusa.busyflights.controller;


import static com.travix.medusa.helper.FlightSearchTestHelper.ARRIVAL_DATE_STR;
import static com.travix.medusa.helper.FlightSearchTestHelper.ARRIVAL_TIME_STR;
import static com.travix.medusa.helper.FlightSearchTestHelper.DEPARTURE_DATE_STR;
import static com.travix.medusa.helper.FlightSearchTestHelper.DEPARTURE_TIME_STR;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.travix.medusa.common.constant.BusyFlightPaths;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.travix.medusa.SpringBaseTest;
import com.travix.medusa.busyflights.domain.Flight;
import com.travix.medusa.busyflights.service.FlightService;
import com.travix.medusa.common.dto.FlightSearch;
import com.travix.medusa.common.type.Airline;
import com.travix.medusa.common.type.AirportCodes;
import com.travix.medusa.common.type.Supplier;

@WebMvcTest(BusyFlightController.class)
public class BusyFlightControllerTest extends SpringBaseTest {

	private static final Flight FLIGHT = new Flight(Airline.KLM, Supplier.CRAZY_AIR, 200.99, AirportCodes.AMSTERDAM_AIRPORT_SCHIPHOL, AirportCodes.HEATHROW_AIRPORT,
			LocalDateTime.parse(DEPARTURE_TIME_STR), LocalDateTime.parse(ARRIVAL_TIME_STR));

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FlightService flightService;

	final String SEARCH_PATH = BusyFlightPaths.BASE + BusyFlightPaths.SEARCH;

	@Test
	public void searchFlight_whenParametersAreFulfilledIgnoringCase_thenReturnOK() throws Exception {
		performSearchGet().
		andExpect(status().isOk());
	}

	private ResultActions performSearchGet() throws Exception {
		return mockMvc.perform(get(SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.param("origin", AirportCodes.AMSTERDAM_AIRPORT_SCHIPHOL.getCode().toLowerCase())
				.param("destination", AirportCodes.HEATHROW_AIRPORT.getCode())
				.param("departureDate", DEPARTURE_DATE_STR)
				.param("returnDate", ARRIVAL_DATE_STR)
				.param("numberOfPassengers", "1"));
	}

	@Test
	public void searchFlight_whenParametersAreMissing_thenReturnBadRequest() throws Exception {
		mockMvc.perform(get(SEARCH_PATH)
				.contentType(MediaType.APPLICATION_JSON)
				.param("origin", AirportCodes.AMSTERDAM_AIRPORT_SCHIPHOL.getCode())).
		andExpect(status().isBadRequest());
	}

	@Test
	public void searchFlight_whenRestIsCalled_thenConvertQueryParametersIntoFlightSearchDTO() throws Exception {
		performSearchGet();

		final ArgumentCaptor<FlightSearch> flightSearch = ArgumentCaptor.forClass(FlightSearch.class);
		verify(flightService).searchFlights(flightSearch.capture());

		assertThat(AirportCodes.AMSTERDAM_AIRPORT_SCHIPHOL, equalTo(flightSearch.getValue().getOrigin()));
		assertThat(AirportCodes.HEATHROW_AIRPORT, equalTo(flightSearch.getValue().getDestination()));
		assertThat(LocalDate.parse(DEPARTURE_DATE_STR), equalTo(flightSearch.getValue().getDepartureDate()));
		assertThat(LocalDate.parse(ARRIVAL_DATE_STR), equalTo(flightSearch.getValue().getReturnDate()));
		assertThat(1, equalTo(flightSearch.getValue().getNumberOfPassengers()));
	}

	@Test
	public void searchFlight_whenFlightServiceReturnsResult_thenConvertIntoJsonArray() throws Exception {
		when(flightService.searchFlights(any(FlightSearch.class))).thenReturn(Arrays.asList(FLIGHT));

		performSearchGet().
		andExpect(status().isOk()).
		andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
		andExpect(jsonPath("$.[0].airline").value(Airline.KLM.getName())).
		andExpect(jsonPath("$.[0].supplier").value(Supplier.CRAZY_AIR.getName())).
		andExpect(jsonPath("$.[0].fare").value("200.99")).
		andExpect(jsonPath("$.[0].departureAirportCode").value(AirportCodes.AMSTERDAM_AIRPORT_SCHIPHOL.getCode())).
		andExpect(jsonPath("$.[0].destinationAirportCode").value(AirportCodes.HEATHROW_AIRPORT.getCode())).
		andExpect(jsonPath("$.[0].departureDate").value(DEPARTURE_TIME_STR)).
		andExpect(jsonPath("$.[0].arrivalDate").value(ARRIVAL_TIME_STR));
	}
}
