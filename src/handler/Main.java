package handler;

import labyrinth.Labyrinth;
import algorithmus.Algorithmus;
import algorithmus.Backtracking;

public class Main {

	private static Labyrinth labyrinth;
	private static Algorithmus alg;
	
	
	public static void loadLab() {
		int[] start = new int[2]; // Variable für den Startpunkt definieren und befüllen
		start[0] = 0;
		start[1] = 3;
		
		int[] ende = new int[2]; // Variable für den Endpunkt definieren und befüllen
		ende[0] = 11;
		ende[1] = 2;
		
		char[][] lab = { // Labyrinth definieren
			  {'0','0','0','0','0','0','0','0','0','0','0','0'},
			  {'0','0','0','1','0','0','1','1','0','0','0','0'},
			  {'0','0','0','1','1','0','1','0','0','1','1','1'},
			  {'1','1','1','0','1','0','1','1','1','1','0','0'},
			  {'0','0','1','0','1','0','1','0','0','0','0','0'},
			  {'0','0','1','1','1','0','1','0','0','0','0','0'},
			  {'0','0','0','0','1','0','1','1','1','0','1','0'},
			  {'0','0','0','1','1','0','1','0','1','0','1','0'},
			  {'0','1','1','1','0','0','0','0','1','1','1','0'},
			  {'0','1','0','1','1','1','1','0','1','0','1','0'},
			  {'0','1','1','0','0','0','1','1','1','0','0','0'},
			  {'0','0','0','0','0','0','0','0','0','0','0','0'}};
		
		labyrinth = new Labyrinth(lab, start, ende); // neues Labyrinth-Objekt generieren mit den obigen Daten
	}
	
	public static void loadAlg() {
		alg = new Backtracking(); // neues Algorithmus-Objekt generieren
	}
		
	public static void main(String[] args) {
		Main.loadLab(); // Labyrinth erstellen
		Main.loadAlg(); // Algorithmus erstellen
		
		Handler handler = new Handler(alg, labyrinth); // Controller Objekt erstellen
		handler.solveLab(); // Lösungsfindung starten
	}

}
