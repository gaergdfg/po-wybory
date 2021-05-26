import java.util.HashMap;
import java.util.Random;


/**
 * Nikt z mieszkancow Bajtocji nie wie, jak Partia Murarzy osiagnela taki sukces i
 * startuje w wyborach. Jako, ze zaden z jej czlonkow nie zna sie na polityce,
 * musza oni polegac na szczesciu w nadchodzacej kampanii. Plan partii jest
 * prosty: beda rzucac kostka, by zadecydowac o dzialaniach, ktore podejma
 * podczas kampanii. Moze to nie jest najlepsza strategia, ale zawsze jakas.
 */
public class PartiaMurarzy extends Partia {
	Random rand = new Random();


	PartiaMurarzy(String nazwa, int budzet) {
		super(nazwa, budzet);
	}


	public boolean wybierzDzialanie(OkragWyborczy[] okregiWyborcze, Dzialanie[] dzialania) {
		HashMap<Dzialanie, OkragWyborczy> dostepneDzialania = new HashMap<Dzialanie, OkragWyborczy>();

		for (OkragWyborczy okragWyborczy : okregiWyborcze) {
			for (Dzialanie dzialanie : dzialania) {
				if (obliczKosztDzialania(dzialanie, okragWyborczy) <= budzet) {
					dostepneDzialania.put(dzialanie, okragWyborczy);
				}
			}
		}

		if (dostepneDzialania.size() == 0) {
			return false;
		}

		Object[] listaDostepnychDzialan = dostepneDzialania.keySet().toArray();
		Dzialanie najlepszeDzialanie = (Dzialanie) listaDostepnychDzialan[rand.nextInt(listaDostepnychDzialan.length)];

		dostepneDzialania.get(najlepszeDzialanie).wykonajDzialanie(najlepszeDzialanie);
		budzet -= obliczKosztDzialania(najlepszeDzialanie, dostepneDzialania.get(najlepszeDzialanie));

		return true;
	}
}
