package handler;

import labyrinth.Labyrinth;
import algorithmus.Algorithmus;

public class Handler {
	
	private Labyrinth labyrinth;
	private Algorithmus alg;
	private int[] currentPosition;
	private String direction;
	
	public Handler(Algorithmus alg, Labyrinth labyrinth) {
		direction = "rechts";
		currentPosition = new int[2];
		this.alg = alg;
		this.labyrinth = labyrinth;
	}

	public void solveLab() {
		currentPosition = labyrinth.getStart();
		while(currentPosition != labyrinth.getEnde()) {
			if (alg.nextStep(this.showNextMove()).equals("gerade")) {
				this.makeNextMove();
			} else if (alg.nextStep(this.showNextMove()).equals("rechts")) {
				this.changeDirection("rechts");
			} else if (alg.nextStep(this.showNextMove()).equals("links")) {
				this.changeDirection("links");
				this.changeDirection("links");
			}
		}
	}
	
	private void changeDirection(String s) {
		if (direction.equals("rechts") && s.equals("rechts")) {
			this.direction = "unten";
		} else if (direction.equals("rechts") && s.equals("links")) {
			this.direction = "oben";
		} else if (direction.equals("unten") && s.equals("rechts")) {
			this.direction = "links";
		} else if (direction.equals("unten") && s.equals("links")) {
			this.direction = "rechts";
		} else if (direction.equals("links") && s.equals("rechts")) {
			this.direction = "oben";
		} else if (direction.equals("links") && s.equals("links")) {
			this.direction = "unten";
		} else if (direction.equals("oben") && s.equals("rechts")) {
			this.direction = "rechts";
		} else if (direction.equals("oben") && s.equals("links")) {
			this.direction = "links";
		}
	}

	public void makeNextMove() {
		if (direction.equals("rechts")) {
			currentPosition[0] = currentPosition[0] + 1;
		} else if (direction.equals("unten")) {
			currentPosition[1] = currentPosition[1] + 1;
		} else if (direction.equals("links")) {
			currentPosition[0] = currentPosition[0] - 1;
		} else if (direction.equals("oben")) {
			currentPosition[1] = currentPosition[1] - 1;
		} else {
			System.exit(0);
		}
	}
	
	public char showNextMove() {
		if (direction.equals("rechts")) {
			return labyrinth.getPosition(currentPosition[0] + 1, currentPosition[1]);
		} else if (direction.equals("unten")) {
			return labyrinth.getPosition(currentPosition[0], currentPosition[1] + 1);
		} else if (direction.equals("links")) {
			return labyrinth.getPosition(currentPosition[0] - 1, currentPosition[1]);
		} else {
			return labyrinth.getPosition(currentPosition[0], currentPosition[1] - 1);
		}
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
