import java.util.Arrays;


public class OkragWyborczy {
	private int numer;
	private int numerScalonegoOkregu;
	private int liczbaWyborcow;
	private int liczbaKandydatow;
	private Wyborca[] wyborcy;
	private Kandydat[] kandydaci;


	OkragWyborczy(int numer, int liczbaWyborcow, Wyborca[] wyborcy, Kandydat[] kandydaci) {
		this.numer = numer;
		this.numerScalonegoOkregu = 0;
		this.liczbaWyborcow = liczbaWyborcow;
		this.liczbaKandydatow = liczbaWyborcow / 10;
		this.wyborcy = wyborcy;
		this.kandydaci = kandydaci;
	}


	OkragWyborczy(OkragWyborczy a, OkragWyborczy b, int liczbaPartii) {
		this.numer = a.numer;
		this.numerScalonegoOkregu = b.numer;
		this.liczbaWyborcow = a.liczbaWyborcow + b.liczbaWyborcow;
		this.liczbaKandydatow = a.liczbaKandydatow + b.liczbaKandydatow;

		Wyborca[] wszyscyWyborcy = Arrays.copyOf(a.wyborcy, a.wyborcy.length + b.wyborcy.length);
		System.arraycopy(b.wyborcy, 0, wszyscyWyborcy, a.wyborcy.length, b.wyborcy.length);
		this.wyborcy = wszyscyWyborcy;

		for (int i = a.wyborcy.length; i < this.wyborcy.length; i++) {
			if (this.wyborcy[i].dajTyp() == 2) {
				this.wyborcy[i].ustawPozycjeKandydata(this.wyborcy[i].dajPozycjeKandydata() + a.wyborcy.length / 10);
			}
		}

		Kandydat[] wszyscyKandydaci = Arrays.copyOf(a.kandydaci, a.kandydaci.length + b.kandydaci.length);
		System.arraycopy(b.kandydaci, 0, wszyscyKandydaci, a.kandydaci.length, b.kandydaci.length);
		this.kandydaci = wszyscyKandydaci;
	}


	public void wykonajDzialanie(Dzialanie dzialanie) {
		for (Wyborca wyborca : wyborcy) {
			wyborca.zmienPoglady(dzialanie);
		}
	}


	public Wyborca[] dajWyborcow() {
		return wyborcy;
	}


	public Kandydat[] dajKandydatow() {
		return kandydaci;
	}


	public int dajLiczbeWyborcow() {
		return liczbaWyborcow;
	}


	/**
	 * wypisuje numer okregu a nastepnie
	 * dane wszystkich osob (wyborcow i kandydatow) z okregu,
	 * pomija dane o mandatach partii
	 */
	public String toString() {
		String opis = "";

		opis += numer + (String)(numerScalonegoOkregu > 0 ? " " + numerScalonegoOkregu : "") + "\n";

		for (Wyborca wyborca : wyborcy) {
			opis += wyborca + "\n";
		}

		for (Kandydat kandydat : kandydaci) {
			opis += kandydat + "\n";
		}

		return opis;
	}
}
