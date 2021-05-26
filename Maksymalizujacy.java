import java.util.Random;
import java.util.ArrayList;


public class Maksymalizujacy extends Wyborca {
	Random rand = new Random();

	private int wybranaCecha;


	Maksymalizujacy(
		String imie,
		String nazwisko,
		int numerPodstawowegoOkregu,
		int typWyborcy,
		int wybranaCecha
	) {
		super(imie, nazwisko, typWyborcy);
		this.wybranaCecha = wybranaCecha;
	}


	Maksymalizujacy(String[] dane) {
		super(dane[0], dane[1], Integer.parseInt(dane[3]));
		wybranaCecha = Integer.parseInt(dane[4]);
	}


	public int wybierzKandydata(Kandydat[] kandydaci) {
		ArrayList<Integer> faworyci = new ArrayList<Integer>();

		int najwiekszaWartosc = Integer.MIN_VALUE;
		for (int i = 0; i < kandydaci.length; i++) {
			if ((kandydaci[i].dajCechy())[wybranaCecha - 1] > najwiekszaWartosc) {
				faworyci.clear();
				faworyci.add(i);
				najwiekszaWartosc = (kandydaci[i].dajCechy())[wybranaCecha - 1];
			} else if ((kandydaci[i].dajCechy())[wybranaCecha - 1] == najwiekszaWartosc) {
				faworyci.add(i);
			}
		}

		return faworyci.get(rand.nextInt(faworyci.size()));
	}
}
