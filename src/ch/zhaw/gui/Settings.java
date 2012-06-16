package ch.zhaw.gui;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ch.zhaw.algorithmus.AStar;
import ch.zhaw.algorithmus.Algorithmus;
import ch.zhaw.algorithmus.Backtracking;
import ch.zhaw.algorithmus.Lefty;
import ch.zhaw.handler.Handler;
import ch.zhaw.labyrinth.Labyrinth;
import ch.zhaw.labyrinth.LabyrinthGenerator;

/* ---------------------------------------------------------------------------------------------
 * Settings: Beschreibung der Klasse
 * ---------------------------------------------
 * 
 * Diese Klasse ist für die darstellung des "neuer Versuch" Fensters verantwortlich.
 * --------------------------------------------------------------------------------------------- */

public class Settings {

	/* ---------------------------------------------
	 * Variablen
	 * --------------------------------------------- */
	
	private JFrame window;
	private Container contentPane;
	private JPanel mainPanel;
	private GridBagConstraints con_x0_y0;
	private GridBagConstraints con_x2_y0;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel rightFirstPanel;
	private JPanel rightSecondPanel;
	private JPanel rightThirdPanel;
	private JPanel rightForthPanel;
	private DefaultComboBoxModel alg1Model;
	private DefaultComboBoxModel alg2Model;
	private DefaultComboBoxModel labyrinthModel;
	private JComboBox alg1Box;
	private JComboBox alg2Box;
	private JComboBox labyrinthBox;
	private Labyrinth lab1;
	private Labyrinth lab2;
	private Labyrinth lab3;
	private Labyrinth lab4;
	private Labyrinth lab5;
	private Algorithmus alg1;
	private Algorithmus alg2;
	private Algorithmus alg3;
	private Algorithmus alg4;
	private Algorithmus alg5;
	private Algorithmus alg6;
	private Handler handler;

	/* ---------------------------------------------
	 * Methoden
	 * --------------------------------------------- */	
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: Settings (Konstruktor)
	 *  
	 * Author: Steve Heller
	 * 
	 * Beim Aufruf wird dem Settings-Objekt das aktuelle Handler-Objekt übergeben. Somit kann das Gui
	 * immer wieder Aktionen auf dem Handler vornehmen sowie ihm Daten übergeben
	 * --------------------------------------------------------------------------------------------- */
	
