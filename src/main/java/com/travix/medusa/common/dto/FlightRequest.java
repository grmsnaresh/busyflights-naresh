package com.travix.medusa.common.dto;

import com.travix.medusa.common.type.AirportCodes;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by grmsnaresh on 05-02-2019.
 */
public class FlightRequest {

    @NotNull
    private AirportCodes origin, destination;

    @NotNull
    private LocalDate departureDate, returnDate;

    public AirportCodes getOrigin() {
        return origin;
    }

    public void setOrigin(AirportCodes origin) {
        this.origin = origin;
    }

    public AirportCodes getDestination() {
        return destination;
    }

    public void setDestination(AirportCodes destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
