package com.travix.medusa.common.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.travix.medusa.common.type.AirportCodes;

/**
 * Flight Search DTO .
 *
 * @author grmsnaresh
 *
 */
public class FlightSearch {

	@NotNull
	private AirportCodes origin, destination;

	@NotNull
	private LocalDate departureDate, returnDate;

	@Range(min = 1, max = 4)
	private int numberOfPassengers;

	public FlightSearch() {
	}

	public FlightSearch(final AirportCodes origin, final AirportCodes destination, final LocalDate departureDate, final LocalDate returnDate,
						final int numberOfPassengers) {
		super();
		this.origin = origin;
		this.destination = destination;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		this.numberOfPassengers = numberOfPassengers;
	}

	public AirportCodes getOrigin() {
		return origin;
	}

	public void setOrigin(final AirportCodes origin) {
		this.origin = origin;
	}

	public AirportCodes getDestination() {
		return destination;
	}

	public void setDestination(final AirportCodes destination) {
		this.destination = destination;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(final LocalDate departureDate) {
		this.departureDate = departureDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(final LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(final int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	@Override
	public String toString() {
		return "FlightSearch [origin=" + origin + ", destination=" + destination + ", departureDate=" + departureDate
				+ ", returnDate=" + returnDate + ", numberOfPassengers=" + numberOfPassengers + "]";
	}
}
