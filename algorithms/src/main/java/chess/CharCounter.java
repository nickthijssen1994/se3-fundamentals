package chess;

import java.util.*;

public class CharCounter {

	Map<String, Integer> lettersAmount = new HashMap<>();
	List<String> allCountries = Arrays.asList("CUBA", "FIJI", "IRAK", "IRAN", "LAOS", "MALI", "OMAN", "PERU", "TOGO",
											  "BENIN", "CHILI", "CHINA", "GABON", "GHANA", "HAÏTI", "INDIA", "JAPAN",
											  "JEMEN", "KENIA", "LIBIË", "MALTA", "NAURU", "NEPAL", "NIGER", "PALAU",
											  "POLEN", "QATAR", "SAMOA", "SYRIË", "TONGA", "ANGOLA", "BELGIË",
											  "BELIZE",
											  "BHUTAN", "BRUNEI", "CANADA", "CYPRUS", "EGYPTE", "GAMBIA", "GUINEE",
											  "GUYANA", "ISRAËL", "ITALIË", "KOSOVO", "MALAWI", "MEXICO", "MONACO",
											  "PANAMA", "RWANDA", "SERVIË", "SOEDAN", "SPANJE", "TSJAAD", "TUVALU",
											  "ZAMBIA", "ZWEDEN", "ALBANIË", "ANDORRA", "ARMENIË", "BAHREIN",
											  "BOLIVIA",
											  "BURUNDI", "COMOREN", "ECUADOR", "ERITREA", "ESTLAND", "FINLAND",
											  "GEORGIË", "GRENADA", "IERLAND", "IJSLAND", "JAMAICA", "KOEWEIT",
											  "KROATIË", "LESOTHO", "LETLAND", "LIBANON", "LIBERIA", "MAROKKO",
											  "MYANMAR", "NAMIBIË", "NIGERIA", "OEGANDA", "RUSLAND", "SENEGAL",
											  "SOMALIË", "TUNESIË", "TURKIJE", "URUGUAY", "VANUATU", "VIETNAM",
											  "ALGERIJE", "BAHAMA'S", "BARBADOS", "BOTSWANA", "BRAZILIË", "CAMBODJA",
											  "COLOMBIA", "DJIBOUTI", "DOMINICA", "ETHIOPIË", "HONDURAS", "JORDANIË",
											  "KAMEROEN", "KIRGIZIË", "KIRIBATI", "LITOUWEN", "MALEISIË", "MOLDAVIË",
											  "MONGOLIË", "OEKRAÏNE", "PAKISTAN", "PARAGUAY", "PORTUGAL", "ROEMENIË",
											  "SLOVENIË", "SURINAME", "TANZANIA", "THAILAND", "TSJECHIË", "ZIMBABWE",
											  "AUSTRALIË", "BULGARIJE", "FRANKRIJK", "GUATEMALA", "HONGARIJE",
											  "INDONESIË", "IVOORKUST", "LUXEMBURG", "MACEDONIË", "MALEDIVEN",
											  "MAURITIUS", "NEDERLAND", "NICARAGUA", "NOORWEGEN", "PALESTINA",
											  "SINGAPORE", "SLOWAKIJE", "SRI LANKA", "SWAZILAND", "VENEZUELA",
											  "ARGENTINIË", "BANGLADESH", "COSTA RICA", "DENEMARKEN", "DUITSLAND ",
											  "FILIPIJNEN", "KAAPVERDIË", "KAZACHSTAN", "MADAGASKAR", "MAURITANIË",
											  "MICRONESIA", "MONTENEGRO", "MOZAMBIQUE", "OOSTENRIJK", "OOST-TIMOR",
											  "SAN MARINO", "SEYCHELLEN", "ZUID-KOREA", "AFGHANISTAN", "EL SALVADOR",
											  "GRIEKENLAND", "NOORD-KOREA", "OEZBEKISTAN", "SAINT LUCIA", "WIT" +
													  "-RUSLAND",
											  "ZUID-AFRIKA", "ZUID-SOEDAN", "ZWITSERLAND", "AZERBEIDZJAN",
											  "BURKINA FASO", "SIERRA LEONE", "TADZJIKISTAN", "TURKMENISTAN",
											  "VATICAANSTAD", "GUINEE-BISSAU", "LIECHTENSTEIN", "NIEUW-ZEELAND",
											  "SAOEDI-ARABIË", "CONGO-KINSHASA", "MARSHALLEILANDEN",
											  "SALOMONSEILANDEN",
											  "VERENIGDE STATEN", "CONGO-BRAZZAVILLE", "ANTIGUA EN BARBUDA",
											  "EQUATORIAAL-GUINEA", "TRINIDAD EN TOBAGO", "PAPOEA-NIEUW-GUINEA",
											  "VERENIGD KONINKRIJK", "SAINT KITTS EN NEVIS", "SAO TOMÉ EN PRINCIPE",
											  "BOSNIË EN HERZEGOVINA", "DOMINICAANSE REPUBLIEK",
											  "VERENIGDE ARABISCHE EMIRATEN", "CENTRAAL-AFRIKAANSE REPUBLIEK",
											  "SAINT VINCENT EN DE GRENADINES");
	List<String> countries = new ArrayList<>();

	public CharCounter() {
		countries.add("AABDEJN-AAIPS");
		countries.add("AABDENR-AL");
		countries.add("AABDIMNRRU");
		countries.add("AACDEHILNS");
		countries.add("AAELLP");
		countries.add("AAFMNS");
		countries.add("ABDOO");
		countries.add("ABEIMNORRRY-EIP");
		countries.add("ABO-BOR-LOR");
		countries.add("AHIIKKLN");
		countries.add("ADP-AHIT");
		countries.add("AGGHIS");
		countries.add("AGHIJKNö");
		countries.add("AORRZ-BLOO-DE");
		countries.add("APPSSUU");
		countries.add("BBEIOOT");
		countries.add("BETTUU");
		countries.add("CCHHIIIMRRU");
		countries.add("DEEEGIKNNP");
		countries.add("EINOPTU");
		countries.add("GINOóRV");
		countries.add("HISSU");
		countries.add("KOTTU");
		countries.add("ABDEIRS");
		countries.add("APPSSUU&BNOR");
	}

	public void countLetters() {
		for (String country : allCountries) {
			for (int letter = 0; letter < country.length(); letter++) {
				String letterString = Character.toString(country.charAt(letter));
				if (!lettersAmount.containsKey(letterString)) {
					lettersAmount.put(letterString, 1);
				}
				else {
					lettersAmount.put(letterString, lettersAmount.get(letterString) + 1);
				}
			}
		}

		for (Map.Entry<String, Integer> me : lettersAmount.entrySet()) {
			System.out.print(me.getKey() + ":");
			System.out.println(me.getValue());
		}
	}
}
