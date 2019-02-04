package com.travix.medusa.common.type;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.BaseTest;

public class AirportCodesTest extends BaseTest {

	@Test
	public void fromString_whenParamterInLowerCase_thenReturnTheEquivalentEnum() {
		assertThat(AirportCodes.AMSTERDAM_AIRPORT_SCHIPHOL, equalTo(AirportCodes.fromString("ams")));
	}

	@Test
	public void fromString_whenParameterIsNullOrWrong_thenReturnUNKOWN() {
		assertThat(AirportCodes.UNKOWN, equalTo(AirportCodes.fromString(null)));
		assertThat(AirportCodes.UNKOWN, equalTo(AirportCodes.fromString(" ")));
	}

	@Test
	public void deserializingIATA_whenJsonRepresentationIsProvided_thenResolveToEnum() throws Exception {
		final ObjectMapper mapper = new ObjectMapper();
		final AirportCodes airportCodes = mapper.readValue("\"ams\"", AirportCodes.class);
		assertThat(airportCodes, equalTo(AirportCodes.AMSTERDAM_AIRPORT_SCHIPHOL));
	}
}
