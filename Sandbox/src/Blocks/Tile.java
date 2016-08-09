package Blocks;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Image;

public class Tile {

	public static ArrayList<Short> TexturesLoaded = new ArrayList<Short>();
	public static HashMap<Short, Image> Textures = new HashMap<Short, Image>();
	
	public static Image voidBlock;
	public static Image cursor;
	
	public static void load() {
		try {
			voidBlock = new Image("resources/terrain/voidBlock.png");
			cursor = new Image("resources/misc/cursor.png");
			
			File dir = new File("src/resources/terrain");
			
			//for(File f : dir.listFiles()) {
				//if(f.getName().endsWith(".png")) {
					//Textures.put(Short.parseShort(f.getName().substring(0, 1)), new Image(f.getPath()));
				//}
			//}
			
			
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
}
