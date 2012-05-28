package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import labyrinth.Labyrinth;

public class JLabyrinth extends JPanel {

	private static final long serialVersionUID = -2186655955384671355L;
	private static final int INNENABSTAND = 0;
	private int rastermass;
	private Labyrinth labyrinth;

	public JLabyrinth(Labyrinth labyrinth, int rastermass) {

		this.labyrinth = labyrinth;
		this.rastermass = rastermass;

		this.setBorder(new EmptyBorder(INNENABSTAND, INNENABSTAND,
				INNENABSTAND, INNENABSTAND));
	}

	@Override
	public Dimension getPreferredSize() {
		if (labyrinth == null) {
			return new Dimension(0, 0);
		} else {
			return new Dimension(rastermass * labyrinth.getBreite(), rastermass
					* labyrinth.getLaenge());
		}
	}

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
					graphics.setColor(Color.BLUE);
					graphics.fillRect(rastermass * j + this.rastermass/4, rastermass * i + this.rastermass/4,
							this.rastermass/2, this.rastermass/2);
				} else if (labyrinth.getChar(j, i) == 'g') {
					graphics.setColor(Color.WHITE);
					graphics.fillRect(rastermass * j, rastermass * i,
							this.rastermass, this.rastermass);
					graphics.setColor(Color.YELLOW);
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
