package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import labyrinth.Labyrinth;

public class Settings {

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
	

	public Settings() {
		createFrame();
		createMainContent();
		createRightColumn();
		labyrinthZeichnen(loadLab());
		sizePositionVisibility();
	}

	public void createFrame() {
		window = new JFrame("Neuer Vergleich");
		contentPane = window.getContentPane();
	}

	public void sizePositionVisibility() {
		window.setSize(400, 300);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width / 2 - 200, dim.height / 2 - 150);
		window.setVisible(false);
		window.setResizable(false);
	}
	
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
//		leftPanel.setBackground(Color.green);
		leftPanel.setPreferredSize(new Dimension(190, 260));
		leftPanel.setLayout(new GridBagLayout());

		rightPanel = new JPanel();
//		rightPanel.setBackground(Color.black);
		rightPanel.setPreferredSize(new Dimension(190, 260));
		rightPanel.setLayout(new BorderLayout());

		mainPanel.add(leftPanel, con_x0_y0);
		mainPanel.add(rightPanel, con_x2_y0);

		contentPane.add(BorderLayout.CENTER, mainPanel);

	}
	
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
		rightForthPanel.add(okButton,BorderLayout.NORTH);
		
		JPanel topAlignPanel = new JPanel();
		topAlignPanel.setLayout(new GridBagLayout());
		
		topAlignPanel.add(rightFirstPanel,con_x0_y0_special);
		topAlignPanel.add(rightSecondPanel,con_x0_y1_special);
		topAlignPanel.add(rightThirdPanel,con_x0_y2_special);
		topAlignPanel.add(rightForthPanel,con_x0_y3_special);
		
		rightPanel.add(topAlignPanel,BorderLayout.NORTH);

		
	}

	
	public void changeVisibility(boolean b) {
		window.setVisible(b);
	}
	
	public void labyrinthZeichnen(Labyrinth labyrinth) {
		leftPanel.add(new JLabyrinth(labyrinth, 13));
		window.validate();
	}
	
	public Labyrinth loadLab() {
		int[] start = new int[2]; // Variable fï¿½r den Startpunkt definieren und befï¿½llen
		start[0] = 0;
		start[1] = 3;
		
		int[] ende = new int[2]; // Variable fï¿½r den Endpunkt definieren und befï¿½llen
		ende[0] = 11;
		ende[1] = 2;
		
//		char[][] lab = { // Labyrinth definieren
//		{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
//				{ '0', '0', '0', '1', '0', '0', '1', '1', '0', '0', '0', '0' },
//				{ '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '1', '1' },
//				{ '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '0', '0' },
//				{ '0', '0', '1', '0', '1', '0', '1', '0', '0', '0', '0', '0' },
//				{ '0', '0', '1', '1', '1', '0', '1', '0', '0', '0', '0', '0' },
//				{ '0', '0', '0', '0', '1', '0', '1', '1', '1', '0', '1', '0' },
//				{ '0', '0', '0', '1', '1', '0', '1', '0', '1', '0', '1', '0' },
//				{ '0', '1', '1', '1', '0', '0', '0', '0', '1', '1', '1', '0' },
//				{ '0', '1', '0', '1', '1', '1', '1', '0', '1', '0', '1', '0' },
//				{ '0', '1', '1', '0', '0', '0', '1', '1', '1', '0', '0', '0' },
//				{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' } };
		
		char[][] lab = { // Labyrinth definieren
				{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' },
				{ '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '1', '0' },
				{ '0', '1', '0', '0', '0', '1', '0', '0', '0', '0', '1', '1' },
				{ '1', '1', '0', '1', '1', '1', '0', '1', '0', '1', '1', '0' },
				{ '0', '1', '1', '1', '0', '0', '0', '1', '0', '0', '1', '0' },
				{ '0', '0', '1', '1', '0', '1', '0', '1', '1', '0', '1', '0' },
				{ '0', '1', '1', '1', '1', '1', '0', '0', '1', '1', '1', '0' },
				{ '0', '1', '0', '0', '0', '1', '1', '0', '1', '1', '0', '0' },
				{ '0', '1', '1', '1', '0', '1', '0', '0', '0', '1', '1', '0' },
				{ '0', '0', '0', '1', '1', '1', '1', '1', '0', '1', '0', '0' },
				{ '0', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '0' },
				{ '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' } };

		return new Labyrinth(lab, start, ende); // neues Labyrinth-Objekt generieren mit den obigen Daten
	}

}
