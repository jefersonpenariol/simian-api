package br.com.jeferson.simian.api.util;

import java.util.HashMap;
import java.util.Map;

public class SimianUtil {
	public static Object[] transformToHorizon(String[] dna) {

		int posicao = 0;
		Map<Integer, String> seqVert = new HashMap<Integer, String>();

		for (String sequence : dna) {
			String letra = "";

			for (int i = 0; i < sequence.length(); i++) {
				posicao = i + 1;
				letra = sequence.substring(i, posicao);

				// Vertical
				if (!seqVert.containsKey(posicao)) {
					seqVert.put(posicao, letra);
				} else {
					seqVert.put(posicao, seqVert.get(posicao).concat(letra));
				}
			}
		}

		return seqVert.values().toArray();
	}
}
