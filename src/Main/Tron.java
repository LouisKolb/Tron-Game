package Main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import View.GameFrame;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Tron {

	public static JsonObject config = new JsonObject();

	public static void main(String[] args) {
		try {
			File file = new File(getProgramPath() + "/config.json");
			FileReader configfile = new FileReader(file);
			config = new JsonParser().parse(configfile).getAsJsonObject();
		} catch (IOException e){
			e.printStackTrace();
		}
		System.out.println("- Taille de la grille: " + config.getAsJsonObject("game_config").get("grid_wight").getAsInt() + " X " + config.getAsJsonObject("game_config").get("grid_height").getAsInt());
		System.out.println("- Taille des joueurs: " + config.getAsJsonObject("game_config").get("pixel_size").getAsInt() + " px");
		GameFrame f = new GameFrame();
		f.addKeyListener(f.gamePanel);
	}

	private static String getProgramPath() {
		String currentdir = System.getProperty("user.dir");
		currentdir = currentdir.replace("\\", "/");
		return currentdir;
	}
}

