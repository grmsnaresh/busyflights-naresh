package com.travix.medusa.common.supplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.Flight;
import com.travix.medusa.common.dto.FlightSearch;
import com.travix.medusa.common.type.Airline;
import com.travix.medusa.common.type.AirportCodes;
import com.travix.medusa.common.type.CabinClass;
import com.travix.medusa.common.type.Supplier;
import com.travix.medusa.common.util.CaliculationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrazyAirServiceImpl implements SupplierService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CrazyAirServiceImpl.class);

	@Value("${supplier.endpoint.search.mockApi}")
	private boolean mockApi;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Flight> searchFlights(final FlightSearch flightSearch) {
		final List<CrazyAir> response = filterFlights(flightSearch);
		return getFlights(response);
	}

	List<Flight> getFlights(List<CrazyAir> response) {
		if (CollectionUtils.isEmpty(response)) {
			return Collections.emptyList();
		}
		return response.stream().
				map(ca -> new Flight(ca.getAirline(), Supplier.CRAZY_AIR,
						CaliculationUtils.roundToTwo.apply(ca.getPrice()),
						ca.getDepartureAirportCode(), ca.getDestinationAirportCode(),
						ca.getDepartureDate(), ca.getArrivalDate())).
				collect(Collectors.toList());
	}

	List<CrazyAir> filterFlights(final FlightSearch flightSearch) {
		TypeReference<List<CrazyAir>> mapType = new TypeReference<List<CrazyAir>>() {};
		InputStream is = TypeReference.class.getResourceAsStream("/mockdata/crazyFlights.json");
		List<CrazyAir> crazyFlights = new ArrayList<>();
		try {
			crazyFlights = objectMapper.readValue(is, mapType);
			System.out.println("States list saved successfully");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return crazyFlights;
	}

}

/**
 * Internal class that gets populated from Crazy Air search endpoint.
 *
 * @author grmsnaresh
 *
 */
class CrazyAir {

    private Airline airline;

    private double price;

    private CabinClass cabinclass;

    private AirportCodes departureAirportCode, destinationAirportCode;

    private LocalDateTime departureDate, arrivalDate;

    public CrazyAir() {
	}

	public CrazyAir(final Airline airline, final double price, final CabinClass cabinclass, final AirportCodes departureAirportCode,
					final AirportCodes destinationAirportCode, final LocalDateTime departureDate, final LocalDateTime arrivalDate) {
		super();
		this.airline = airline;
		this.price = price;
		this.cabinclass = cabinclass;
		this.departureAirportCode = departureAirportCode;
		this.destinationAirportCode = destinationAirportCode;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(final Airline airline) {
		this.airline = airline;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public CabinClass getCabinclass() {
		return cabinclass;
	}

	public void setCabinclass(final CabinClass cabinclass) {
		this.cabinclass = cabinclass;
	}

	public AirportCodes getDepartureAirportCode() {
		return departureAirportCode;
	}

	public void setDepartureAirportCode(final AirportCodes departureAirportCode) {
		this.departureAirportCode = departureAirportCode;
	}

	public AirportCodes getDestinationAirportCode() {
		return destinationAirportCode;
	}

	public void setDestinationAirportCode(final AirportCodes destinationAirportCode) {
		this.destinationAirportCode = destinationAirportCode;
	}

	public LocalDateTime getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(final LocalDateTime departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDateTime getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(final LocalDateTime arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	@Override
	public String toString() {
		return "CrazyAir [airline=" + airline + ", price=" + price + ", cabinclass=" + cabinclass
				+ ", departureAirportCode=" + departureAirportCode + ", destinationAirportCode="
				+ destinationAirportCode + ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate + "]";
	}

}
