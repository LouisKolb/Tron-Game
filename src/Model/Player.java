package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.sql.*;

import javax.swing.JOptionPane;

import Main.Tron;

public class Player {

	private String name;
	private int direction;
	private LengthNode head;
	private Color color;

	public Player(String name, int direction, Color color, int x, int y) {
		this.name = name;
		this.direction = direction;
		this.color = color;
		head = new LengthNode(x, y, null);
	}

	public String getName() { return name; }

	public void setDirection(int direction) { this.direction = direction; }

	public int getDirection() { return direction; }

	public LengthNode getHead() { return head; }

	public void next() {
		switch(direction) {
		case 0:
			head = new LengthNode(head.getX(), head.getY() - Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), head);
			break;
		case 1:
			head = new LengthNode(head.getX() + Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), head.getY(), head);
			break;
		case 2:
			head = new LengthNode(head.getX(), head.getY() + Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), head);
			break;
		case 3:
			head = new LengthNode(head.getX() - Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), head.getY(), head);
			break;
		case -1:
			this.direction = 3;
			head = new LengthNode(head.getX(), head.getY() - Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), head);
			break;
		}
	}

	public boolean isLose(Player other, int maxWidth, int maxHeight) {
		for(LengthNode p = head.getNext(); p.getNext() != null ; p = p.getNext())
			if(head.sameValues(p))
				return true;
		for(LengthNode p = other.getHead(); p.getNext() != null ; p = p.getNext())
			if(head.sameValues(p))
				return true;
		if(head.getX() < 0 || head.getY() < 0 || head.getX() > maxWidth + Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt() || head.getY() > maxHeight + Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt())
			return true;
		return false;
	}
	
	public void isWinner(long gameTimer) {
		float GameTimer = gameTimer;
		GameTimer = GameTimer/1000;
		String time = Float.toString(GameTimer);

		try {
			String url="jdbc:mysql://localhost:3306/tron?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&noAccessToProcedureBodies=true";
			String user="root";
			String password="louiskolb67";

			Connection conn = null;
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("- SQL Connecté à la BDD!");

			CallableStatement cStmt = conn.prepareCall("{call add_game(?, ?)}");
			cStmt.setString(1, this.getName());
			cStmt.setString(2, time);
			cStmt.execute();
			System.out.println("- SQL Enregistré sur la BDD!");


		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		System.out.println("- Le joueur " + this.getName() + " a gagné!");
		System.out.println("- Durée de la partie: " + GameTimer + "s");
		System.out.println("- FIN DE LA PARTIE !");

		JOptionPane.showMessageDialog(null, "Le joueur " + this.getName() + " a gagné!\nAppuyez sur OK puis sur R pour recommencer!", "Fin de la partie", JOptionPane.INFORMATION_MESSAGE);
	}


	public void draw(Graphics g) {
		g.setColor(color);
		LengthNode p = head;
		for(;p != null; p=p.getNext())
			g.fillRect(p.getX(), p.getY(), Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt());	//AJOUTER au joueur un carr? de pos getX, getY de la taille PIXELSIZE
	}
}