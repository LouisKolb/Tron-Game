package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH = GamePanel.W, FRAME_HEIGHT = GamePanel.H;

	public GamePanel gamePanel;

	public GameFrame() {
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Tron Game");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	
		gamePanel = new GamePanel();
		gamePanel.setVisible(true);
		add(gamePanel);
		gamePanel.start();
		setIconImage(new ImageIcon("tron.png").getImage());

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
			gamePanel.setVisible(true);
			add(gamePanel);
			gamePanel.start();
	}
}