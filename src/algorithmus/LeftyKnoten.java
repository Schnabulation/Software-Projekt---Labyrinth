package algorithmus;

/* ---------------------------------------------------------------------------------------------
 * AStarKnoten: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Diese Klasse wird vom AStar Algorithmus genutzt. Sie dient dazu einen Punkt, bzw. Knoten zu
 * speichern. Damit wird gewährleistet dass jeder abgespeicherte Punkt die nötigen Variablen und
 * Methoden hat.
 * 
 * --------------------------------------------------------------------------------------------- */


public class LeftyKnoten {

	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	private int richtung;
	private int x;
	private int y;

	/* ---------------------------------------------
	 * Methoden
	 * --------------------------------------------- */	
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: AStarKnoten (Konstruktor)
	 *  
	 * Author: Reto Huber
	 * 
	 * dem Konstruktor wird der parent Knoten übregeben, die Koordinaten, den Abstand h zum Ziel und
	 * ein boolean g1, welches true ist, wenn der bisherige Weg mitbeachtet werden soll (originaler A*).
	 * Je nachdem wie dieses Flag gesetzt ist, wird f unterschiedlich berechnet.
	 * 
	 * --------------------------------------------------------------------------------------------- */	
	public LeftyKnoten(int x, int y, int richtung){
		this.x = x;
		this.y = y;
		this.richtung = richtung;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRichtung() {
		return richtung;
	}

	public void setRichtung(int richtung) {
		this.richtung = richtung;
	}

	
}
