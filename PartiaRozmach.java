public class PartiaRozmach extends Partia {
	PartiaRozmach(String nazwa, int budzet) {
		super(nazwa, budzet);
	}


	public boolean wybierzDzialanie(OkragWyborczy[] okregiWyborcze, Dzialanie[] dzialania) {
		int najlepszyOkrag = -1, najlepszeDzialanie = -1, najwyzszyKoszt = Integer.MIN_VALUE;

		for (int okrag = 0; okrag < okregiWyborcze.length; okrag++) {
			for (int i = 0; i < dzialania.length; i++) {
				int koszt = obliczKosztDzialania(dzialania[i], okregiWyborcze[okrag]);

				if (koszt > najwyzszyKoszt && koszt <= budzet) {
					najlepszyOkrag = okrag;
					najlepszeDzialanie = i;
					najwyzszyKoszt = koszt;
				}
			}
		}

		if (najlepszyOkrag == -1) {
			return false;
		}

		okregiWyborcze[najlepszyOkrag].wykonajDzialanie(dzialania[najlepszeDzialanie]);
		budzet -= najwyzszyKoszt;

		return true;
	}
}
