package com.travix.medusa.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.travix.medusa.BaseTest;
import com.travix.medusa.common.util.CaliculationUtils;

public class CaliculationUtilsTest extends BaseTest {

	@Test
	public void roundToTwo_whenHigherThanHald_thenRoundUp() {
		final double d = 21.236;
		assertThat(21.24, is(CaliculationUtils.roundToTwo.apply(d)));
	}

}
