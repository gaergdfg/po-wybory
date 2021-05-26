import java.util.ArrayList;


public class PartiaZachlanna extends Partia {
	PartiaZachlanna(String nazwa, int budzet) {
		super(nazwa, budzet);
	}


	public boolean wybierzDzialanie(OkragWyborczy[] okregiWyborcze, Dzialanie[] dzialania) {
		int najlepszyOkrag = -1, najlepszeDzialanie = -1, najwiekszyPrzyrostSumy = Integer.MIN_VALUE;

		for (int okrag = 0; okrag < okregiWyborcze.length; okrag++) {
			ArrayList<Kandydat> kandydaciPartii = new ArrayList<Kandydat>();

			for (Kandydat kandydat : okregiWyborcze[okrag].dajKandydatow()) {
				if (kandydat.dajPartie() == nazwa) {
					kandydaciPartii.add(kandydat);
				}
			}

			int[] sumaCechKandydatow = kandydaciPartii.get(0).dajCechy();
			for (int i = 1; i < kandydaciPartii.size(); i++) {
				sumaCechKandydatow = dodajWektory(sumaCechKandydatow, kandydaciPartii.get(i).dajCechy());
			}

			for (int i = 0; i < dzialania.length; i++) {
				int koszt = obliczKosztDzialania(dzialania[i], okregiWyborcze[okrag]);

				if (koszt <= budzet) {
					continue;
				}

				int przyrostSumy = wymnozWektory(sumaCechKandydatow, dzialania[i].dajWplyw());

				if (przyrostSumy > najwiekszyPrzyrostSumy) {
					najlepszyOkrag = okrag;
					najlepszeDzialanie = i;
					najwiekszyPrzyrostSumy = przyrostSumy;
				}
			}
		}

		if (najlepszyOkrag == -1) {
			return false;
		}

		okregiWyborcze[najlepszyOkrag].wykonajDzialanie(dzialania[najlepszeDzialanie]);
		budzet -= obliczKosztDzialania(dzialania[najlepszeDzialanie], okregiWyborcze[najlepszyOkrag]);

		return true;
	}


	private int[] dodajWektory(int[] a, int[] b) {
		int[] wynik = new int[a.length];

		for (int i = 0; i < a.length; i++) {
			wynik[i] = a[i] + b[i];
		}

		return a;
	}


	private int wymnozWektory(int[] a, int[] b) {
		int wynik = 0;
		
		for (int i = 0; i < a.length; i++) {
			wynik += a[i] * b[i];
		}

		return wynik;
	}
}
