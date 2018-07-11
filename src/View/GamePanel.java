package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import Controller.GameManager;
import Main.Tron;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private GameManager manager;
	
	public static final int W = Tron.config.getAsJsonObject("game_config").get("grid_wight").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), H = Tron.config.getAsJsonObject("game_config").get("grid_height").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt();

	private Timer timer;
	public static long  startTime;

	public GamePanel() {
		setBackground(Color.GRAY);
		setLayout(new BorderLayout());
		setSize(W, H);


		manager = new GameManager(W, H);
		timer = new Timer(30, this);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		manager.draw(g);
	}

	public void start() {
		startTime = System.currentTimeMillis();
		manager.reset();
		timer.start();
		System.out.println("- Début d'une nouvelle partie!");
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== timer)
			if(manager.checkState())
				repaint();
			else
				timer.stop();
			repaint();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
			manager.setP1TargetDirection(((manager.getP1().getDirection()) - 1) % 4);
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
			manager.setP1TargetDirection(((manager.getP1().getDirection()) + 1) % 4);

		if(e.getKeyCode() == KeyEvent.VK_Q)
			manager.setP2TargetDirection(((manager.getP2().getDirection()) - 1) % 4);
		else if(e.getKeyCode() == KeyEvent.VK_D)
			manager.setP2TargetDirection(((manager.getP2().getDirection()) + 1) % 4);

		if(e.getKeyCode() == KeyEvent.VK_R)
			start();
	}

	public void keyReleased(KeyEvent arg0) {

	}

	public void keyTyped(KeyEvent arg0) {

	}
}