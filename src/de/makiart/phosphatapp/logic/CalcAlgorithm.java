package de.makiart.phosphatapp.logic;

import java.util.ArrayList;
import java.util.List;

import de.makiart.phosphatapp.data.Edibles;

public class CalcAlgorithm {

	/**
	 * Lebensmittel die kein Phosphat enthalten und daher die (3*0PE=1PE)-Regel nicht gilt
	 */
	public static final List<String> exceptionals = new ArrayList<String>() {
		private static final long serialVersionUID = -7800422045292002799L;
		{
			add("Wasser");
			add("Salz");
		}
	};
	
	/**
	 * Berechnen der Phosphateinheiten.<br />
	 * Gibt den berechneten Phospaht Wert zurück.<br />
	 * Drei 0 PE Werte ergeben 1 PE.
	 * @param foodList Liste von Lebensmitteln
	 * @return den berechneten PE-Wert
	 */
	public static int calculatePe(List<Edibles> foodList) {
		int phosphatCount = 0;
		int peZeroCount = 0;
		for (Edibles f : foodList) {
			if (exceptionals.contains(f.getName())) {
				continue;
			}
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
	
	/**
	 * Berechne die Höhe der Phospahteinheit.
	 * Mit Berücksichtigung der Menge
	 * @param f ein Lebensmittel mit einer bestimmten Mente
	 * @return den berechneten PE-Wert
	 */
	public static int calculatePe(Edibles f) {
		if (exceptionals.contains(f.getName())) {
			return 0;
		}
		
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
