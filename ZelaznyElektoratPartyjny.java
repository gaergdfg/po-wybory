import java.util.Random;
import java.util.ArrayList;


public class ZelaznyElektoratPartyjny extends Wyborca {
	Random rand = new Random();

	private String wybranaPartia;


	ZelaznyElektoratPartyjny(
		String imie,
		String nazwisko,
		int numerPodstawowegoOkregu,
		int typWyborcy,
		String wybranaPartia
	) {
		super(imie, nazwisko, typWyborcy);
		this.wybranaPartia = wybranaPartia;
	}


	ZelaznyElektoratPartyjny(String[] dane) {
		super(dane[0], dane[1], Integer.parseInt(dane[3]));
		this.wybranaPartia = dane[4];
	}


	public int wybierzKandydata(Kandydat[] kandydaci) {
		ArrayList<Integer> faworyci = new ArrayList<Integer>();

		for (int i = 0; i < kandydaci.length; i++) {
			if (kandydaci[i].dajPartie().equals(wybranaPartia)) {
				faworyci.add(i);
			}
		}

		return faworyci.get(rand.nextInt(faworyci.size()));
	}
}
