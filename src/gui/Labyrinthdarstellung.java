package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import labyrinth.Labyrinth;
 
/**
 * Ein Objekt dieser Klasse stellt ein Labyrinth grafisch dar.
 */
public class Labyrinthdarstellung extends JPanel {
  
  /**
   * Innenabstand der Darstellung vom Fensterrand.
   */
  private static final int INNENABSTAND = 0;
  
  /**
   * Pixel pro Rastereinheit des Labyrinths.
   */
  private int rastermass;
  
  /**
   * Labyrinth, das dargestellt wird.
   */
  private Labyrinth labyrinth;
  
  /**
   * Erzeugt ein Objekt dieser Klasse zur Darstellung des
   * �bergebenen Labyrinths.
   */
  public Labyrinthdarstellung(Labyrinth labyrinth, int rastermass) {
    
    this.labyrinth = labyrinth;
    this.rastermass = rastermass;
    
    this.setBorder(new EmptyBorder(INNENABSTAND, INNENABSTAND,
                                   INNENABSTAND, INNENABSTAND));
  }
  
  /**
   * Liefert bevorzugte Gr��e dieser Darstellung.
   * 
   * @return bevorzugte Gr��e basierend auf Rastermass und
   *         Gr��e des Labyrinths
   */
  @Override
  public Dimension getPreferredSize() {
    
    return (labyrinth == null)
           ? new Dimension(0, 0)
           : new Dimension(rastermass * labyrinth.getBreite()
                             + getInsets().left + getInsets().right,
                           rastermass * labyrinth.getLaenge()
                             + getInsets().top + getInsets().bottom);
  }
  
  /**
   * Zeichnet das Labyrinth in die angegebene Grafikumgebung.
   * 
   * @param graphics  Grafikumgebung, in die gezeichnet wird
   */
  public void paintComponent(Graphics graphics) {
    
    super.paintComponent(graphics);

    
  
		for (int i = 0; i < labyrinth.getLaenge(); i++) {
				for (int j = 0; j < labyrinth.getBreite(); j++) {
					if (labyrinth.getChar(j, i) == '0') {
					    graphics.setColor(Color.BLACK);
					} else if (labyrinth.getChar(j, i) == '1') {
					    graphics.setColor(Color.WHITE);
					} else if (labyrinth.getChar(j, i) == 'm') {
						graphics.setColor(Color.GREEN);
					} else {
						graphics.setColor(Color.RED);
					}
					graphics.fillRect(rastermass * j, rastermass * i, this.rastermass, this.rastermass);
				}
			}
    
//    for (int i = 0; i < labyrinth.gibAnzahlWandelemente(); i++) {
//      Punkt startpunkt = labyrinth.gibStartpunkt(i);
//      Punkt zielpunkt = labyrinth.gibEndpunkt(i);
//      graphics.drawLine(getInsets().left + rastermass * startpunkt.gibX(),
//              getInsets().top + rastermass * startpunkt.gibY(),
//              getInsets().left + rastermass * zielpunkt.gibX(),
//              getInsets().top + rastermass * zielpunkt.gibY());
//    }
  }
 
}