	public Settings(Handler handler) {
		this.handler = handler;
		
		variabelnInitialisieren();		
		createFrame();
		createMainContent();
		createRightColumn();
		labyrinthZeichnen(LabyrinthGenerator.loadLab(1));
		sizePositionVisibility();
		fillDropdowns();
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: variabelnInitialisieren
	 *  
	 * Author: Steve Heller
	 * 
	 * Initialisiert die Variabeln und erstellt die Algorithmen- und Labyrinthobjekte.
	 * --------------------------------------------------------------------------------------------- */
	public void variabelnInitialisieren() {
		lab1 = LabyrinthGenerator.loadLab(1);
		lab2 = LabyrinthGenerator.loadLab(2);
		lab3 = LabyrinthGenerator.loadLab(3);
		lab4 = LabyrinthGenerator.loadLab(4);
		lab5 = LabyrinthGenerator.loadLab(5);
		
		alg1 = new Backtracking();
		alg2 = new AStar("Manhattan",true);
		alg3 = new AStar("Manhattan",false);
		alg4 = new AStar("Luftlinie",true);
		alg5 = new AStar("Luftlinie",false);
		alg6 = new Lefty();
		
		
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: createFrame
	 *  
	 * Author: Steve Heller
	 * 
	 * Erstellt das Haupt-JFrame und organisiert dessen Eigenschaften.
	 * --------------------------------------------------------------------------------------------- */
	public void createFrame() {
		window = new JFrame("Neuer Vergleich");
		contentPane = window.getContentPane();
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: sizePositionVisibility
	 *  
	 * Author: Steve Heller
	 * 
	 * Hilfsmethode, welches das Fenster zentral positioniert, die Grösse definiert und einige
	 * weiter Einstellungen festlegt.
	 * --------------------------------------------------------------------------------------------- */
	public void sizePositionVisibility() {
		window.setSize(400, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width / 2 - 200, dim.height / 2 - 150);
		window.setVisible(false);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: createMainContent
	 *  
	 * Author: Steve Heller
	 * 
	 * Diese Methode generiert das Haupt JPanel und organisiert dessen Content im Hauptfenster.
	 * --------------------------------------------------------------------------------------------- */
	public void createMainContent() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		con_x0_y0 = new GridBagConstraints();
		con_x0_y0.gridy = 0;
		con_x0_y0.gridx = 0;
		con_x0_y0.anchor = GridBagConstraints.LINE_START;

		con_x2_y0 = new GridBagConstraints();
		con_x2_y0.gridy = 0;
		con_x2_y0.gridx = 2;
		con_x2_y0.anchor = GridBagConstraints.LINE_START;

		leftPanel = new JPanel();
		leftPanel.setPreferredSize(new Dimension(190, 260));
		leftPanel.setLayout(new GridBagLayout());

		rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(190, 260));
		rightPanel.setLayout(new BorderLayout());

		mainPanel.add(leftPanel, con_x0_y0);
		mainPanel.add(rightPanel, con_x2_y0);

		contentPane.add(BorderLayout.CENTER, mainPanel);

	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: createRightColumn
	 *  
	 * Author: Steve Heller
	 * 
	 * Diese Methode erstellt den rechten Teil des Frames initial.
	 * ---------------------------------------------------------------------------------------- */
	public void createRightColumn() {
		GridBagConstraints con_x0_y0_special = new GridBagConstraints();
		con_x0_y0_special.gridy = 0;
		con_x0_y0_special.gridx = 0;
		con_x0_y0_special.insets = new Insets(10,0,0,0);
		con_x0_y0_special.anchor = GridBagConstraints.PAGE_START;
		
		GridBagConstraints con_x0_y1_special = new GridBagConstraints();
		con_x0_y1_special.gridy = 1;
		con_x0_y1_special.gridx = 0;
		con_x0_y1_special.insets = new Insets(10,0,0,0);
		con_x0_y1_special.anchor = GridBagConstraints.PAGE_START;
		
		GridBagConstraints con_x0_y2_special = new GridBagConstraints();
		con_x0_y2_special.gridy = 2;
		con_x0_y2_special.gridx = 0;
		con_x0_y2_special.insets = new Insets(10,0,0,0);
		con_x0_y2_special.anchor = GridBagConstraints.PAGE_START;
		
		GridBagConstraints con_x0_y3_special = new GridBagConstraints();
		con_x0_y3_special.gridy = 3;
		con_x0_y3_special.gridx = 0;
		con_x0_y3_special.insets = new Insets(40,0,0,0);
		con_x0_y3_special.anchor = GridBagConstraints.PAGE_START;
		
		
		rightFirstPanel = new JPanel();
		rightFirstPanel.setPreferredSize(new Dimension(190, 50));
		rightFirstPanel.setLayout(new BorderLayout());
		
		rightSecondPanel = new JPanel();
		rightSecondPanel.setPreferredSize(new Dimension(190, 50));
		rightSecondPanel.setLayout(new BorderLayout());
		
		rightThirdPanel = new JPanel();
		rightThirdPanel.setPreferredSize(new Dimension(190, 50));
		rightThirdPanel.setLayout(new BorderLayout());
		
		rightForthPanel = new JPanel();
		rightForthPanel.setPreferredSize(new Dimension(190, 50));
		rightForthPanel.setLayout(new BorderLayout());
		
		JLabel labyrinthWaehlen = new JLabel("Labyrinth wählen:");
		rightFirstPanel.add(labyrinthWaehlen,BorderLayout.NORTH);
		labyrinthModel = new DefaultComboBoxModel();
		labyrinthBox = new JComboBox(labyrinthModel);
		rightFirstPanel.add(labyrinthBox,BorderLayout.CENTER);
		
		JLabel alg1Waehlen = new JLabel("Algorithmus 1 wählen:");
		rightSecondPanel.add(alg1Waehlen,BorderLayout.NORTH);
		alg1Model = new DefaultComboBoxModel();
		alg1Box = new JComboBox(alg1Model);
		rightSecondPanel.add(alg1Box,BorderLayout.CENTER);
		
		JLabel alg2Waehlen = new JLabel("Algorithmus 2 wählen");
		rightThirdPanel.add(alg2Waehlen,BorderLayout.NORTH);
		alg2Model = new DefaultComboBoxModel();
		alg2Box = new JComboBox(alg2Model);
		rightThirdPanel.add(alg2Box,BorderLayout.CENTER);
		
		JButton okButton = new JButton("Los");
		okButton.addActionListener(new losButtonAction());
		rightForthPanel.add(okButton,BorderLayout.NORTH);
		
		JPanel topAlignPanel = new JPanel();
		topAlignPanel.setLayout(new GridBagLayout());
		
		topAlignPanel.add(rightFirstPanel,con_x0_y0_special);
		topAlignPanel.add(rightSecondPanel,con_x0_y1_special);
		topAlignPanel.add(rightThirdPanel,con_x0_y2_special);
		topAlignPanel.add(rightForthPanel,con_x0_y3_special);
		
		rightPanel.add(topAlignPanel,BorderLayout.NORTH);
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: fillDropdowns
	 *  
	 * Author: Steve Heller
	 * 
	 * Lädt die Dropdowns.
	 * ---------------------------------------------------------------------------------------- */
	public void fillDropdowns() {
		labyrinthModel.addElement(lab1);
		labyrinthModel.addElement(lab2);
		labyrinthModel.addElement(lab3);
		labyrinthModel.addElement(lab4);
		labyrinthModel.addElement(lab5);
		labyrinthBox.addActionListener(new labyrinthBoxAction());
		
		alg1Model.addElement(alg1);
		alg1Model.addElement(alg2);
		alg1Model.addElement(alg3);
		alg1Model.addElement(alg4);
		alg1Model.addElement(alg5);
		alg1Model.addElement(alg6);
		
		alg2Model.addElement(alg1);
		alg2Model.addElement(alg2);
		alg2Model.addElement(alg3);
		alg2Model.addElement(alg4);
		alg2Model.addElement(alg5);
		alg2Model.addElement(alg6);
	}

	/* ---------------------------------------------------------------------------------------------
	 * Methode: changeVisibility
	 *  
	 * Author: Steve Heller
	 * 
	 * Diese public Methode stellt die private Methode setVisible öffentlich zur
	 * Verfügung
	 * ---------------------------------------------------------------------------------------- */
	public void changeVisibility(boolean b) {
		window.setVisible(b);
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * Methode: labyrinthZeichnen
	 *  
	 * Author: Steve Heller
	 * 
	 * Zeichnet das übergebene Objekt vom Typ Labyrinth links im Fenster
	 * ein.
	 * ---------------------------------------------------------------------------------------- */
	public void labyrinthZeichnen(Labyrinth labyrinth) {
		leftPanel.removeAll();
		leftPanel.add(new JLabyrinth(labyrinth, (labyrinth.getRastermass() / 3 * 2)));
		window.validate();
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * labyrinthBoxAction: Beschreibung der Klasse
	 * ---------------------------------------------
	 * 
	 * Diese Klasse definiert den ActionListener für das Labyrinth-Dropdown.
	 * 
	 * --------------------------------------------------------------------------------------------- */
	private class labyrinthBoxAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			labyrinthZeichnen((Labyrinth) labyrinthModel.getSelectedItem());
		}
		
	}
	
	/* ---------------------------------------------------------------------------------------------
	 * losButtonAction: Beschreibung der Klasse
	 * ---------------------------------------------
	 * 
	 * Diese Klasse definiert den ActionListener für den "Los" Button
	 * 
	 * --------------------------------------------------------------------------------------------- */
	private class losButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (alg1Model.getSelectedItem() == alg2Model.getSelectedItem()) {
				JOptionPane.showMessageDialog(window, "Bitte wählen Sie zwei verschiedene Algorithmen",
						"Nicht geklappt", JOptionPane.ERROR_MESSAGE);
			} else {
				window.setVisible(false);
				handler.setAlg1((Algorithmus) alg1Model.getSelectedItem());
				handler.setAlg2((Algorithmus) alg2Model.getSelectedItem());
				handler.setLabyrinth((Labyrinth) labyrinthModel.getSelectedItem());
				handler.setGuiAlgNames();
				handler.resetGuiStatistics(false);
				handler.guiSetEnable(true);
			}
		}
	}

}
