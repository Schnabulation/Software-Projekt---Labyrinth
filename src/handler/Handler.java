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
	private int speed;
	private Runnable r3;
	private Thread t3;
	
	public Handler() {
		settings = new Settings(this);
		gui = new Gui(this);
		gui.setEnable(false);
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
		gui.updateStatistics(alg1.getStepCounter(), alg2.getStepCounter(), alg1.getStepFinalWay(), alg2.getStepFinalWay());
	}
	public void step(){
		if(alg1.getStepCounter() == 0 && alg2.getStepCounter()==0){
		
			alg1.startStepByStep(labyrinth.clone());
			alg2.startStepByStep(labyrinth.clone());
		}
		if(!alg1.isEnde() || !alg2.isEnde()){
			gui.labyrinth1Zeichnen(alg1.nextStep());
			gui.labyrinth2Zeichnen(alg2.nextStep());
			gui.updateStatistics(alg1.getStepCounter(), alg2.getStepCounter(), alg1.getStepFinalWay(), alg2.getStepFinalWay());
		}
	}
	public void speed(){
		r3 = new MyRunnableThree();
		t3 = new Thread(r3);
		
		if(alg1.getStepCounter() == 0 && alg2.getStepCounter()==0){
		
			alg1.startStepByStep(labyrinth.clone());
			alg2.startStepByStep(labyrinth.clone());
			t3.start();
		}
		}
	
	public void guiSetEnable(boolean b) {
		gui.setEnable(b);
	}
	
public class MyRunnableThree implements Runnable {

	@Override
	public void run() {
		synchronized (this) {
			while(!alg1.isEnde() || !alg2.isEnde()){
				speed = gui.getSpeed();
				try {
					Thread.sleep(speed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
				gui.labyrinth1Zeichnen(alg1.nextStep());
				gui.labyrinth2Zeichnen(alg2.nextStep());
				gui.updateStatistics(alg1.getStepCounter(), alg2.getStepCounter(), alg1.getStepFinalWay(), alg2.getStepFinalWay());
				}
		}
	}

}
}