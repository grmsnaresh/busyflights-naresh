package com.travix.medusa.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.DoubleFunction;

/**
 * Common math operations.
 *
 * @author grmsnaresh
 *
 */
public class CaliculationUtils {

	/**
	 * A function to round double number decimals into.
	 *
	 * <p>
	 * Rounding behavior is Half Up.
	 * </p>
	 * @author grmsnaresh
	 *
	 * @see RoundingMode#HALF_UP
	 */
	public static final DoubleFunction<Double> roundToTwo = (d) ->
		new BigDecimal(d).setScale(2, RoundingMode.HALF_UP).doubleValue();
}
