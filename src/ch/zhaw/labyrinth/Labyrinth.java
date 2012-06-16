package ch.zhaw.labyrinth;

/* ---------------------------------------------------------------------------------------------
 * Labyrinth: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Auf diese Klasse bezieht sich die ganze Simulation. Damit wir nicht jedesmal mit einem
 * zweidimensionalen Array arbeiten müssen, zieht diese Klasse alle Informationen zusammen
 * und stellt sie einfach zur Verfügung.
 * --------------------------------------------------------------------------------------------- */

public class Labyrinth implements Cloneable {

	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	private char[][] labRaw;
	private int laenge;
	private int breite;
	private int[] start;
	private int[] ende;
	private int labID;
	private int rastermass;
	
	/* ---------------------------------------------
	 * Methoden
	 * --------------------------------------------- */	
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: Labyrinth (Konstruktor)
	 *  
	 * Author: Steve Heller
	 * 
	 * Der Konstruktor baut aus den Rohdaten wie 2-dim Arry, Eingang und Ausgang sowie Rastermass
	 * das Labyrinth Objekt.
	 * --------------------------------------------------------------------------------------------- */
	
	public Labyrinth(char[][] lab, int[] start, int[] ende, int labID, int rastermass) {
		labRaw = this.invertLab(lab); // dreht das Labyrinth in der Dimension [x][y] --> [y][x]
		setLaenge(labRaw.length);
		setBreite(labRaw[1].length);
		this.setStart(start);
		this.setEnde(ende);
		this.labID = labID;
		this.rastermass = rastermass;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: invertLab
	 *  
	 * Author: Steve Heller
	 * 
	 * Diese Hilfsmethode invertiert das zweidimensionale Array. So ist es für den Programmierer
	 * einfacher, ein Labyrinth zu erstellen (es sieht bereits richtig aus).
	 * --------------------------------------------------------------------------------------------- */
	private char[][] invertLab(char[][] lab) {
		char[][] newLab = new char[lab[1].length][lab.length];
		for (int i = 0; i < lab.length; i++) {
				for (int j = 0; j < lab[1].length; j++) {
					newLab[i][j] = lab[j][i];
				}
			}
		 return newLab;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: zeichnen
	 *  
	 * Author: Steve Heller
	 * 
	 * Diese Methode wird nicht benötigt. Sie diente in Anfangszeiten der Zeichnung eines
	 * Labyrinthes auf der Konsole.
	 * --------------------------------------------------------------------------------------------- */
	public void zeichnen() {
		 for (int i = 0; i < this.getLaenge(); i++) {
				for (int j = 0; j < this.getBreite(); j++) {
					System.out.print(this.getChar(j,i)+ " ");
				}
				System.out.println();	 
			}
		 System.out.println("-------------------------------------");
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: setStart
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode für die Variable start.
	 * --------------------------------------------------------------------------------------------- */
	public void setStart(int[] start) {
		this.start = start;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getStart
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode für die Variable start.
	 * --------------------------------------------------------------------------------------------- */
	public int[] getStart() {
		return start;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: setEnde
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode für die Variable ende.
	 * --------------------------------------------------------------------------------------------- */
	public void setEnde(int[] ende) {
		this.ende = ende;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getEnde
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode für die Variable ende.
	 * --------------------------------------------------------------------------------------------- */
	public int[] getEnde() {
		return ende;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: setLaenge
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode für die Variable laenge.
	 * --------------------------------------------------------------------------------------------- */
	public void setLaenge(int laenge) {
		this.laenge = laenge;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getLaenge
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode für die Variable laenge.
	 * --------------------------------------------------------------------------------------------- */
	public int getLaenge() {
		return laenge;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: setBreite
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode für die Variable breite.
	 * --------------------------------------------------------------------------------------------- */
	public void setBreite(int breite) {
		this.breite = breite;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getBreite
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode für die Variable breite.
	 * --------------------------------------------------------------------------------------------- */
	public int getBreite() {
		return breite;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getChar
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode für die Variable labRaw. Erwartet Koordinaten als Input.
	 * --------------------------------------------------------------------------------------------- */
	public char getChar(int x, int y) {
		return this.labRaw[x][y];
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: setChar
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode für die Variable labRaw. Erwartet ausserdem Koordinaten als Input.
	 * --------------------------------------------------------------------------------------------- */
	public void setChar(int x, int y, char newChar) {
		labRaw[x][y] = newChar;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: getID
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode für die Variable labID.
	 * --------------------------------------------------------------------------------------------- */
	public int getID() {
		return this.labID;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: clone
	 *  
	 * Author: Steve Heller
	 * 
	 * Zu überschreibende Methode des Interface "cloneable". Liefert ein neues Objekt vom Typ
	 * Labyrinth zurück, welches genau gleich ist wie das aktuelle Objekt.
	 * --------------------------------------------------------------------------------------------- */
	public Labyrinth clone() {
		char[][] newLab = new char[labRaw[1].length][labRaw.length];
		for (int i = 0; i < labRaw.length; i++) {
				for (int j = 0; j < labRaw[1].length; j++) {
					newLab[i][j] = labRaw[j][i];
				}
			}
		Labyrinth tempLab = new Labyrinth(newLab, this.getStart(), this.getEnde(), this.getID(), this.getRastermass());
		return tempLab;
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: toString
	 *  
	 * Author: Steve Heller
	 * 
	 * Überschreibt die Methode toString mit dem Text "Labyrinth" sowie der ID des Labyrinths.
	 * --------------------------------------------------------------------------------------------- */
	public String toString() {
		return "Labyrinth " + labID;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: setRastermass
	 *  
	 * Author: Steve Heller
	 * 
	 * Setter-Methode für die Variable rastermass.
	 * --------------------------------------------------------------------------------------------- */
	public void setRastermass(int rastermass) {
		this.rastermass = rastermass;
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getRastermass
	 *  
	 * Author: Steve Heller
	 * 
	 * Getter-Methode für die Variable rastermass.
	 * --------------------------------------------------------------------------------------------- */
	public int getRastermass() {
		return rastermass;
	}
	
}
