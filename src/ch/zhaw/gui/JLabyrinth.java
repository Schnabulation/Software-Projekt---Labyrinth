package ch.zhaw.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch.zhaw.labyrinth.Labyrinth;

/* ---------------------------------------------------------------------------------------------
 * JLabyrinth: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Diese Klasse erweitert JPanel. Sie definiert ein neues Component, welches für die
 * grafische Darstellung eines Labyrinths verantwortlich ist.
 * --------------------------------------------------------------------------------------------- */

public class JLabyrinth extends JPanel {

	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	private static final long serialVersionUID = -2186655955384671355L;
	private static final int INNENABSTAND = 0;
	private int rastermass;
	private Labyrinth labyrinth;

	/* ---------------------------------------------
	 * Methoden
	 * --------------------------------------------- */	
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: JLabyrinth (Konstruktor)
	 *  
	 * Author: Steve Heller
	 * 
	 * Der Konstruktor erwartet ein Objekt vom Typ Labyrinth sowie einen Zahlenwert, welcher
	 * die Grösse des zu zeichnenden Labyrinths festlegt.
	 * --------------------------------------------------------------------------------------------- */
	
	public JLabyrinth(Labyrinth labyrinth, int rastermass) {

		this.labyrinth = labyrinth;
		this.rastermass = rastermass;

		this.setBorder(new EmptyBorder(INNENABSTAND, INNENABSTAND,
				INNENABSTAND, INNENABSTAND));
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: getPreferredSize
	 *  
	 * Author: Steve Heller
	 * 
	 * Überschreibt die Methode getPreferredSize. Falls das Objekt null ist, wird die Grösse
	 * 0 x 0 zurückgeliefert, ansonsten die Grösse des Labyrinths.
	 * --------------------------------------------------------------------------------------------- */
	
	public Dimension getPreferredSize() {
		if (labyrinth == null) {
			return new Dimension(0, 0);
		} else {
			return new Dimension(rastermass * labyrinth.getBreite(), rastermass
					* labyrinth.getLaenge());
		}
	}
		
	/* ---------------------------------------------------------------------------------------------
	 * Methode: paintComponent
	 *  
	 * Author: Steve Heller
	 * 
	 * Diese Methode zeichnet das Labyrinth wie folgt:
	 * Das Labyrinth-Array wird Zeile für Zeile und in der Zeile nochmal Spalte für Spalte 
	 * ausgelesen. Wird ein 0 vorgefunden, wird ein schwarzes Quadrat gezeichnet.
	 * Wird ein 1 vorgefunden, wird ein weisses Quadrat gezeichnet.
	 * --------------------------------------------------------------------------------------------- */
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		for (int i = 0; i < labyrinth.getLaenge(); i++) {
			for (int j = 0; j < labyrinth.getBreite(); j++) {
				if (labyrinth.getChar(j, i) == '0') {
					graphics.setColor(Color.BLACK);
					graphics.fillRect(rastermass * j, rastermass * i,
							this.rastermass, this.rastermass);
				} else if (labyrinth.getChar(j, i) == '1') {
					graphics.setColor(Color.WHITE);
					graphics.fillRect(rastermass * j, rastermass * i,
							this.rastermass, this.rastermass);
				} else if (labyrinth.getChar(j, i) == 'm') {
					graphics.setColor(Color.WHITE);
					graphics.fillRect(rastermass * j, rastermass * i,
							this.rastermass, this.rastermass);
					graphics.setColor(Color.GREEN);
					graphics.fillRect(rastermass * j + this.rastermass/4, rastermass * i + this.rastermass/4,
							this.rastermass/2, this.rastermass/2);
				} else if (labyrinth.getChar(j, i) == 'o') {
					graphics.setColor(Color.WHITE);
					graphics.fillRect(rastermass * j, rastermass * i,
							this.rastermass, this.rastermass);
					graphics.setColor(Color.GRAY);
					graphics.fillRect(rastermass * j + this.rastermass/4, rastermass * i + this.rastermass/4,
							this.rastermass/2, this.rastermass/2);
				} else if (labyrinth.getChar(j, i) == 'g') {
					graphics.setColor(Color.WHITE);
					graphics.fillRect(rastermass * j, rastermass * i,
							this.rastermass, this.rastermass);
					graphics.setColor(Color.LIGHT_GRAY);
					graphics.fillRect(rastermass * j + this.rastermass/4, rastermass * i + this.rastermass/4,
							this.rastermass/2, this.rastermass/2);
				} else {
					graphics.setColor(Color.WHITE);
					graphics.fillRect(rastermass * j, rastermass * i,
							this.rastermass, this.rastermass);
					graphics.setColor(Color.RED);
					graphics.fillRect(rastermass * j + this.rastermass/4, rastermass * i + this.rastermass/4,
							this.rastermass/2, this.rastermass/2);
				}

			}
		}

	}

}
