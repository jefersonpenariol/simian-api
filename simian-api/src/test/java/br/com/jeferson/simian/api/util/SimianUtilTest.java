package br.com.jeferson.simian.api.util;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class SimianUtilTest {

	@Test
	public void testTransformToHorizon() {
		String[] dna = {"AAAAGA", "CCCCGC", "TCATGT", "AAAAGG", "CCCCTA", "TCACTA"};
		assertNotNull(SimianUtil.transformToHorizon(dna));
	}
}
