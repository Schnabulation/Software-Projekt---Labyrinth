package labyrinth;

public class Labyrinth {

	private char[][] labRaw;
	private int laenge;
	private int breite;
	private int[] start;
	private int[] ende;
	
	
	public Labyrinth(char[][] lab, int[] start, int[] ende) {
		labRaw = lab;
		setLaenge(labRaw.length);
		setBreite(labRaw[1].length);
		this.setStart(start);
		this.setEnde(ende);
	}

	public void setStart(int[] start) {
		this.start = start;
	}

	public int[] getStart() {
		return start;
	}

	public void setEnde(int[] ende) {
		this.ende = ende;
	}

	public int[] getEnde() {
		return ende;
	}

	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	public int getLaenge() {
		return laenge;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getBreite() {
		return breite;
	}

	public char getPosition(int x, int y) {
		return this.labRaw[x][y];
	}
	

	
}
