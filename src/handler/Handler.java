package handler;

import gui.Gui;
import labyrinth.Labyrinth;
import algorithmus.Algorithmus;

public class Handler {
	
	private Labyrinth labyrinth;
	private Algorithmus alg1;
	private Algorithmus alg2;
	private Gui gui;
	
	public Handler(Algorithmus alg1, Algorithmus alg2, Labyrinth labyrinth) {
		this.alg1 = alg1;
		this.alg2 = alg2;
		this.labyrinth = labyrinth;
		gui = new Gui();
	}

	public void start() {
		gui.labyrinth1Zeichnen(alg1.solveLab(labyrinth.clone()));
		gui.labyrinth2Zeichnen(alg2.solveLab(labyrinth.clone()));
//		alg.writeStatistics();
	}
}
