package gui;

import handler.Handler;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import labyrinth.Labyrinth;

public class Gui {
	private JFrame window;
	private JMenuBar menu;
	private JMenu file;
	private JMenu info;
	private Container contentPane;
	private JPanel mainPanel;
	private GridBagConstraints con_x0_y0;
	private GridBagConstraints con_x1_y0;
	private GridBagConstraints con_x0_y1;
	private GridBagConstraints con_x1_y1;
	private GridBagConstraints con_x0_y2;
	private GridBagConstraints con_x2_y0;
	private GridBagConstraints con_x0_y3;
	private JPanel topLeftPanel;
	private JPanel bottomLeftPanel;
	private JPanel rightColumnPanel;
	private JSeparator vertSeperator;
	private JSeparator horiSeperator;
	private JPanel rightFirstPanel;
	private JPanel rightSecondPanel;
	private JPanel rightThirdPanel;
	private JPanel rightForthPanel;
	private Handler handler;
	private JLabel abgelaufeneZeit;
	private JLabel anzahlSchritte;
	private JPanel rightFifthPanel;
	private JSlider speed;
	
	public Gui(Handler handler) {
		this.handler = handler;
		
		createFrame();
		createMenu();
		createMainContent();
		createRightColumn();
		sizePositionVisibility();
		
		handler.openSettings();
	}
	
	public void createFrame() {
		window = new JFrame("Software-Projekt - Labyrinth");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = window.getContentPane();
	}
	
	public void sizePositionVisibility() {
		window.setSize(600, 600);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation(dim.width / 2 - 300, dim.height / 2 - 300);
		window.setVisible(true);
		window.setResizable(false);
	}
	
	public void createMainContent() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		
		con_x0_y0 = new GridBagConstraints();
		con_x0_y0.gridy = 0;
		con_x0_y0.gridx = 0;
		con_x0_y0.anchor = GridBagConstraints.LINE_START;
		
		con_x1_y1 = new GridBagConstraints();
		con_x1_y1.gridy = 1;
		con_x1_y1.gridx = 1;
		con_x1_y1.anchor = GridBagConstraints.LINE_START;
		
		con_x1_y0 = new GridBagConstraints();
		con_x1_y0.gridy = 0;
		con_x1_y0.gridx = 1;
		con_x1_y0.gridheight = 3;
		con_x1_y0.anchor = GridBagConstraints.LINE_START;
		
		con_x2_y0 = new GridBagConstraints();
		con_x2_y0.gridy = 0;
		con_x2_y0.gridx = 2;
		con_x2_y0.gridheight = 3;
		con_x2_y0.anchor = GridBagConstraints.LINE_START;
		
		con_x0_y1 = new GridBagConstraints();
		con_x0_y1.gridy = 1;
		con_x0_y1.gridx = 0;
		con_x0_y1.anchor = GridBagConstraints.CENTER;
		
		con_x0_y2 = new GridBagConstraints();
		con_x0_y2.gridy = 2;
		con_x0_y2.gridx = 0;
		con_x0_y2.anchor = GridBagConstraints.LINE_START;
		
		con_x0_y3 = new GridBagConstraints();
		con_x0_y3.gridy = 3;
		con_x0_y3.gridx = 0;
		con_x0_y3.anchor = GridBagConstraints.LINE_START;
		
		topLeftPanel = new JPanel();
//		topLeftPanel.setBackground(Color.green);
		topLeftPanel.setPreferredSize(new Dimension(290, 265));
		topLeftPanel.setLayout(new GridBagLayout());
		
		bottomLeftPanel = new JPanel();
//		bottomLeftPanel.setBackground(Color.red);
		bottomLeftPanel.setPreferredSize(new Dimension(290, 265));
		bottomLeftPanel.setLayout(new GridBagLayout());
		
		rightColumnPanel = new JPanel();
//		rightColumnPanel.setBackground(Color.black);
		rightColumnPanel.setPreferredSize(new Dimension(290, 530));
		rightColumnPanel.setLayout(new BorderLayout());
		
		mainPanel.add(topLeftPanel,con_x0_y0);
		mainPanel.add(bottomLeftPanel, con_x0_y2);
		mainPanel.add(rightColumnPanel, con_x2_y0);
		
		vertSeperator = new JSeparator(SwingConstants.VERTICAL);
		vertSeperator.setPreferredSize(new Dimension(1, 530));
		
