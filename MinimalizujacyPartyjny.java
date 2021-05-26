import java.util.Random;
import java.util.ArrayList;


public class MinimalizujacyPartyjny extends Wyborca {
	Random rand = new Random();

	private int wybranaCecha;
	private String wybranaPartia;


	MinimalizujacyPartyjny(
		String imie,
		String nazwisko,
		int numerPodstawowegoOkregu,
		int typWyborcy,
		int wybranaCecha,
		String wybranaPartia
	) {
		super(imie, nazwisko, typWyborcy);
		this.wybranaCecha = wybranaCecha;
		this.wybranaPartia = wybranaPartia;
	}


	MinimalizujacyPartyjny(String[] dane) {
		super(dane[0], dane[1], Integer.parseInt(dane[3]));
		wybranaCecha = Integer.parseInt(dane[4]);
		wybranaPartia = dane[5];
	}


	public int wybierzKandydata(Kandydat[] kandydaci) {
		ArrayList<Integer> faworyci = new ArrayList<Integer>();

		int najmniejszaWartosc = Integer.MAX_VALUE;
		for (int i = 0; i < kandydaci.length; i++) {
			if (kandydaci[i].dajPartie().equals(wybranaPartia)) {
				if ((kandydaci[i].dajCechy())[wybranaCecha - 1] < najmniejszaWartosc) {
					faworyci.clear();
					faworyci.add(i);
					najmniejszaWartosc = (kandydaci[i].dajCechy())[wybranaCecha - 1];
				} else if ((kandydaci[i].dajCechy())[wybranaCecha - 1] == najmniejszaWartosc) {
					faworyci.add(i);
				}
			}
		}

		return faworyci.get(rand.nextInt(faworyci.size()));
	}
}
