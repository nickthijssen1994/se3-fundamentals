package tutorials;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumeralToRomanTest {

	@Test
	void numeralToRomanTest(){
		assertEquals(1, convertRomanToNumeral("I"));
		assertEquals(2, convertRomanToNumeral("II"));
		assertEquals(3, convertRomanToNumeral("III"));
		assertEquals(4, convertRomanToNumeral("IV"));
		assertEquals(5, convertRomanToNumeral("V"));
		assertEquals(6, convertRomanToNumeral("VI"));
		assertEquals(7, convertRomanToNumeral("VII"));
		assertEquals(8, convertRomanToNumeral("VIII"));
		assertEquals(9, convertRomanToNumeral("IX"));
		assertEquals(10, convertRomanToNumeral("X"));
	}

	Map<Character, Integer> romanSymbols = Map.of('I', 1, 'V', 5, 'X', 10, 'L', 50, 'C', 100, 'D', 500, 'M', 1000);

	private int convertRomanToNumeral(String roman) {
		roman = roman.toUpperCase();
		int sum = 0;
		int current = 0;
		int previous = 0;
		for (int index = roman.length() - 1; index >= 0 ; index--) {
			if (romanSymbols.containsKey(roman.charAt(index))) {
				current = romanSymbols.get(roman.charAt(index));
				if (current >= previous) {
					sum += current;
				}
				else {
					sum -= current;
				}
				previous = current;
			}
			else {
				throw new IllegalArgumentException("Can not convert numeral");
			}
		}
		return sum;
	}
}
