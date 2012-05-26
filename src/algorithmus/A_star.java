package algorithmus;

import labyrinth.Labyrinth;

public class A_star extends Algorithmus {

	@Override
	public Labyrinth solveLab(Labyrinth originalLab) {
		setStartTime(System.currentTimeMillis());
		markieren(originalLab.getStart()[0], originalLab.getStart()[1],originalLab);
		
		
		//din CODE! Falls au rekursiv, bruchts evtl au e neui Methode, siehe Backtracking.
		
		setEndTime(System.currentTimeMillis());
		return originalLab;
	}
	
	public void markieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		originalLab.setChar(x, y, 'm');
		originalLab.zeichnen();
		// currentLabyrinth[y][x]='m';
	}
	public void demarkieren(int x, int y, Labyrinth originalLab){
		increaseStepCounter();
		originalLab.setChar(x, y, 'x');
		originalLab.zeichnen();
		// currentLabyrinth[y][x]='x';
	}

}
