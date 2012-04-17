package handler;

import labyrinth.Labyrinth;
import algorithmus.Algorithmus;

public class Handler {
	
	private Labyrinth labyrinth;
	private Algorithmus alg;
	
	public Handler(Algorithmus alg, Labyrinth labyrinth) {
		this.alg = alg;
		this.labyrinth = labyrinth;
	}

	public void start() {
		Labyrinth solvedLab = alg.solveLab(labyrinth);
		solvedLab.zeichnen();
		alg.writeStatistics();
	}
}
