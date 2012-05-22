package gui;

import handler.Main;

import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;

import labyrinth.Labyrinth;
 
/**
 * Ein Objekt dieser Klasse dient der Darstellung eines Labyrinths.
 */
public class UILabyrinth extends JFrame {
  
  /**
   * H�he des Labyrinths.
   */
  private static final int HOEHE = 1000;
  
  /**
   * Breite des Labyrinths.
   */
  private static final int BREITE = 1000;
  
  /**
   * Pixel pro Rastereinheit des Labyrinths.
   */
  private static final int PIXEL_PRO_RASTER = 10;
  
  /**
   * Labyrinth, das dargestellt wird.
   */
  private static Labyrinth labyrinth;
  
  /**
   * Komponente zur Darstellung des Labyrinths.
   */
  private Labyrinthdarstellung darstellung;
  
  /**
   * Erzeugt die Oberfl�che zur Darstellung des �bergebenen Labyrinths.
   *
   * @param labyrinth  Labyrinth, das dargestellt wird
   * @param rastermass  Anzahl Pixel pro Rastereinheit
   */
  public UILabyrinth(Labyrinth labyrinth, int rastermass) {
    
    super("Labyrinth");
    
    this.labyrinth = labyrinth;
    
    /* Erzeugt die Komponenten dieses Frame.
     */
    erzeugeKomponenten(labyrinth, rastermass);
    
    /* Anwendung beim Schlie�en dieses Frame beenden.
     */
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  /**
   * Erzeugt die Komponenten dieses Frame.
   */
  private void erzeugeKomponenten(Labyrinth labyrinth, int rastermass) {
    
    Container container = this.getContentPane();
    container.setLayout(new GridLayout(1, 1));
    
    /*
     * Objekt zur Darstellung des Labyrinths erzeugen und dem
     * Container diesen Frame hinzuf�gen.
     */
    darstellung = new Labyrinthdarstellung(labyrinth, rastermass);
    container.add(darstellung);
  }
  
  /**
   * Start der Anwendung.
   *
   * @param args  wird nicht verwendet
   */
  
  public static void loadLab() {
		int[] start = new int[2]; // Variable f�r den Startpunkt definieren und bef�llen
		start[0] = 0;
		start[1] = 3;
		
		int[] ende = new int[2]; // Variable f�r den Endpunkt definieren und bef�llen
		ende[0] = 11;
		ende[1] = 2;
		
		char[][] lab = { // Labyrinth definieren
			  {'0','0','0','0','0','0','0','0','0','0','0','0'},
			  {'0','0','0','1','0','0','1','1','0','0','0','0'},
			  {'0','0','0','1','1','0','1','0','0','1','1','1'},
			  {'1','1','1','0','1','0','1','1','1','1','0','0'},
			  {'0','0','1','0','1','0','1','0','0','0','0','0'},
			  {'0','0','1','1','1','0','1','0','0','0','0','0'},
			  {'0','0','0','0','1','0','1','1','1','0','1','0'},
			  {'0','0','0','1','1','0','1','0','1','0','1','0'},
			  {'0','1','1','1','0','0','0','0','1','1','1','0'},
			  {'0','1','0','1','1','1','1','0','1','0','1','0'},
			  {'0','1','1','0','0','0','1','1','1','0','0','0'},
			  {'0','0','0','0','0','0','0','0','0','0','0','0'}};
		
		labyrinth = new Labyrinth(lab, start, ende); // neues Labyrinth-Objekt generieren mit den obigen Daten
	}
  
  public static void main(String[] args) {
	  
	UILabyrinth.loadLab();
	  
	  
    
    UILabyrinth fenster = new UILabyrinth(labyrinth, PIXEL_PRO_RASTER);
    fenster.pack();
    fenster.setResizable(true);
    fenster.setVisible(true);
  }
  
}
