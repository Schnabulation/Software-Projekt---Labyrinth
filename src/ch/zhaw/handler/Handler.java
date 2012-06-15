package ch.zhaw.handler;

import ch.zhaw.algorithmus.Algorithmus;
import ch.zhaw.gui.Gui;
import ch.zhaw.gui.Settings;
import ch.zhaw.labyrinth.Labyrinth;

/* ---------------------------------------------------------------------------------------------
 * Handler: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * TEXT!!!!!!!!!!!!!!!!!!!!!
 * --------------------------------------------------------------------------------------------- */

public class Handler {
	
	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	private Labyrinth labyrinth;
	private Algorithmus alg1;
	private Algorithmus alg2;
	private Gui gui;
	private Settings settings;
	private int speed;
	private Runnable r3;
	private Thread t3;

	/* ---------------------------------------------
	 * Methoden
	 * --------------------------------------------- */
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: Handler (Konstruktor)
	 *  
	 * Author: Steve Heller
	 * 
	 * TEXT!!!!!
	 * --------------------------------------------------------------------------------------------- */
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
	
	public void resetGuiStatistics(boolean b) {
		gui.resetStatistics(b);
	}
	
	public void setGuiAlgNames() {
		gui.updateAlgName(this.getAlg1().toString(), this.getAlg2().toString());
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: start
	 *  
	 * Author: Steve Heller
	 * 
	 * die start Methode wird aufgerufen, um das Labyrinth von den Algorithmen sofort zu lösen, also
	 * nicht Schritt für Schritt.
	 * Dafür wird von beiden Algorithmen die solveLab Methode aufgerufen und die Statistics
	 * upgedatet.
	 * --------------------------------------------------------------------------------------------- */
	public void start() {
		gui.labyrinth1Zeichnen(alg1.solveLab(labyrinth.clone()));
		gui.labyrinth2Zeichnen(alg2.solveLab(labyrinth.clone()));
		gui.updateStatistics(alg1.getStepCounter(), alg2.getStepCounter(), alg1.getStepFinalWay(), alg2.getStepFinalWay());
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: step
	 *  
	 * Author: Reto Huber
	 * 
	 * die step Methode wird aufgerufen, um das Labyrinth von den Algorithmen Schritt für Schritt
	 * zu lösen, also nicht sofort.
	 * Beim ersten Aufruf wird zuerst noch die startStepByStep Methode aufgerufen, danch nur noch die
	 * nextStep Methode, um einen Schritt weiter zu gehen.
	 * Anschliessend werden die Statistics upgedatet.
	 * 
	 * --------------------------------------------------------------------------------------------- */
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
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: speed
	 *  
	 * Author: Reto Huber
	 * 
	 * die speed Methode wird aufgerufen, um das Labyrinth von den Algorithmen Schritt für Schritt
	 * mit einer bestimmten Geschwindigkeit zu lösen, also nicht sofort und nicht mit nextStep Klick.
	 * Beim ersten Aufruf wird zuerst noch die startStepByStep Methode aufgerufen, danach Zeitversätzt
	 * die nextStep Methode anhand der Wartezeit die im GUI definiert ist. Dafür wird ein dritter
	 * Thread erstellt.
	 * Anschliessend werden die Statistics upgedatet.
	 * 
	 * --------------------------------------------------------------------------------------------- */
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

	/* ---------------------------------------------------------------------------------------------
	 * MyRunnableThree: Beschreibung der Klasse
	 * ---------------------------------------------
	 * 
	 * Diese Klasse ist für den Geschwindigkeits Modus. Sie dient lediglich dazu, den 
	 * Thread1 einen Schritt weiter zu bringen, indem sie ihn durch notify aus dem Wartezustand
	 * bringt.
	 * 
	 * --------------------------------------------------------------------------------------------- */

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