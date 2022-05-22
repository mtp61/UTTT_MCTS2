package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import game.GameState;

public class Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int width, height;
	private GameState gs;
	
	public Panel(GameState gs, int width, int height) {
		this.gs = gs;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// clear
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		// draw board
		gs.draw(g, 50, 50, width - 100, height - 100);
	}
	
	public void mousePressed(MouseEvent e) {
//		int mouseX = e.getX();
//		int mouseY = e.getY();
		
		// TODO
	}
	
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			// TODO test
			case KeyEvent.VK_ESCAPE:
				System.out.println("escape hit");
				break;
		}
	}
	
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
}
