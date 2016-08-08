package Blocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Tile {

	public static ArrayList<Short> TexturesLoaded = new ArrayList<Short>();
	public static HashMap<Short, Image> Textures = new HashMap<Short, Image>();
	
	public static Image voidBlock;
	public static Image cursor;
	
	public static void load() {
		try {
			voidBlock = new Image("resources/terrain/voidBlock.png");
			cursor = new Image("resources/misc/cursor.png");
		} catch (Exception e) {
			System.out.println("Error while importing Images! " + e);
		}
	}
	
	public static void requestTiles(short id) {
		if(Textures.get(id) != null) { 
			/*Already has Texture*/
		} else {
			Image temp = null;
			System.out.println("Requesting Texture for ID: " + id);
			try {
				temp = new Image("resources/terrain/" + id + ".png");
				TexturesLoaded.add(id);
				Textures.put(id, temp);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error Requesting Tile: " + e);
			}
		}
	}
	
	public static void cleanUpTiles() {
		// Check Blocks in players view.
		// If there is a block that is loaded that isnt in the players view. Unload those textures.
	}
	
}
