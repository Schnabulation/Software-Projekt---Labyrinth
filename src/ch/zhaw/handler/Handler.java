package ch.zhaw.handler;

import ch.zhaw.algorithmus.Algorithmus;
import ch.zhaw.gui.Gui;
import ch.zhaw.gui.Settings;
import ch.zhaw.labyrinth.Labyrinth;

/* ---------------------------------------------------------------------------------------------
 * Handler: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Die Handler-Klasse �bernimmt den kompletten Ablauf des Programms. Sie ruft auch die
 * grafische Oberfl�che auf und besch�ftigt sich mit dem kontrollierten Aufruf der
 * entsprechenden Methoden und Labyrinthe.
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
	 * Wird durch die Main-Methode aufgerufen.
	 * Die Methode erstellt die grafischen Oberfl�chen und ruft diese auf.
	 * --------------------------------------------------------------------------------------------- */
	public Handler() {
		settings = new Settings(this);
		gui = new Gui(this);
		gui.setEnable(false);
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: resetHandler
	 *  
	 * Author: Steve Heller
	 * 
	 * Setzt die ganze Applikation auf zur�ck. Wird aufgerufen, wenn auf den "L�schen" Knopf
	 * geklickt wird.
	 * --------------------------------------------------------------------------------------------- */
	public void resetHandler() {
		labyrinth = null;
		alg1 = null;
		alg2 = null;
		settings = new Settings(this);
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getLabyrinth
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode f�r die Variable labyrinth.
	 * --------------------------------------------------------------------------------------------- */
	public Labyrinth getLabyrinth() {
		return labyrinth;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: setLabyrinth
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode f�r die Variable labyrinth. Ausserdem wird dadurch das Labyrinth auf
	 * dem Hauptfenster gezeichnet.
	 * --------------------------------------------------------------------------------------------- */
	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
		gui.labyrinth1Zeichnen(labyrinth);
		gui.labyrinth2Zeichnen(labyrinth);
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getAlg1
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode f�r Variable alg1.
	 * --------------------------------------------------------------------------------------------- */
	public Algorithmus getAlg1() {
		return alg1;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: setAlg1
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode f�r Variable alg1.
	 * --------------------------------------------------------------------------------------------- */
	public void setAlg1(Algorithmus alg1) {
		this.alg1 = alg1;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getAlg2
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode f�r Variable alg2.
	 * --------------------------------------------------------------------------------------------- */
	public Algorithmus getAlg2() {
		return alg2;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: setAlg2
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode f�r Variable alg2.
	 * --------------------------------------------------------------------------------------------- */
	public void setAlg2(Algorithmus alg2) {
		this.alg2 = alg2;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getGui
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode f�r Variable gui.
	 * --------------------------------------------------------------------------------------------- */
	public Gui getGui() {
		return gui;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: setGui
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode f�r Variable gui.
	 * --------------------------------------------------------------------------------------------- */
	public void setGui(Gui gui) {
		this.gui = gui;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: openSettings
	 *  
	 * Author: Steve Heller
	 * 
	 * Hilfsmethode. Ruft die Methode changeVisibility auf dem Objekt settings auf.
	 * --------------------------------------------------------------------------------------------- */
	public void openSettings() {
		settings.changeVisibility(true);
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: resetGuiStatistics
	 *  
	 * Author: Steve Heller
	 * 
	 * Hilfsmethode. Ruft die Methode resetStatistics auf dem Objekt gui auf.
	 * --------------------------------------------------------------------------------------------- */
	public void resetGuiStatistics(boolean b) {
		gui.resetStatistics(b);
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: setGuiAlgNames
	 *  
	 * Author: Steve Heller
	 * 
	 * Hilfsmethode. Ruft die Methode updateAlgName auf dem Objekt gui auf.
	 * --------------------------------------------------------------------------------------------- */
	public void setGuiAlgNames() {
		gui.updateAlgName(this.getAlg1().toString(), this.getAlg2().toString());
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: start
	 *  
	 * Author: Steve Heller
	 * 
	 * die start Methode wird aufgerufen, um das Labyrinth von den Algorithmen sofort zu l�sen, also
	 * nicht Schritt f�r Schritt.
	 * Daf�r wird von beiden Algorithmen die solveLab Methode aufgerufen und die Statistics
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
	 * die step Methode wird aufgerufen, um das Labyrinth von den Algorithmen Schritt f�r Schritt
	 * zu l�sen, also nicht sofort.
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
	 * die speed Methode wird aufgerufen, um das Labyrinth von den Algorithmen Schritt f�r Schritt
	 * mit einer bestimmten Geschwindigkeit zu l�sen, also nicht sofort und nicht mit nextStep Klick.
	 * Beim ersten Aufruf wird zuerst noch die startStepByStep Methode aufgerufen, danach Zeitvers�tzt
	 * die nextStep Methode anhand der Wartezeit die im GUI definiert ist. Daf�r wird ein dritter
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
	 * Diese Klasse ist f�r den Geschwindigkeits Modus. Sie dient lediglich dazu, den 
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