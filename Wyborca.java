public abstract class Wyborca {
	private String imie;
	private String nazwisko;
	private int typWyborcy;
	private String wybranyKandydat;

	private int pozycjaKandydata; // dla podklasy ZelaznyElektoratKandydata

	private int[] wagiCechWyborcy; // dla podklasy Wszechstronny


	Wyborca(
		String imie,
		String nazwisko,
		int typWyborcy
	) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.typWyborcy = typWyborcy;
		this.wybranyKandydat = "";
		this.pozycjaKandydata = -1;
		this.wagiCechWyborcy = new int[0];
	}


	/**
	 * Zwraca pozycje na liscie kandydata, na ktorego oddaje glos
	 */
	public abstract int wybierzKandydata(Kandydat[] kandydaci);


	public void oddajGlos(Kandydat kandydat) {
		wybranyKandydat = kandydat.dajImieNazwisko();
	}


	public int dajTyp() {
		return typWyborcy;
	}


	public String toString() {
		return imie + " " + nazwisko + " " + wybranyKandydat;
	}


	/* Poczatek funkcji dla podklasy ZelaznyElektoratKandydata */
	public void ustawWybranegoKandydata(String kandydat) {
		wybranyKandydat = kandydat;
	}
	
	
	protected int dajPozycjeKandydata() {
		return pozycjaKandydata;
	}


	protected void ustawPozycjeKandydata(int pozycja) {
		pozycjaKandydata = pozycja;
	}
	/* Koniec funkcji dla podklasy ZelaznyElektoratKandydata */


	/* Poczatek funkcji dla podklasy Wszechstronny */
	public void zmienPoglady(Dzialanie dzialanie) {
		for (int i = 0; i < wagiCechWyborcy.length; i++) {
			wagiCechWyborcy[i] += dzialanie.dajWplyw()[i];
		}
	}


	protected int[] dajWagiCech() {
		return wagiCechWyborcy;
	}


	protected void ustawWagiCech(int[] wagiCech) {
		wagiCechWyborcy = wagiCech;
	}
	/* Koniec funkcji dla podklasy Wszechstronny */
}
