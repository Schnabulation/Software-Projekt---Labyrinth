package algorithmus;

import labyrinth.Labyrinth;

public class Backtracking extends Algorithmus {

	@Override
	public Labyrinth solveLab(Labyrinth originalLab) {
		return originalLab;
	}
	
	public void markieren(int x, int y){
		// currentLabyrinth[y][x]='m';
	}
	public void demarkieren(int x, int y){
		// currentLabyrinth[y][x]='x';
	}

}
