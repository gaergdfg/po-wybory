import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;


public class Wejscie {
	Wejscie() {}

	
	public Bajtocja zaladujBajtocjeZPliku(String sciezkaDoPliku) {
		try {
			// ========================= CZYTANIE WEJSCIA =========================
			File plikWejsciowy = new File(sciezkaDoPliku);
			Scanner wejscie = new Scanner(plikWejsciowy);

			String[] rozmiarDanych = wejscie.nextLine().split(" ");
			int liczbaOkregow = Integer.parseInt(rozmiarDanych[0]);
			int liczbaPartii = Integer.parseInt(rozmiarDanych[1]);
			int liczbaDzialan = Integer.parseInt(rozmiarDanych[2]);
			int liczbaCechKandydatow = Integer.parseInt(rozmiarDanych[3]);

			int faktycznaLiczbaOkregow = liczbaOkregow;

			String[] scalanieOkregow = wejscie
				.nextLine()
				.replaceAll("\\(\\d,(\\d)\\)", "$1")
				.split(" ");
			boolean[] czyOkragScalony = new boolean[liczbaOkregow + 1];
			for (int i = 1; i < scalanieOkregow.length; i++) {
				czyOkragScalony[Integer.parseInt(scalanieOkregow[i])] = true;
				faktycznaLiczbaOkregow--;
			}

			String[] nazwyPartii = wejscie.nextLine().split(" ");
			HashMap<String, Integer> kolejnoscPartii = new HashMap<String, Integer>();
			for (int i = 0; i < liczbaPartii; i++) {
				kolejnoscPartii.put(nazwyPartii[i], i);
			} 

			String[] listaBudzetowPartii = wejscie.nextLine().split(" ");
			int[] budzetPartii = new int[listaBudzetowPartii.length];
			for (int i = 0; i < listaBudzetowPartii.length; i++) {
				budzetPartii[i] = Integer.parseInt(listaBudzetowPartii[i]);
			}

			String[] strategiaPartii = wejscie.nextLine().split(" ");

			String[] listaRozmiarowOkregow = wejscie.nextLine().split(" ");
			int[] liczbaWyborcowOkregu = new int[liczbaOkregow];
			for (int i = 0; i < listaRozmiarowOkregow.length; i++) {
				liczbaWyborcowOkregu[i] = Integer.parseInt(listaRozmiarowOkregow[i]);
			}

			ArrayList<ArrayList<String>> informacjeKandydata = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < liczbaOkregow; i++) {
				informacjeKandydata.add(i, new ArrayList<String>());
			}
			for (int okrag = 1; okrag <= liczbaOkregow; okrag++) {
				for (int i = 0; i < liczbaWyborcowOkregu[okrag - 1] * liczbaPartii / 10; i++) {
					String daneKandydata = wejscie.nextLine();
					informacjeKandydata.get(okrag - 1).add(daneKandydata);
				}
			}

			ArrayList<ArrayList<String>> informacjeWyborcy = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < liczbaOkregow; i++) {
				informacjeWyborcy.add(i, new ArrayList<String>());
			}
			for (int okrag = 1; okrag <= liczbaOkregow; okrag++) {
				for (int i = 0; i < liczbaWyborcowOkregu[okrag - 1]; i++) {
					String daneWyborcy = wejscie.nextLine();
					informacjeWyborcy.get(okrag - 1).add(daneWyborcy);
				}
			}

			int[][] statystykiDzialan = new int[liczbaDzialan][liczbaCechKandydatow];
			for (int nrDzialania = 0; nrDzialania < liczbaDzialan; nrDzialania++) {
				String[] informacjeDzialania = wejscie.nextLine().replaceAll("^\\s*", "").split(" ");
				for (int i = 0; i < liczbaCechKandydatow; i++) {
					statystykiDzialan[nrDzialania][i] = Integer.parseInt(informacjeDzialania[i]);
				}
			}

			// ========================= INICJALIZACJA =========================

