package com.travix.medusa.common.converter;

import org.springframework.core.convert.converter.Converter;

import com.travix.medusa.common.type.AirportCodes;

/**
 * Instructs Spring Rest to convert String parameter into {@link AirportCodes}.
 *
 * @author grmsnaresh
 *
 */
public class IATAConverter implements Converter<String, AirportCodes> {

	@Override
	public AirportCodes convert(final String code) {
		return AirportCodes.fromString(code);
	}



}
