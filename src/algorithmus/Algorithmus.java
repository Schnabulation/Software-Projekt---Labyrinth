package algorithmus;

import labyrinth.Labyrinth;

public abstract class Algorithmus implements StepByStep{
	
	private int stepCounter;
	private long startTime;
	private long endTime;
	private boolean ende = false;

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
	public void writeStatistics(){
		System.out.println("------ STATISTIK ------");
		System.out.println("benötigte Anzal Schritte: " + getStepCounter());
		System.out.println("benötigte Zeit: " + getSolveTime() + " Millisekunden");
	}
	public String toString() {
		String classname = this.getClass().getName();
		int mid = classname.lastIndexOf ('.') + 1;
		return classname.substring(mid);
	}
	
}
