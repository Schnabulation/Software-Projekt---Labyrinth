package handler;

import labyrinth.Labyrinth;
import algorithmus.AStar;
import algorithmus.Algorithmus;
import algorithmus.Backtracking;

public class Main {

	private static Labyrinth labyrinth;
	private static Algorithmus alg1;
	private static Algorithmus alg2;
	
	
	public static void loadLab() {
		int[] start = new int[2]; // Variable f�r den Startpunkt definieren und bef�llen
		start[0] = 0;
		start[1] = 3;
		
		int[] ende = new int[2]; // Variable f�r den Endpunkt definieren und bef�llen
		ende[0] = 11;
		ende[1] = 2;
		
//		char[][] lab = { // Labyrinth definieren
//		{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
//				{ '0', '0', '0', '1', '0', '0', '1', '1', '0', '0', '0', '0' },
//				{ '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '1', '1' },
//				{ '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '0', '0' },
//				{ '0', '0', '1', '0', '1', '0', '1', '0', '0', '0', '0', '0' },
//				{ '0', '0', '1', '1', '1', '0', '1', '0', '0', '0', '0', '0' },
//				{ '0', '0', '0', '0', '1', '0', '1', '1', '1', '0', '1', '0' },
//				{ '0', '0', '0', '1', '1', '0', '1', '0', '1', '0', '1', '0' },
//				{ '0', '1', '1', '1', '0', '0', '0', '0', '1', '1', '1', '0' },
//				{ '0', '1', '0', '1', '1', '1', '1', '0', '1', '0', '1', '0' },
//				{ '0', '1', '1', '0', '0', '0', '1', '1', '1', '0', '0', '0' },
//				{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' } };
		
		char[][] lab = { // Labyrinth definieren
				{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
				{ '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '0' },
				{ '0', '1', '0', '0', '0', '1', '0', '0', '0', '0', '1', '1' },
				{ '1', '1', '0', '1', '1', '1', '0', '1', '0', '1', '1', '0' },
				{ '0', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1', '0' },
				{ '0', '0', '1', '1', '0', '1', '0', '1', '1', '0', '1', '0' },
				{ '0', '1', '1', '1', '1', '1', '0', '0', '1', '1', '1', '0' },
				{ '0', '1', '0', '0', '0', '1', '1', '0', '1', '1', '0', '0' },
				{ '0', '1', '1', '1', '0', '1', '0', '0', '0', '1', '1', '0' },
				{ '0', '0', '0', '1', '1', '1', '1', '1', '0', '1', '0', '0' },
				{ '0', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '0' },
				{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' } };

		labyrinth = new Labyrinth(lab, start, ende); // neues Labyrinth-Objekt generieren mit den obigen Daten
	}
	
	public static void loadAlg() {
		alg1 = new Backtracking(); // neues Algorithmus-Objekt generieren
//		alg1 = new AStar("Manhattan",true);
		alg2 = new AStar("Manhattan",false); // neues Algorithmus-Objekt generieren
	}
		
	public static void main(String[] args) {
		Main.loadLab(); // Labyrinth erstellen
		Main.loadAlg(); // Algorithmus erstellen
		
		Handler handler = new Handler(alg1, alg2, labyrinth); // Controller Objekt erstellen
		handler.start(); // L�sungsfindung starten
		
	}

}
