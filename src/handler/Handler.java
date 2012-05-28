package handler;

import java.util.Scanner;

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

		alg1.startStepByStep(labyrinth.clone());
		alg2.startStepByStep(labyrinth.clone());
		Scanner scanner = new Scanner(System.in);
		while(!alg1.isEnde() || !alg2.isEnde()){
			scanner.nextLine();
			gui.labyrinth1Zeichnen(alg1.nextStep());
			gui.labyrinth2Zeichnen(alg2.nextStep());
		}

//		gui.labyrinth1Zeichnen(alg1.nextStep());
		//gui.labyrinth1Zeichnen(alg1.solveLab(labyrinth.clone()));
		//gui.labyrinth2Zeichnen(alg2.solveLab(labyrinth.clone()));
		alg1.writeStatistics();
		alg2.writeStatistics();
	}
}
