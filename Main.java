public class Main {
	public static void main(String[] argumenty) {
		if (argumenty.length != 1) {
			return;
		}

		Wejscie wejscie = new Wejscie();

		Bajtocja bajtocja = wejscie.zaladujBajtocjeZPliku(argumenty[0]);
		if (bajtocja == null) {
			return;
		}

		bajtocja.przeprowadzWybory();

		SystemPrzeliczaniaGlosow[] systemy = {new MetodaDHondta(), new MetodaSainteLague(), new MetodaHareNiemeyera()};

		for (SystemPrzeliczaniaGlosow system : systemy) {
			bajtocja.zliczMandaty(system);
		}
	}
}
