package ch.zhaw.algorithmus;

/* ---------------------------------------------------------------------------------------------
 * AStarKnoten: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Diese Klasse wird vom AStar Algorithmus genutzt. Sie dient dazu einen Punkt, bzw. Knoten zu
 * speichern. Damit wird gewährleistet dass jeder abgespeicherte Punkt die nötigen Variablen und
 * Methoden hat.
 * 
 * --------------------------------------------------------------------------------------------- */


public class AStarKnoten {

	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	private AStarKnoten parent;
	private int x;
	private int y;
	private float h; //Abstand zum Ziel
	private float g; //zurückgelegter Weg vom Start
	private float f; // kosten gesammt
	
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
	public AStarKnoten(AStarKnoten parent, int x, int y, float h, boolean g1){
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.h = h;
		this.f = h;
		if (g1){
			if(parent != null){
				this.g = parent.getG() + 1;
				this.f = g+h;
			} else{
				this.g = 0;
			}	
		}
	}

	public float getF() {
		return f;
	}

	public void setF(float f) {
		this.f = f;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public AStarKnoten getParent() {
		return parent;
	}

	public void setParent(AStarKnoten parent) {
		this.parent = parent;
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

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}
	
}
