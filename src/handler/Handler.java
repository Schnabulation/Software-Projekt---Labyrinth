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
	
//	public Handler(Algorithmus alg1, Algorithmus alg2, Labyrinth labyrinth, Gui gui) {
//		this.alg1 = alg1;
//		this.alg2 = alg2;
//		this.labyrinth = labyrinth;
//		this.gui = gui;
//	}
	

	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}

	public Algorithmus getAlg1() {
		return alg1;
	}

	public void setAlg1(Algorithmus alg1) {
		this.alg1 = alg1;
	}

	public Algorithmus getAlg2() {
		return alg2;
	}

	public void setAlg2(Algorithmus alg2) {
		this.alg2 = alg2;
	}

	public Gui getGui() {
		return gui;
	}

	public void setGui(Gui gui) {
		this.gui = gui;
	}

	public void start() {
//		gui.labyrinth1Zeichnen(alg1.nextStep());
		gui.labyrinth1Zeichnen(alg1.solveLab(labyrinth.clone()));
		gui.labyrinth2Zeichnen(alg2.solveLab(labyrinth.clone()));
//		alg1.writeStatistics();
//		alg2.writeStatistics();
	}
	public void step(){
		if(alg1.getStepCounter() == 0 && alg2.getStepCounter()==0){
		
			alg1.startStepByStep(labyrinth.clone());
			alg2.startStepByStep(labyrinth.clone());
		}
		System.out.println("im STep" + alg1.isEnde());
		if(!alg1.isEnde() || !alg2.isEnde()){
			gui.labyrinth1Zeichnen(alg1.nextStep());
			gui.labyrinth2Zeichnen(alg2.nextStep());
		}
		
//		alg1.startStepByStep(labyrinth.clone());
//		alg2.startStepByStep(labyrinth.clone());
//		System.out.println("im STep" + alg2.isEnde());
//		Scanner scanner = new Scanner(System.in);
//		while(!alg1.isEnde() || !alg2.isEnde()){
//
//			System.out.println("im");
//			scanner.nextLine();
//			gui.labyrinth1Zeichnen(alg1.nextStep());
//			gui.labyrinth2Zeichnen(alg2.nextStep());
//			alg1.nextStep().zeichnen();
//		}
	}
}
