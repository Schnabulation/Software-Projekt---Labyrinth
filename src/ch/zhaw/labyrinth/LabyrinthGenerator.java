package ch.zhaw.labyrinth;

/* ---------------------------------------------------------------------------------------------
 * LabyrinthGenerator: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Der LabyrinthGenerator bietet eine statische Methode an, welche verschiedene Labyrinthe
 * generiert. Diese Klasse ist also dazu da, die verschiedenen Labyrinthe zu generieren.
 * --------------------------------------------------------------------------------------------- */

public class LabyrinthGenerator {
	
	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */

	static Labyrinth labyrinth;
	
	/* ---------------------------------------------
	 * Methoden
	 * --------------------------------------------- */	
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: loadLab
	 *  
	 * Author: Steve Heller
	 * 
	 * Diese statische Methode erwartet als Eingabe eine ID. Als return Wert wird ein Objekt vom
	 * Typ Labyrinth zur�ckgeliefert.
	 * --------------------------------------------------------------------------------------------- */

	public static Labyrinth loadLab(int labID) {

		if (labID == 1) {

			int[] start = new int[2];
			start[0] = 0;
			start[1] = 3;

			int[] ende = new int[2];
			ende[0] = 11;
			ende[1] = 2;
			
			int rastermass = 20;

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

			labyrinth = new Labyrinth(lab, start, ende, labID, rastermass);
		} else if (labID == 2) {
			
			int rastermass = 20;

			int[] start = new int[2];
			start[0] = 0;
			start[1] = 3;

			int[] ende = new int[2];
			ende[0] = 11;
			ende[1] = 2;

			char[][] lab = { // Labyrinth definieren
					{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
					{ '0', '0', '0', '1', '0', '0', '1', '1', '0', '0', '0', '0' },
					{ '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '1', '1' },
					{ '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '0', '0' },
					{ '0', '0', '1', '0', '1', '0', '1', '0', '0', '0', '0', '0' },
					{ '0', '0', '1', '1', '1', '0', '1', '0', '0', '0', '0', '0' },
					{ '0', '0', '0', '0', '1', '0', '1', '1', '1', '0', '1', '0' },
					{ '0', '0', '0', '1', '1', '0', '1', '0', '1', '0', '1', '0' },
					{ '0', '1', '1', '1', '0', '0', '0', '0', '1', '1', '1', '0' },
					{ '0', '1', '0', '1', '1', '1', '1', '0', '1', '0', '1', '0' },
					{ '0', '1', '1', '0', '0', '0', '1', '1', '1', '0', '0', '0' },
					{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' } };

			labyrinth = new Labyrinth(lab, start, ende, labID, rastermass);
		} else if (labID == 3) {
			
			int rastermass = 10;

			int[] start = new int[2];
			start[0] = 0;
			start[1] = 7;

			int[] ende = new int[2];
			ende[0] = 16;
			ende[1] = 23;

			char[][] lab = { // Labyrinth definieren
					{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
					{ '0', '1', '1', '0', '0', '0', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '0', '1', '0' },
					{ '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '0', '0', '1', '0', '0', '1', '0', '0', '1', '0', '1', '0' },    
					{ '0', '1', '0', '0', '0', '1', '0', '0', '0', '1', '1', '0', '1', '0', '1', '1', '0', '1', '1', '1', '1', '0', '1', '0' },
					{ '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '1', '1', '0', '0', '1', '0', '0', '0', '1', '0' },
					{ '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '0' },
					{ '0', '1', '1', '1', '0', '1', '1', '0', '0', '0', '1', '0', '1', '0', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0' },
					{ '1', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0', '0', '1', '0', '1', '0', '1', '0', '0', '1', '0', '0', '0', '0' },
					{ '0', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '0', '1', '1', '0' },
					{ '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '0', '0', '0', '1', '1', '1', '1', '0', '0', '1', '0' },
					{ '0', '0', '0', '0', '1', '1', '0', '0', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0' },
					{ '0', '1', '1', '0', '0', '0', '0', '1', '1', '0', '0', '1', '1', '0', '1', '0', '0', '0', '1', '1', '0', '1', '1', '0' },
					{ '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '0', '0', '1', '1', '1', '1', '1', '0', '0', '0', '0', '0' },
					{ '0', '0', '0', '0', '1', '0', '1', '0', '1', '1', '0', '0', '0', '1', '1', '0', '0', '1', '1', '1', '1', '1', '1', '0' },
					{ '0', '1', '1', '0', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '0', '0', '1', '1', '0' },
					{ '0', '1', '1', '1', '1', '1', '1', '1', '0', '0', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '0', '1', '1', '0' },
					{ '0', '0', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1', '1', '0', '0', '1', '1', '0' },
					{ '0', '1', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '1', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0' },
					{ '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '0', '0', '0' },
					{ '0', '1', '1', '0', '0', '1', '0', '0', '1', '1', '0', '1', '0', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '0' },
					{ '0', '1', '0', '0', '1', '1', '0', '1', '1', '0', '0', '1', '0', '0', '1', '1', '1', '0', '1', '0', '0', '0', '1', '0' },
					{ '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1', '1', '0', '1', '1', '0' },
					{ '0', '1', '0', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0' },
					{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '0', '0', '0', '0', '0', '0', '0' } };

			labyrinth = new Labyrinth(lab, start, ende, labID, rastermass);
		} else if (labID == 4) {
		
		int rastermass = 10;

		int[] start = new int[2];
		start[0] = 0;
		start[1] = 3;

		int[] ende = new int[2];
		ende[0] = 12;
		ende[1] = 23;

		char[][] lab = { // Labyrinth definieren
				{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
				{'0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0'},
				{'0','1','0','0','0','1','0','0','0','1','0','0','0','1','0','1','0','1','0','0','0','1','1','0'},
				{'1','1','0','1','1','1','1','0','1','1','0','1','1','1','0','1','0','1','0','1','1','1','1','0'},
				{'0','1','0','1','1','1','1','0','1','1','0','1','1','1','0','1','0','1','0','1','1','1','1','0'},
				{'0','1','0','0','0','1','1','0','1','1','0','0','0','1','0','1','0','1','0','0','0','1','1','0'},
				{'0','1','1','1','0','1','1','0','1','1','0','1','1','1','0','0','0','1','0','1','1','1','1','0'},
				{'0','1','1','1','0','1','1','0','1','1','0','1','1','1','0','0','0','1','0','1','1','1','1','0'},
				{'0','1','0','0','0','1','1','0','1','1','0','0','0','1','1','0','1','1','0','0','0','1','1','0'},
				{'0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0'},
				{'0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0'},
				{'0','1','1','1','0','0','0','1','0','0','0','1','0','0','0','1','0','0','0','1','1','1','1','0'},
				{'0','1','1','1','0','1','0','1','0','1','1','1','1','0','1','1','0','1','0','1','1','1','1','0'},
				{'0','1','1','1','0','1','0','1','0','1','1','1','1','0','1','1','0','1','0','1','1','1','1','0'},
				{'0','1','1','1','0','0','0','1','0','0','0','1','1','0','1','1','0','1','0','1','1','1','1','0'},
				{'0','1','1','1','0','0','1','1','0','1','1','1','1','0','1','1','0','1','0','1','1','1','1','0'},
				{'0','1','1','1','0','1','0','1','0','1','1','1','1','0','1','1','0','1','0','1','1','1','1','0'},
				{'0','1','1','1','0','1','0','1','0','0','0','1','1','0','1','1','0','0','0','1','1','1','1','0'},
				{'0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0'},
				{'0','1','0','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','1','0'},
				{'0','1','0','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','1','0'},
				{'0','1','0','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','1','0'},
				{'0','1','0','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','1','0'},
				{'0','0','0','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','0','0'} };
		
		labyrinth = new Labyrinth(lab, start, ende, labID, rastermass);
	} else if (labID == 5) {
		
		int rastermass = 10;

		int[] start = new int[2];
		start[0] = 1;
		start[1] = 0;

		int[] ende = new int[2];
		ende[0] = 23;
		ende[1] = 7;

		char[][] lab = { // Labyrinth definieren
				{'0','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
				{'0','1','0','1','1','1','1','1','1','0','0','1','1','1','0','0','1','1','0','1','0','0','1','0'},
				{'0','1','0','1','1','1','1','0','0','0','1','1','1','1','1','0','0','1','0','1','0','0','1','0'},
				{'0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0','1','0'},
				{'0','1','0','1','1','1','1','1','0','0','0','1','0','1','1','1','0','1','1','1','1','0','1','0'},
				{'0','0','0','0','0','0','1','1','0','1','0','0','0','1','1','1','0','1','1','1','1','0','1','0'},
				{'0','1','1','1','1','0','0','0','0','1','1','1','1','1','0','1','0','0','0','0','1','0','1','0'},
				{'0','1','1','1','1','0','1','1','1','1','0','1','1','1','0','1','1','1','1','0','1','0','1','1'},
				{'0','1','1','0','0','0','1','1','1','0','0','1','1','1','1','1','1','1','1','0','1','0','1','0'},
				{'0','1','1','1','1','1','1','1','1','1','1','1','1','1','0','0','0','0','0','0','1','0','1','0'},
				{'0','1','1','0','1','1','1','0','0','0','1','1','1','1','1','1','1','1','0','1','1','0','1','0'},
				{'0','1','1','0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','0','1','0'},
				{'0','1','1','0','1','1','1','1','1','1','1','0','0','0','0','0','1','1','0','1','1','0','1','0'},
				{'0','1','1','0','0','0','0','0','1','1','1','0','1','1','1','0','1','1','0','1','1','0','1','0'},
				{'0','1','1','1','1','1','1','1','0','1','1','0','1','1','1','0','0','0','0','1','1','0','1','0'},
				{'0','1','1','1','1','1','1','1','0','1','1','0','1','1','1','0','1','1','1','1','1','0','1','0'},
				{'0','1','1','0','1','1','1','1','0','1','1','0','1','0','1','0','1','1','1','1','1','0','1','0'},
				{'0','1','1','0','1','0','1','1','0','1','1','0','1','0','1','0','1','1','1','1','1','0','1','0'},
				{'0','1','1','0','1','0','1','1','0','1','1','0','1','1','1','0','1','1','1','0','0','0','1','0'},
				{'0','1','1','0','0','0','0','1','1','1','1','1','1','1','1','0','1','1','1','1','1','0','1','0'},
				{'0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0','1','1','1','1','1','0','1','0'},
				{'0','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','1','0'},
				{'0','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','0'},
				{'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'} };
		
		labyrinth = new Labyrinth(lab, start, ende, labID, rastermass);
	}
		return labyrinth;
	}

}
