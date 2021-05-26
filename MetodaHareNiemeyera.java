import java.util.ArrayList;
import java.util.Random;


public class MetodaHareNiemeyera extends SystemPrzeliczaniaGlosow {
	Random rand = new Random();

	private String nazwa = "Metoda Hare'a-Niemeyera";


	MetodaHareNiemeyera() {}


	public int[] przelicz(int[] liczbaGlosow, int liczbaMandatowDoRozdania) {
		int przyznaneMandaty[] = new int[liczbaGlosow.length];
		double wspolczynnik[] = new double[liczbaGlosow.length];
		ArrayList<Integer> faworyci = new ArrayList<Integer>();
		int sumaGlosow = 0;

		for (int i = 0; i < liczbaGlosow.length; i++) {
			sumaGlosow += liczbaGlosow[i];
		}
		
		for (int i = 0; i < liczbaGlosow.length; i++) {
			wspolczynnik[i] = (double)(liczbaGlosow[i] * liczbaMandatowDoRozdania) / (double)sumaGlosow;
			przyznaneMandaty[i] = (int)Math.floor(wspolczynnik[i]);
			liczbaMandatowDoRozdania -= przyznaneMandaty[i];
			wspolczynnik[i] -= przyznaneMandaty[i];
		}

		while (liczbaMandatowDoRozdania > 0) {
			double najwiekszaReszta = 0.;

			for (int i = 0; i < liczbaGlosow.length; i++) {
				if (wspolczynnik[i] > najwiekszaReszta) {
					faworyci.clear();
					faworyci.add(i);
					najwiekszaReszta = wspolczynnik[i];
				} else if (wspolczynnik[i] == najwiekszaReszta) {
					faworyci.add(i);
				}
			}

			int wybranyFaworyt = faworyci.get(rand.nextInt(faworyci.size()));
			faworyci.clear();

			wspolczynnik[wybranyFaworyt] = 0.;
			przyznaneMandaty[wybranyFaworyt]++;
			liczbaMandatowDoRozdania--;
		}

		return przyznaneMandaty;
	}


	public String toString() {
		return nazwa;
	}
}