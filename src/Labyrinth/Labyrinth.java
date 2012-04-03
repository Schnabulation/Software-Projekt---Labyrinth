package Labyrinth;

public class Labyrinth {

	private char[][] originalLabyrinth;
	private char[][] currentLabyrinth;
	private int laenge;
	private int breite;
	private int startx;
	private int starty;
	private int endex;
	private int endey;
	
	
	public Labyrinth(char[][] lab){
		originalLabyrinth = lab;
		currentLabyrinth=originalLabyrinth;
		laenge = currentLabyrinth.length;
		breite = currentLabyrinth[1].length;
		findeStart();
		findeEnde();
	}
	
	public void markieren(int x, int y){
		currentLabyrinth[y][x]='m';
	}
	public void demarkieren(int x, int y){
		currentLabyrinth[y][x]='x';
	}
	
	public void zeichnen(){
		 for (int i = 0; i < laenge; i++) {
				for (int j = 0; j < breite; j++) {
					System.out.print(currentLabyrinth[i][j]+ " ");
				}
				System.out.println();
		 
			}
		 System.out.println("-------------------------------------");
	}
	
	private void findeStart(){
		startx=0;
		starty=3;
	}
	private void findeEnde(){
		endex=11;
		endey=2;
	}
	
}
