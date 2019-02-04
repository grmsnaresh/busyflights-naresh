package com.travix.medusa.common.supplier;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.Flight;
import com.travix.medusa.common.dto.FlightSearch;
import com.travix.medusa.common.type.Airline;
import com.travix.medusa.common.type.AirportCodes;
import com.travix.medusa.common.type.Supplier;
import com.travix.medusa.common.util.CaliculationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToughJetServiceImpl implements SupplierService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ToughJetServiceImpl.class);


	@Value("${supplier.endpoint.search.mockApi}")
	private boolean mockApi;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<Flight> searchFlights(final FlightSearch flightSearch) {
		final List<ToughJet> toughJets = filterFlights(flightSearch);
		return getFlights(toughJets);
	}

	List<Flight> getFlights(final List<ToughJet> toughJets ) {
		if (CollectionUtils.isEmpty(toughJets)) return Collections.emptyList();

		return toughJets.stream().
				map(tj -> new Flight(tj.getCarrier(), Supplier.TOUGH_JET,
						CaliculationUtils.roundToTwo.apply(calculatePrice(tj.getBasePrice(), tj.getTax(), tj.getDiscount())),
						tj.getDepartureAirportName(), tj.getArrivalAirportName(),
						tj.getOutboundDateTime(), tj.getInboundDateTime())).
				collect(Collectors.toList());
	}

	double calculatePrice(final double basePrice, final double tax, final double discount) {
		LOGGER.debug("Calculate price from base price [{}], tax [{}], discount [{}]",
				basePrice, tax, discount);

		final double discountAmount = basePrice * (discount/100);
		final double price = (basePrice - discountAmount) + tax;
		return price;
	}

	List<ToughJet> filterFlights(final FlightSearch flightSearch) {
		TypeReference<List<ToughJet>> mapType = new TypeReference<List<ToughJet>>() {};
		InputStream is = TypeReference.class.getResourceAsStream("/mockdata/toughJetFlights.json");
		List<ToughJet> toughJets = new ArrayList<>();
		try {
			toughJets = objectMapper.readValue(is, mapType);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return toughJets;
	}

	private void log(final ResponseEntity<ToughJet[]> response) {
		LOGGER.debug("ToughJet search result {}", Arrays.stream(response.getBody())
				.map(tj -> tj.toString())
				.reduce((str1, str2) -> String.join(",", str1, str2))
				.get());
	}

}

/**
 * Internal class that gets populated from Tough Jet search endpoint.
 *
 * @author grmsnaresh
 *
 */
class ToughJet {

    private Airline carrier;

    private double basePrice, tax, discount;

    private AirportCodes departureAirportName, arrivalAirportName;

    private LocalDateTime outboundDateTime, inboundDateTime;

    public ToughJet() {
	}

	public ToughJet(final Airline carrier, final double basePrice, final double tax, final double discount, final AirportCodes departureAirportName,
					final AirportCodes arrivalAirportName, final LocalDateTime outboundDateTime, final LocalDateTime inboundDateTime) {
		super();
		this.carrier = carrier;
		this.basePrice = basePrice;
		this.tax = tax;
		this.discount = discount;
		this.departureAirportName = departureAirportName;
		this.arrivalAirportName = arrivalAirportName;
		this.outboundDateTime = outboundDateTime;
		this.inboundDateTime = inboundDateTime;
	}

	public Airline getCarrier() {
		return carrier;
	}

	public void setCarrier(final Airline carrier) {
		this.carrier = carrier;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(final double basePrice) {
		this.basePrice = basePrice;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(final double tax) {
		this.tax = tax;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(final double discount) {
		this.discount = discount;
	}

	public AirportCodes getDepartureAirportName() {
		return departureAirportName;
	}

	public void setDepartureAirportName(final AirportCodes departureAirportName) {
		this.departureAirportName = departureAirportName;
	}

	public AirportCodes getArrivalAirportName() {
		return arrivalAirportName;
	}

	public void setArrivalAirportName(final AirportCodes arrivalAirportName) {
		this.arrivalAirportName = arrivalAirportName;
	}

	public LocalDateTime getOutboundDateTime() {
		return outboundDateTime;
	}

	public void setOutboundDateTime(final LocalDateTime outboundDateTime) {
		this.outboundDateTime = outboundDateTime;
	}

	public LocalDateTime getInboundDateTime() {
		return inboundDateTime;
	}

	public void setInboundDateTime(final LocalDateTime inboundDateTime) {
		this.inboundDateTime = inboundDateTime;
	}

	@Override
	public String toString() {
		return "ToughJet [carrier=" + carrier + ", basePrice=" + basePrice + ", tax=" + tax + ", discount="
				+ discount + ", departureAirportName=" + departureAirportName + ", arrivalAirportName="
				+ arrivalAirportName + ", outboundDateTime=" + outboundDateTime + ", inboundDateTime=" + inboundDateTime
				+ "]";
	}

}
