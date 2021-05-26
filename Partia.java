public abstract class Partia {
	protected String nazwa;
	protected int budzet;


	Partia(String nazwa, int budzet) {
		this.nazwa = nazwa;
		this.budzet = budzet;
	}

	/**
	 * Zwraca true, gdy partia wykonala dzialanie lub
	 * false, gdy partia nie mogla wykonac juz dzialania
	 */
	public abstract boolean wybierzDzialanie(OkragWyborczy[] okregiWyborcze, Dzialanie[] dzialania);


	protected int obliczKosztDzialania(Dzialanie dzialanie, OkragWyborczy okragWyborczy) {
		return zsumujWartosciBezwgledneWektora(dzialanie.dajWplyw()) * okragWyborczy.dajLiczbeWyborcow();
	}


	protected int zsumujWartosciBezwgledneWektora(int[] wektor) {
		int suma = 0;

		for (int i = 0; i < wektor.length; i++) {
			suma += Math.abs(wektor[i]);
		}

		return suma;
	}


	public String dajNazwe() {
		return nazwa;
	}
}
