package handler;

import labyrinth.Labyrinth;
import algorithmus.Algorithmus;

public class Handler {
	
	private Labyrinth labyrinth;
	private Algorithmus alg;
	
	public Handler(Algorithmus alg, Labyrinth labyrinth) {
		this.alg = alg;
		this.labyrinth = labyrinth;
	}

	public void solveLab() {
		this.zeichnen();
	}
	
	public void markieren(int x, int y){
		// currentLabyrinth[y][x]='m';
	}
	public void demarkieren(int x, int y){
		// currentLabyrinth[y][x]='x';
	}
	
	public void zeichnen(){
		 for (int i = 0; i < labyrinth.getLaenge(); i++) {
				for (int j = 0; j < labyrinth.getBreite(); j++) {
					System.out.print(labyrinth.getPosition(i,j)+ " ");
				}
				System.out.println();
		 
			}
		 System.out.println("-------------------------------------");
	}

}
