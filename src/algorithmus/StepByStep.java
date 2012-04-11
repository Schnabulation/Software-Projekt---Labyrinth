package algorithmus;

import labyrinth.Labyrinth;

public interface StepByStep {
	public void startStepByStep(Labyrinth lab);
	public Labyrinth nextStep();
}
