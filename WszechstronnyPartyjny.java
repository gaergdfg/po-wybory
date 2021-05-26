import java.util.Random;
import java.util.ArrayList;


public class WszechstronnyPartyjny extends Wyborca {
	Random rand = new Random();
	private String wybranaPartia;


	WszechstronnyPartyjny(
		String imie,
		String nazwisko,
		int numerPodstawowegoOkregu,
		int typWyborcy,
		int[] wagiCechWyborcy,
		String wybranaPartia
	) {
		super(imie, nazwisko, typWyborcy);
		ustawWagiCech(wagiCechWyborcy);
		this.wybranaPartia = wybranaPartia;
	}


	WszechstronnyPartyjny(String[] dane, int liczbaCechKandydatow) {
		super(dane[0], dane[1], Integer.parseInt(dane[3]));

		int[] wagiCechWyborcy = new int[liczbaCechKandydatow];
		for (int i = 0; i < liczbaCechKandydatow; i++) {
			wagiCechWyborcy[i] = Integer.parseInt(dane[4 + i]);
		}
		ustawWagiCech(wagiCechWyborcy);

		wybranaPartia = dane[dane.length - 1];
	}


	public int wybierzKandydata(Kandydat[] kandydaci) {
		ArrayList<Integer> faworyci = new ArrayList<Integer>();

		int najwiekszaSumaWazona = Integer.MIN_VALUE;
		for (int i = 0; i < kandydaci.length; i++) {
			if (kandydaci[i].dajPartie().equals(wybranaPartia)) {
				int suma = obliczSumeWazonaCech(kandydaci[i].dajCechy());

				if (suma > najwiekszaSumaWazona) {
					faworyci.clear();
					faworyci.add(i);
					najwiekszaSumaWazona = suma;
				} else if (suma == najwiekszaSumaWazona) {
					faworyci.add(i);
				}
			}
		}

		return faworyci.get(rand.nextInt(faworyci.size()));
	}


	private int obliczSumeWazonaCech(int[] cechyKandydata) {
		int suma = 0;
		int[] wagiCechWyborcy = dajWagiCech();

		for (int i = 0; i < cechyKandydata.length; i++) {
			suma += cechyKandydata[i] * wagiCechWyborcy[i];
		}

		return suma;
	}
}