		horiSeperator = new JSeparator(SwingConstants.HORIZONTAL);
		horiSeperator.setPreferredSize(new Dimension(250, 1));
		
		
		mainPanel.add(vertSeperator,con_x1_y0);
		mainPanel.add(horiSeperator,con_x0_y1);
		
		contentPane.add(BorderLayout.CENTER, mainPanel);
	}
	
	public void createRightColumn() {
		GridBagConstraints con_x0_y0_special = new GridBagConstraints();
		con_x0_y0_special.gridy = 0;
		con_x0_y0_special.gridx = 0;
//		con_x0_y0_special.weighty = 0;
		con_x0_y0_special.insets = new Insets(10,0,0,0);
		con_x0_y0_special.anchor = GridBagConstraints.PAGE_START;
		
		GridBagConstraints con_x0_y1_special = new GridBagConstraints();
		con_x0_y1_special.gridy = 1;
		con_x0_y1_special.gridx = 0;
//		con_x0_y1_special.weighty = 0;
		con_x0_y1_special.insets = new Insets(30,0,0,0);
		con_x0_y1_special.anchor = GridBagConstraints.PAGE_START;
		
		GridBagConstraints con_x0_y2_special = new GridBagConstraints();
		con_x0_y2_special.gridy = 2;
		con_x0_y2_special.gridx = 0;
//		con_x0_y2_special.weighty = 0;
		con_x0_y2_special.insets = new Insets(30,0,0,0);
		con_x0_y2_special.anchor = GridBagConstraints.PAGE_START;
		
		GridBagConstraints con_x0_y3_special = new GridBagConstraints();
		con_x0_y3_special.gridy = 3;
		con_x0_y3_special.gridx = 0;
//		con_x0_y3_special.weighty = 0.1;
		con_x0_y3_special.insets = new Insets(150,0,0,0);
		con_x0_y3_special.anchor = GridBagConstraints.PAGE_START;
		
		GridBagConstraints con_x0_y4_special = new GridBagConstraints();
		con_x0_y4_special.gridy = 4;
		con_x0_y4_special.gridx = 0;
//		con_x0_y4_special.weighty = 0.1;
		con_x0_y4_special.insets = new Insets(20,0,0,0);
		con_x0_y4_special.anchor = GridBagConstraints.PAGE_START;
		
		
		rightFirstPanel = new JPanel();
//		rightFirstPanel.setBackground(Color.black);
		rightFirstPanel.setPreferredSize(new Dimension(250, 30));
		rightFirstPanel.setLayout(new BorderLayout());
		
		rightSecondPanel = new JPanel();
//		rightSecondPanel.setBackground(Color.red);
		rightSecondPanel.setPreferredSize(new Dimension(250, 70));
		rightSecondPanel.setLayout(new BorderLayout());
		
		rightThirdPanel = new JPanel();
//		rightThirdPanel.setBackground(Color.blue);
		rightThirdPanel.setPreferredSize(new Dimension(250, 70));
		rightThirdPanel.setLayout(new BorderLayout());
		
		rightForthPanel = new JPanel();
//		rightForthPanel.setBackground(Color.green);
		rightForthPanel.setPreferredSize(new Dimension(250, 80));
		rightForthPanel.setLayout(new BorderLayout());
		
		rightFifthPanel = new JPanel();
//		rightFifthPanel.setBackground(Color.white);
		rightFifthPanel.setPreferredSize(new Dimension(250, 30));
		rightFifthPanel.setLayout(new BorderLayout());
		
		JButton neuerVergleich = new JButton("Neuer Vergleich");
		neuerVergleich.addActionListener(new neuerVergleichAction());
		rightFirstPanel.add(neuerVergleich,BorderLayout.CENTER);
		
		abgelaufeneZeit = new JLabel("<html>Länge Lösungsweg: <br>" +
				"Alg. 1:<br>Alg. 2:<br>Differenz:</html>");
		rightSecondPanel.add(abgelaufeneZeit,BorderLayout.NORTH);
		
		anzahlSchritte = new JLabel("<html>Anzahl Schritte:<br>" +
		"Alg. 1:<br>Alg. 2:<br>Differenz:</html>");
		rightThirdPanel.add(anzahlSchritte,BorderLayout.NORTH);
		
		speed = new JSlider(0, 1000, 100); 
		speed.setMinorTickSpacing(100);
		speed.setMajorTickSpacing(200);
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		
		JButton loeschen = new JButton("Löschen");
		loeschen.setPreferredSize(new Dimension(83, 30));
		loeschen.addActionListener(new loeschenButtonAction());
		JButton nextStep = new JButton("Step");
		nextStep.setPreferredSize(new Dimension(83, 30));
		nextStep.addActionListener(new nextStepButtonAction());
		JButton start = new JButton("Start");
		start.setPreferredSize(new Dimension(83, 30));
		start.addActionListener(new startButtonAction());
		
		TitledBorder rightForthPanelBorder;
		rightForthPanelBorder = BorderFactory.createTitledBorder("Speed (Wartezeit in ms)");
		rightForthPanel.setBorder(rightForthPanelBorder);
		rightForthPanel.add(speed,BorderLayout.NORTH);
		
		rightFifthPanel.add(loeschen,BorderLayout.WEST);
		rightFifthPanel.add(nextStep,BorderLayout.CENTER);
		rightFifthPanel.add(start,BorderLayout.EAST);
		
		JPanel topAlignPanel = new JPanel();
		topAlignPanel.setLayout(new GridBagLayout());
		
		topAlignPanel.add(rightFirstPanel,con_x0_y0_special);
		topAlignPanel.add(rightSecondPanel,con_x0_y1_special);
		topAlignPanel.add(rightThirdPanel,con_x0_y2_special);
		topAlignPanel.add(rightForthPanel,con_x0_y3_special);
		topAlignPanel.add(rightFifthPanel,con_x0_y4_special);
		
		rightColumnPanel.add(topAlignPanel,BorderLayout.NORTH);
		
	}
	
	public void createMenu() {
		menu = new JMenuBar();
		file = new JMenu("Datei");
		info = new JMenu("?");
		menu.add(file);
		menu.add(info);

		window.setJMenuBar(menu);

	}
	
	public void updateStatistics(int anzSchritteAlg1, int anzSchritteAlg2, long anzMillisAlg1, long anzMillisAlg2) {
		abgelaufeneZeit.setText("<html>Länge Lösungsweg: <br>" +
		"Alg. 1: " + anzMillisAlg1 + "<br>Alg. 2: " + anzMillisAlg2 + "<br>Differenz: " + Math.abs(anzMillisAlg1 - anzMillisAlg2) + "</html>");

		anzahlSchritte.setText("<html>Anzahl Schritte:<br>" +
		"Alg. 1: " + anzSchritteAlg1 + "<br>Alg. 2: " + anzSchritteAlg2 + "<br>Differenz: " + Math.abs(anzSchritteAlg1 - anzSchritteAlg2) + "</html>");
	}
	
	public void resetStatistics() {
		abgelaufeneZeit.setText("<html>Länge Lösungsweg: <br>" +
		"Alg. 1:<br>Alg. 2:<br>Differenz:</html>");
		
		anzahlSchritte.setText("<html>Anzahl Schritte:<br>" +
		"Alg. 1:<br>Alg. 2:<br>Differenz:</html>");
	}
	
	public void labyrinth1Zeichnen(Labyrinth labyrinth) {
		topLeftPanel.removeAll();
		topLeftPanel.add(new JLabyrinth(labyrinth, labyrinth.getRastermass()));
		window.validate();
	}
	
	public void labyrinth2Zeichnen(Labyrinth labyrinth) {
		bottomLeftPanel.removeAll();
		bottomLeftPanel.add(new JLabyrinth(labyrinth, labyrinth.getRastermass()));
		window.validate();
	}
	
	public int getSpeed() {
		return speed.getValue();
	}
	
	public void setEnable(boolean b) {
		window.setEnabled(b);
	}
	
	private class neuerVergleichAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			handler.resetHandler();
			handler.openSettings();
			setEnable(false);
		}	
	}
	
	public class startButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (speed.getValue() == 0) {
				handler.start();
			} else {
			handler.speed();
			}
		}
	}
	
	public class nextStepButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			handler.step();
		}
	}
	
	public class loeschenButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			topLeftPanel.removeAll();
			bottomLeftPanel.removeAll();
			resetStatistics();
			window.repaint();
			handler.resetHandler();
		}
		
	}

}
