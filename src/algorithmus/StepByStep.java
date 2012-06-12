package algorithmus;

import labyrinth.Labyrinth;

/* ---------------------------------------------------------------------------------------------
 * StepByStep: Beschreibung des Interfaces
 * ---------------------------------------------
 * 
 * Dieses Interface kann bei alen Algorithmen implementiert werden, die den Schritt für Schritt
 * Modus zur Verfügung stellen wollen, bzw. können.
 * Dafür müssen dann zwei Methoden implementiert werden, startStepByStep um alles zu initialisieren
 * und die Methode nextStep mit welcher dann immer der nächste Schritt abgefragt werden kann.
 * --------------------------------------------------------------------------------------------- */


public interface StepByStep {
	
	public void startStepByStep(Labyrinth lab);
	public Labyrinth nextStep();
	

}