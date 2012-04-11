package algorithmus;

import labyrinth.Labyrinth;

public abstract class Algorithmus {
	
	private int solveTime;
	private int stepCounter;

	public abstract Labyrinth solveLab(Labyrinth originalLab);

	public int getSolveTime() {
		return solveTime;
	}

	public void setSolveTime(int solveTime) {
		this.solveTime = solveTime;
	}

	public int getStepCounter() {
		return stepCounter;
	}

	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}
	
}
