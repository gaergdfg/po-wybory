public class Kandydat {
	private String imie;
	private String nazwisko;
	private String nazwaPartii;
	private int pozycjaNaLiscie;
	private int[] cechy;
	private int liczbaOtrzymanychGlosow;


	Kandydat(
		String imie,
		String nazwisko,
		String nazwaPartii,
		int pozycjaNaLiscie,
		int[] cechy
	) {
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.nazwaPartii = nazwaPartii;
		this.pozycjaNaLiscie = pozycjaNaLiscie;
		this.cechy = cechy;
		this.liczbaOtrzymanychGlosow = 0;
	}


	public void oddajGlos() {
		liczbaOtrzymanychGlosow++;
	}


	public String dajImieNazwisko() {
		return imie + " " + nazwisko;
	}


	public String dajPartie() {
		return nazwaPartii;
	}


	public int[] dajCechy() {
		return cechy;
	}


	public int dajLiczbeGlosow() {
		return liczbaOtrzymanychGlosow;
	}


	public String toString() {
		return imie + " " + nazwisko + " " + nazwaPartii + " " + pozycjaNaLiscie + " " + liczbaOtrzymanychGlosow;
	}
}
