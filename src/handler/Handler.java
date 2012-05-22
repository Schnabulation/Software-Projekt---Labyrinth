package handler;

import gui.Gui;
import labyrinth.Labyrinth;
import algorithmus.Algorithmus;

public class Handler {
	
	private Labyrinth labyrinth;
	private Algorithmus alg;
	private Gui gui;
	
	public Handler(Algorithmus alg, Labyrinth labyrinth) {
		this.alg = alg;
		this.labyrinth = labyrinth;
		gui = new Gui();
		gui.labyrinth1Zeichnen(labyrinth);
	}

	public void start() {
//		Labyrinth tempLab = labyrinth.
		Labyrinth solvedLab = alg.solveLab(labyrinth.clone());
		gui.labyrinth2Zeichnen(solvedLab);
		alg.writeStatistics();
	}
}
