package algorithmus;

import labyrinth.Labyrinth;

public class Backtracking extends Algorithmus {

	private boolean schrittGemacht;
	
	public Backtracking() {
		schrittGemacht = true;
	}

	@Override
	public String nextStep(char aktuellePosition) {
		if (schrittGemacht) {
			schrittGemacht = false;
			if (aktuellePosition != '0') { // keine Sackgasse, weiter pr�fen
				if (aktuellePosition != 'x') { // wir waren noch nie da, weiter
												// pr�fen
					if (aktuellePosition == '1') { // Weg gefunden, eins weiter
						schrittGemacht = true;
						return "gerade";
					}
				}
			}
			return "rechts";
		} else {
		if (aktuellePosition != '0') { // keine Sackgasse, weiter pr�fen
			if (aktuellePosition != 'x') { // wir waren noch nie da, weiter
											// pr�fen
				if (aktuellePosition == '1') { // Weg gefunden, eins weiter
					schrittGemacht = true;
					return "gerade";
				}
			}
		}
		return "links";
	} }

}
