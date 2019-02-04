package com.travix.medusa.busyflights.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travix.medusa.busyflights.domain.Flight;
import com.travix.medusa.common.dto.FlightSearch;
import com.travix.medusa.common.supplier.SupplierService;

@Service
public class FlightServiceImpl implements FlightService {

	private final List<SupplierService> supplierServices;

	@Autowired
	public FlightServiceImpl(final List<SupplierService> supplierServices) {
		this.supplierServices = supplierServices;
	}

	@Override
	public List<Flight> searchFlights(final FlightSearch flightSearch) {
		return supplierServices.stream()
				.map(supplier -> supplier.searchFlights(flightSearch))
				.flatMap(List::stream)
				.sorted((f1, f2) -> Double.valueOf(f1.getFare()).compareTo(Double.valueOf(f2.getFare())))
				.collect(Collectors.toList());
	}

}
