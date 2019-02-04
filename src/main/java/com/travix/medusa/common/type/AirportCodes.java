package com.travix.medusa.common.type;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Enum to hold Airport codes.
 *
 * @author grmsnaresh
 *
 */
public enum AirportCodes {

	HEATHROW_AIRPORT("LHR"),
	AMSTERDAM_AIRPORT_SCHIPHOL("AMS"),
	CAIRO_AIRPORT("CAI"),
	UNKOWN("");

	private static final Map<String, AirportCodes> codes = new HashMap<>();
	private String code;

	static {
		Arrays.stream(AirportCodes.values()).forEach(type ->
			codes.put(type.getCode().toUpperCase(), type));
	}

	private AirportCodes(final String code) {
		this.code = code;
	}

	@JsonValue
	public String getCode() {
		return code;
	}

	@JsonCreator
	public static AirportCodes fromString(final String code) {
		return codes.getOrDefault(StringUtils.defaultString(code).toUpperCase(),
				AirportCodes.UNKOWN);
	}
}
