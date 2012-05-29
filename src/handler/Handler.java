package handler;

import gui.Gui;
import gui.Settings;
import labyrinth.Labyrinth;
import algorithmus.Algorithmus;

public class Handler {
	
	private Labyrinth labyrinth;
	private Algorithmus alg1;
	private Algorithmus alg2;
	private Gui gui;
	private Settings settings;
	
	public Handler() {
		settings = new Settings(this);
		gui = new Gui(this);
	}
	
	public void resetHandler() {
		labyrinth = null;
		alg1 = null;
		alg2 = null;
		settings = new Settings(this);
	}

	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		gui.labyrinth1Zeichnen(labyrinth);
		gui.labyrinth2Zeichnen(labyrinth);
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
	
	public void openSettings() {
		settings.changeVisibility(true);
	}
	
	public void resetGuiStatistics() {
		gui.resetStatistics();
	}

	public void start() {
		gui.labyrinth1Zeichnen(alg1.solveLab(labyrinth.clone()));
		gui.labyrinth2Zeichnen(alg2.solveLab(labyrinth.clone()));
		gui.updateStatistics(alg1.getStepCounter(), alg2.getStepCounter(), alg1.getSolveTime(), alg2.getSolveTime());
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
	}
}
