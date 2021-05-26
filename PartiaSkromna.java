public class PartiaSkromna extends Partia {
	PartiaSkromna(String nazwa, int budzet) {
		super(nazwa, budzet);
	}


	public boolean wybierzDzialanie(OkragWyborczy[] okregiWyborcze, Dzialanie[] dzialania) {
		int najlepszyOkrag = -1, najlepszeDzialanie = -1, najnizszyKoszt = Integer.MAX_VALUE;

		for (int okrag = 0; okrag < okregiWyborcze.length; okrag++) {
			for (int i = 0; i < dzialania.length; i++) {
				int koszt = obliczKosztDzialania(dzialania[i], okregiWyborcze[okrag]);

				if (koszt < najnizszyKoszt && koszt <= budzet) {
					najlepszyOkrag = okrag;
					najlepszeDzialanie = i;
					najnizszyKoszt = koszt;
				}
			}
		}

		if (najlepszyOkrag == -1) {
			return false;
		}

		okregiWyborcze[najlepszyOkrag].wykonajDzialanie(dzialania[najlepszeDzialanie]);
		budzet -= najnizszyKoszt;

		return true;
	}
}
