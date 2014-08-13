package de.makiart.phosphatapp.logic;

import java.util.List;

public class CalcAlgorithm {

	/**
	 * Berechnen der Phosphateinheiten.<br />
	 * Gibt den berechneten Phospaht Wert zurück.<br />
	 * Drei 0 PE Werte ergeben 1 PE.
	 */
	public static int calculatePe(List<Food> foodList) {
		int phosphatCount = 0;
		int peZeroCount = 0;
		for (Food f : foodList) {
			for (int i = 0; i < f.getTimes(); i++) {
				int value = f.getPeValue();
				phosphatCount += value;
				if (value == 0) {
					peZeroCount++;
					if (peZeroCount >= 3) {
						phosphatCount++;
						peZeroCount = 0;
					}
				}
			}
		}
		return phosphatCount;
	}
	
	public static int calculatePe(Food f) {
		int phosphatCount = 0;
		int peZeroCount = 0;
		for (int i = 0; i < f.getTimes(); i++) {
			int value = f.getPeValue();
			phosphatCount += value;
			if (value == 0) {
				peZeroCount++;
				if (peZeroCount >= 3) {
					phosphatCount++;
					peZeroCount = 0;
				}
			}
		}
		return phosphatCount;
	}
}
