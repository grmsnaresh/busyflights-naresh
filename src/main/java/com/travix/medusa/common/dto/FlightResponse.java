package com.travix.medusa.common.dto;

import com.travix.medusa.common.type.Airline;
import com.travix.medusa.common.type.AirportCodes;
import com.travix.medusa.common.type.Supplier;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by grmsnaresh on 05-02-2019.
 */
public class FlightResponse {

    private Airline airline;
    private AirportCodes departureAirportCode, destinationAirportCode;
    private LocalDateTime departureDate, arrivalDate;

    protected FlightResponse(Airline airline, AirportCodes departureAirportCode, AirportCodes destinationAirportCode,
                             LocalDateTime departureDate, LocalDateTime arrivalDate) {
        this.airline = airline;
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
    public FlightResponse() {}
    public Airline getAirline() {
        return airline;
    }

    public void setAirline(final Airline airline) {
        this.airline = airline;
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
        return "Flight [airline=" + airline + ", departureAirportCode="
                + departureAirportCode + ", destinationAirportCode=" + destinationAirportCode + ", departureDate="
                + departureDate + ", arrivalDate=" + arrivalDate + "]";
    }
}