			OkragWyborczy[] okregiWyborcze = new OkragWyborczy[liczbaOkregow];
			for (int nrOkregu = 1; nrOkregu <= liczbaOkregow; nrOkregu++) {
				// WYBORCY
				Wyborca[] wyborcy = new Wyborca[liczbaWyborcowOkregu[nrOkregu - 1]];

				for (int i = 0; i < liczbaWyborcowOkregu[nrOkregu - 1]; i++) {
					int pozycjaPartiiNaLiscie = 0; // dotyczy tylko ZelaznegoElektoratuWyborcy

					if (Integer.parseInt(informacjeWyborcy.get(nrOkregu - 1).get(i).split(" ")[3]) == 2) {
						pozycjaPartiiNaLiscie = 
							kolejnoscPartii.get((informacjeWyborcy.get(nrOkregu - 1).get(i).split(" "))[4]) *
							liczbaWyborcowOkregu[nrOkregu - 1] / 10;
					}

					wyborcy[i] = stworzWyborce(
						informacjeWyborcy.get(nrOkregu - 1).get(i).split(" "),
						liczbaCechKandydatow,
						pozycjaPartiiNaLiscie
					);
				}

				// KANDYDACI
				Kandydat[] kandydaci = new Kandydat[liczbaWyborcowOkregu[nrOkregu - 1] / 10 * liczbaPartii];

				for (int i = 0; i < liczbaWyborcowOkregu[nrOkregu - 1] / 10 * liczbaPartii; i++) {
					kandydaci[i] = stworzKandydata(informacjeKandydata.get(nrOkregu - 1).get(i).split(" "), liczbaCechKandydatow);
				}

				// OKRAG
				okregiWyborcze[nrOkregu - 1] = new OkragWyborczy(nrOkregu, liczbaWyborcowOkregu[nrOkregu - 1], wyborcy, kandydaci);
			}

			OkragWyborczy[] scaloneOkregiWyborcze = new OkragWyborczy[faktycznaLiczbaOkregow];
			for (int i = 0, pozycja = 0; i < liczbaOkregow; i++) {
				if (czyOkragScalony[i + 1]) {
					scaloneOkregiWyborcze[pozycja - 1] = new OkragWyborczy(okregiWyborcze[i - 1], okregiWyborcze[i], liczbaPartii);
				} else {
					scaloneOkregiWyborcze[pozycja++] = okregiWyborcze[i];
				}
			}

			Dzialanie[] dzialania = new Dzialanie[liczbaDzialan];
			for (int i = 0; i < liczbaDzialan; i++) {
				dzialania[i] = new Dzialanie(statystykiDzialan[i]);
			}

			Partia[] partie = new Partia[liczbaPartii];
			for (int i = 0; i < liczbaPartii; i++) {
				switch (strategiaPartii[i]) {
					case "R":
						partie[i] = new PartiaRozmach(nazwyPartii[i], budzetPartii[i]);
						break;
					case "S":
						partie[i] = new PartiaSkromna(nazwyPartii[i], budzetPartii[i]);
						break;
					case "W":
						partie[i] = new PartiaMurarzy(nazwyPartii[i], budzetPartii[i]);
						break;
					case "Z":
						partie[i] = new PartiaZachlanna(nazwyPartii[i], budzetPartii[i]);
						break;
					default:
						return null;
				}
			}

			wejscie.close();

			return new Bajtocja(scaloneOkregiWyborcze, dzialania, partie);
		} catch (Exception e) {
			return null;
		}
	}


	private Wyborca stworzWyborce(String[] daneWyborcy, int liczbaCechKandydatow, int pozycjaPartiiNaLiscie) {
		switch (Integer.parseInt(daneWyborcy[3])) {
			case 1:
				return new ZelaznyElektoratPartyjny(daneWyborcy);
			case 2:
				return new ZelaznyElektoratKandydata(daneWyborcy, pozycjaPartiiNaLiscie + Integer.parseInt(daneWyborcy[5]));
			case 3:
				return new Minimalizujacy(daneWyborcy);
			case 4:
				return new Maksymalizujacy(daneWyborcy);
			case 5:
				return new Wszechstronny(daneWyborcy, liczbaCechKandydatow);
			case 6:
				return new MinimalizujacyPartyjny(daneWyborcy);
			case 7:
				return new MaksymalizujacyPartyjny(daneWyborcy);
			case 8:
				return new WszechstronnyPartyjny(daneWyborcy, liczbaCechKandydatow);
			default:
				return null;
		}
	}


	private Kandydat stworzKandydata(String[] daneKandydata, int liczbaCechKandydatow) {
		int[] cechyKandydata = new int[liczbaCechKandydatow];
		for (int nrCechy = 0; nrCechy < liczbaCechKandydatow; nrCechy++) {
			cechyKandydata[nrCechy] = Integer.parseInt(daneKandydata[5 + nrCechy]);
		}

		return new Kandydat(
			daneKandydata[0],
			daneKandydata[1],
			daneKandydata[3],
			Integer.parseInt(daneKandydata[4]),
			cechyKandydata
		);
	}
}