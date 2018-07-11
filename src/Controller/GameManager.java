package Controller;

import java.awt.*;
import java.util.Date;

import Main.Tron;
import Model.Player;
import View.GamePanel;

public class GameManager {
	private Player player1, player2;
	private int width, height;

	public GameManager(int maxWidth, int maxHeight) {
		width = maxWidth;
		height = maxHeight;

		player1 = new Player(Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("name").getAsString(),Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("direction").getAsInt(), new Color(Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("color_r").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("color_g").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("color_b").getAsInt()), Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("start_x").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("start_y").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt());
		player2 = new Player(Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("name").getAsString(), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("direction").getAsInt(), new Color(Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("color_r").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("color_g").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("color_b").getAsInt()), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("start_x").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("start_y").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt());
	}

	public void setP1TargetDirection(int direct) { player1.setDirection(direct); }

	public void setP2TargetDirection(int direct) { player2.setDirection(direct); }

	public Player getP1() { return player1; }

	public Player getP2() { return player2; }

	public boolean checkState() {
		player1.next();
		player2.next();

		if(player1.isLose(player2, width, height))
		{
			player2.isWinner(gameoverTimer());
			return false;
		}
		else if(player2.isLose(player1, width, height))
		{
			player1.isWinner(gameoverTimer());
			return false;
		}
		return true;
	}

	private long gameoverTimer() {
		return (new Date()).getTime() - GamePanel.startTime;
	}

	public void reset() {
		player1 = new Player(Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("name").getAsString(),Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("direction").getAsInt(), new Color(Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("color_r").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("color_g").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("color_b").getAsInt()), Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("start_x").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("1").get("start_y").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt());
		player2 = new Player(Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("name").getAsString(), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("direction").getAsInt(), new Color(Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("color_r").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("color_g").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("color_b").getAsInt()), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("start_x").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt(), Tron.config.getAsJsonObject("players").getAsJsonObject("2").get("start_y").getAsInt() * Tron.config.getAsJsonObject("game_config").get("pixel_size").getAsInt());
	}

	public void draw(Graphics g) {
		player1.draw(g);
		player2.draw(g);
	}

}