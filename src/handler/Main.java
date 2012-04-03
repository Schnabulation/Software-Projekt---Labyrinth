package handler;

import labyrinth.Labyrinth;
import algorithmus.AlgInterface;
import algorithmus.Backtracking;

public class Main {

	private static Labyrinth labyrinth;
	private static AlgInterface alg;
	private static char[][] lab={
			  {'0','0','0','0','0','0','0','0','0','0','0','0'},
			  {'0','0','0','1','0','0','1','1','0','0','0','0'},
			  {'0','0','0','1','1','0','1','0','0','1','1','1'},
			  {'1','1','1','0','1','0','1','1','1','1','0','0'},
			  {'0','0','1','0','1','0','1','0','0','0','0','0'},
			  {'0','0','1','1','1','0','1','0','0','0','0','0'},
			  {'0','0','0','0','1','0','1','1','1','0','1','0'},
			  {'0','0','0','1','1','0','1','0','1','0','1','0'},
			  {'0','1','1','1','0','0','0','0','1','1','1','0'},
			  {'0','1','0','1','1','1','1','0','1','0','1','0'},
			  {'0','1','1','0','0','0','1','1','1','0','0','0'},
			  {'0','0','0','0','0','0','0','0','0','0','0','0'}};
	private static Backtracking backtracking;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		labyrinth = new Labyrinth(lab);
		alg = new Backtracking();
		Main main = new Main();
		main.solveLab(alg, labyrinth);
	}
	
	public void solveLab(AlgInterface algorithm, Labyrinth lab) {
		System.out.println("Original Labyrinth");
		labyrinth.zeichnen();
	}

}
