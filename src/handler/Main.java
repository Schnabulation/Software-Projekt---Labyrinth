package handler;

import gui.Gui;
import labyrinth.Labyrinth;
import labyrinth.LabyrinthGenerator;
import algorithmus.AStar;
import algorithmus.Algorithmus;
import algorithmus.Backtracking;

public class Main {

	private static Labyrinth labyrinth;
	private static Algorithmus alg1;
	private static Algorithmus alg2;
	
	public static void loadAlg() {
		//alg1 = new Backtracking(); // neues Algorithmus-Objekt generieren
		alg1 = new AStar("Manhattan",true);
		alg2 = new AStar("Manhattan",false); // neues Algorithmus-Objekt generieren
	}
		
	public static void main(String[] args) {
//		labyrinth = LabyrinthGenerator.loadLab(1); // Labyrinth erstellen
//		Main.loadAlg(); // Algorithmus erstellen
//		
//		Handler handler = new Handler(alg1, alg2, labyrinth); // Controller Objekt erstellen
//		handler.start(); // L�sungsfindung starten
		
		new Gui();
		
	}

}
