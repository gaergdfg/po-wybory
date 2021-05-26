public class Bajtocja {
	private OkragWyborczy[] okregiWyborcze;
	private Dzialanie[] listaDostepnychDzialan;
	private Partia[] partie;
	
	int[][] glosyPrzyznaneDlaPartii;


	Bajtocja(OkragWyborczy[] okregiWyborcze, Dzialanie[] listaDostepnychDzialan, Partia[] partie) {
		this.okregiWyborcze = okregiWyborcze;
		this.listaDostepnychDzialan = listaDostepnychDzialan;
		this.partie = partie;
	}


	/**
	 * Zmienia stan Bajtocji na po-wyborach (wszyscy obywatele oddali juz swoje
	 * glosy, ktore zostaly policzone, jedyne czego brakuje, to wyliczenie
	 * liczby mandatow, ktore otrzymaly partie)
	 */
	public void przeprowadzWybory() {
		// przeprowadz kampanie wszystkich partii
		for (Partia partia : partie) {
			while (partia.wybierzDzialanie(okregiWyborcze, listaDostepnychDzialan)) {}
		}

		// przeprowadz wlasciwe wybory
		for (OkragWyborczy okragWyborczy : okregiWyborcze) {
			for (Wyborca wyborca : okragWyborczy.dajWyborcow()) {
				int pozycjaWybranegoKandydata = wyborca.wybierzKandydata(okragWyborczy.dajKandydatow());
				okragWyborczy.dajKandydatow()[pozycjaWybranegoKandydata].oddajGlos();
				wyborca.oddajGlos(okragWyborczy.dajKandydatow()[pozycjaWybranegoKandydata]);
			}
		}

		// zlicz glosy oddane na partie w poszczegolnych okregach
		glosyPrzyznaneDlaPartii = new int[okregiWyborcze.length][partie.length];
		for (int okrag = 0; okrag < okregiWyborcze.length; okrag++) {
			for (Kandydat kandydat : okregiWyborcze[okrag].dajKandydatow()) {
				glosyPrzyznaneDlaPartii[okrag][znajdzIndeksPartii(kandydat.dajPartie())] += kandydat.dajLiczbeGlosow();
			}
		}
	}


	private int znajdzIndeksPartii(String nazwaPartii) {
		for (int i = 0; i < partie.length; i++) {
			if (partie[i].dajNazwe().equals(nazwaPartii)) {
				return i;
			}
		}
		
		return -1;
	}


	/**
	 * Zlicza mandaty danym systemem oraz wypisuje dane wymagane w zadaniu
	 */
	public void zliczMandaty(SystemPrzeliczaniaGlosow system) {
		System.out.println(system);

		int[] przyznaneMandatyGlobalnie = new int[partie.length];

		for (int okrag = 0; okrag < okregiWyborcze.length; okrag++) {
			System.out.println(okregiWyborcze[okrag]);

			int[] przyznaneMandaty = system.przelicz(glosyPrzyznaneDlaPartii[okrag], okregiWyborcze[okrag].dajWyborcow().length / 10);

			for (int partia = 0; partia < partie.length; partia++) {
				System.out.println(partie[partia].dajNazwe() + " " + przyznaneMandaty[partia]);
				przyznaneMandatyGlobalnie[partia] += przyznaneMandaty[partia];
			}
		}

		for (int partia = 0; partia < partie.length; partia++) {
			System.out.println(partie[partia].dajNazwe() + " " + przyznaneMandatyGlobalnie[partia]);
		}
	}
}
