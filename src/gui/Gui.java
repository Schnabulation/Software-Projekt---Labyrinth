package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
	private JPanel topLeftPanel;
	private JPanel bottomLeftPanel;
	private JPanel rightColumnPanel;
	private GridBagConstraints con_x2_y0;
	private JSeparator vertSeperator;
	private GridBagConstraints con_x0_y2;
	private JSeparator horiSeperator;
	
	public Gui() {
		createFrame();
		createMenu();
		createMainContent();
		createRightColumn();
		sizePositionVisibility();
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
		rightColumnPanel.setLayout(new GridBagLayout());
		
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
		
	}
	
	public void createMenu() {
		menu = new JMenuBar();
		file = new JMenu("Datei");
		info = new JMenu("?");
		menu.add(file);
		menu.add(info);

		window.setJMenuBar(menu);

	}
	
	public void labyrinth1Zeichnen(Labyrinth labyrinth) {
		topLeftPanel.add(new JLabyrinth(labyrinth, 20));
		window.validate();
	}
	
	public void labyrinth2Zeichnen(Labyrinth labyrinth) {
		bottomLeftPanel.add(new JLabyrinth(labyrinth, 20));
		window.validate();
	}

}
