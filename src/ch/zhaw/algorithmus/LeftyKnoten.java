package ch.zhaw.algorithmus;

/* ---------------------------------------------------------------------------------------------
 * LeftyKnoten: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Diese Klasse wird vom Lefty Algorithmus genutzt. Sie dient dazu beim demarkieren zu wissen, woher
 * man ursprünglich zu diesem Punkt gekommen ist, um sein Vorgänger Knoten als nächstes zu analysieren.
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
	 * Methode: LeftyKnoten (Konstruktor)
	 *  
	 * Author: Reto Huber
	 * 
	 * dem Konstruktor werden die Koordinaten und die Richtung woher man auf diesen Punkt gekommen ist
	 * übergeben.
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
