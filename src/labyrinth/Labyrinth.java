package labyrinth;

public class Labyrinth implements Cloneable {

	private char[][] labRaw;
	private int laenge;
	private int breite;
	private int[] start;
	private int[] ende;
	
	
	public Labyrinth(char[][] lab, int[] start, int[] ende) {
		labRaw = this.invertLab(lab); // dreht das Labyrinth in der Dimension [x][y] --> [y][x]
		setLaenge(labRaw.length);
		setBreite(labRaw[1].length);
		this.setStart(start);
		this.setEnde(ende);
	}

	private char[][] invertLab(char[][] lab) {
		char[][] newLab = new char[lab[1].length][lab.length];
		for (int i = 0; i < lab.length; i++) {
				for (int j = 0; j < lab[1].length; j++) {
					newLab[i][j] = lab[j][i];
				}
			}
		 return newLab;
	}
	
	public void zeichnen() {
		 for (int i = 0; i < this.getLaenge(); i++) {
				for (int j = 0; j < this.getBreite(); j++) {
					System.out.print(this.getChar(j,i)+ " ");
				}
				System.out.println();	 
			}
		 System.out.println("-------------------------------------");
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

	public char getChar(int x, int y) {
		return this.labRaw[x][y];
	}
	
	public void setChar(int x, int y, char newChar) {
		labRaw[x][y] = newChar;
	}
	
	public Labyrinth clone() {
		char[][] newLab = new char[labRaw[1].length][labRaw.length];
		for (int i = 0; i < labRaw.length; i++) {
				for (int j = 0; j < labRaw[1].length; j++) {
					newLab[i][j] = labRaw[j][i];
				}
			}
		Labyrinth tempLab = new Labyrinth(newLab, this.getStart(), this.getEnde());
		return tempLab;
	}
	
}
