package Labyrinth;

import Algorithmus.Backtracking;

public class Main {

	private static Labyrinth labyrinth;
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
		// TODO Auto-generated method stub
		labyrinth=new Labyrinth(lab);
		labyrinth.zeichnen();
		backtracking = new Backtracking(labyrinth);
		
	}

}
