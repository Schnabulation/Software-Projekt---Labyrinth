package algorithmus;

import labyrinth.Labyrinth;

/* ---------------------------------------------------------------------------------------------
 * Algorithmus: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Die Klasse Algorithmus ist die Superklasse aller unserer Algorithmen und wird dann dem Handler 
 * zur Verfügung gestellt.
 * Sie beinhaltet darum alle Variablen und Methoden die die Handler Klasse benutzen muss und somit
 * bei allen Algorithmen vorhanden sein müssen.
 * Sie implementiert das Interface StepByStep für den Schritt für Schritt Modus, Methoden für die 
 * Statistik und um den Algorithmus über ein Labyrinth auszuführen.
 * 
 * --------------------------------------------------------------------------------------------- */


public abstract class Algorithmus implements StepByStep{
	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	private int stepCounter;
	private long startTime;
	private long endTime;
	private boolean ende = false;
	private int stepFinalWay;

	/* ---------------------------------------------
	 * Methoden
	 * --------------------------------------------- */
	
	public int getStepFinalWay() {
		return stepFinalWay;
	}

	public void setStepFinalWay(int stepFinalWay) {
		this.stepFinalWay = stepFinalWay;
	}

	public boolean isEnde() {
		return ende;
	}

	public void setEnde(boolean ende) {
		this.ende = ende;
	}

	public abstract Labyrinth solveLab(Labyrinth originalLab);

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getSolveTime() {
		return endTime-startTime;
	}

	public int getStepCounter() {
		return stepCounter;
	}

	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}
	public void increaseStepCounter(){
		this.stepCounter = this.stepCounter + 1;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: writeStatistics 
	 *  
	 * Author: Reto Huber
	 * 
	 * die writeStatistics Methode schreibt die aktuelle Anzahl Schritte und gebrauchte Zeit in die
	 * Konsole. Dies wurde vor allem zu Beginn gebraucht, als das GUI noch nicht vorhandne war.
	 * --------------------------------------------------------------------------------------------- */
 	
	public void writeStatistics(){
		System.out.println("------ STATISTIK ------");
		System.out.println("benötigte Anzal Schritte: " + getStepCounter());
		System.out.println("benötigte Zeit: " + getSolveTime() + " Millisekunden");
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: toString
	 *  
	 * Author: Steve Heller
	 * 
	 * Die toString Methode wurde überschrieben, so dass sie der Name des Algorithmus zurück gibt.
	 * Dies wird unter anderem für die Erstellung der Statistiken im GUI benötigt.
	 * 
	 * --------------------------------------------------------------------------------------------- */
 	
	
	public String toString() {
		String classname = this.getClass().getName();
		int mid = classname.lastIndexOf ('.') + 1;
		return classname.substring(mid);
	}	
}
