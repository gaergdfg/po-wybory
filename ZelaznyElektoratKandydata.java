public class ZelaznyElektoratKandydata extends Wyborca {
	ZelaznyElektoratKandydata(
		String imie,
		String nazwisko,
		int numerPodstawowegoOkregu,
		int typWyborcy,
		int pozycjaKandydata
	) {
		super(imie, nazwisko, typWyborcy);
		ustawPozycjeKandydata(pozycjaKandydata);
	}


	ZelaznyElektoratKandydata(String[] dane, int pozycjaKandydata) {
		super(dane[0], dane[1], Integer.parseInt(dane[3]));
		ustawPozycjeKandydata(pozycjaKandydata);
	}


	public int wybierzKandydata(Kandydat[] kandydaci) {
		return dajPozycjeKandydata() - 1;
	}


	public void zaktualizujPozycjeKandydata(int liczbaPozycji) {
		ustawPozycjeKandydata(dajPozycjeKandydata() + liczbaPozycji);
	}
}
