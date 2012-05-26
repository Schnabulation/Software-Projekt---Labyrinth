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
		gui.labyrinth2Zeichnen(labyrinth);
	}

	public void start() {
		Labyrinth solvedLab = alg.solveLab(labyrinth.clone());
		gui.labyrinth1Zeichnen(solvedLab);
		alg.writeStatistics();
	}
}
