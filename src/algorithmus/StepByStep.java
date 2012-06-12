package algorithmus;

import labyrinth.Labyrinth;

/* ---------------------------------------------------------------------------------------------
 * StepByStep: Beschreibung des Interfaces
 * ---------------------------------------------
 * 
 * Dieses Interface kann bei alen Algorithmen implementiert werden, die den Schritt f�r Schritt
 * Modus zur Verf�gung stellen wollen, bzw. k�nnen.
 * Daf�r m�ssen dann zwei Methoden implementiert werden, startStepByStep um alles zu initialisieren
 * und die Methode nextStep mit welcher dann immer der n�chste Schritt abgefragt werden kann.
 * --------------------------------------------------------------------------------------------- */


public interface StepByStep {
	
	public void startStepByStep(Labyrinth lab);
	public Labyrinth nextStep();
	

}