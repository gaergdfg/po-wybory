import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class MetodaDHondta extends SystemPrzeliczaniaGlosow {
	Random rand = new Random();

	private String nazwa = "Metoda D'Hondta";


	MetodaDHondta() {}


	public int[] przelicz(int[] liczbaGlosow, int liczbaMandatowDoRozdania) {
		int przyznaneMandaty[] = new int[liczbaGlosow.length];
		int obecnyDzielnik[] = new int[liczbaGlosow.length];
		ArrayList<Integer> faworyci = new ArrayList<Integer>();

		Arrays.fill(obecnyDzielnik, 1);

		while (liczbaMandatowDoRozdania > 0) {
			int najwiekszaLiczbaGlosow = 0;

			for (int i = 0; i < liczbaGlosow.length; i++) {
				if (liczbaGlosow[i] / obecnyDzielnik[i] > najwiekszaLiczbaGlosow) {
					faworyci.clear();
					faworyci.add(i);
					najwiekszaLiczbaGlosow = liczbaGlosow[i] / obecnyDzielnik[i];
				} else if (liczbaGlosow[i] / obecnyDzielnik[i] == najwiekszaLiczbaGlosow) {
					faworyci.add(i);
				}
			}

			int wybranyFaworyt = faworyci.get(rand.nextInt(faworyci.size()));
			faworyci.clear();

			przyznaneMandaty[wybranyFaworyt]++;
			obecnyDzielnik[wybranyFaworyt]++;
			liczbaMandatowDoRozdania--;
		}

		return przyznaneMandaty;
	}


	public String toString() {
		return nazwa;
	}
}
