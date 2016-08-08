package World;

import java.awt.Rectangle;

import Blocks.Block;
import Core.Game;
import External.Perlin;

public class Generator {

	private int seed = 0;
	private int width = 0;
	private int height = 0; // map dimensions GOES BY BLOCKS NOT PIXELS. 50 width = 50 blocks, 50 height = 50 blocks

	private float waterheight = (float) 0.3;
	private int groundlevel = height - 50;

	private int gl = 70;

	@SuppressWarnings("unused")
	private int waterlvl = 70;

	private int[] tiles; // tile array
	private int[] gtiles; // Grass Tile Array
	private int[] waterlevel; // Water tile array

	private int groundHeight = height - groundlevel;


	// Use Math and Generate a Landscape with Perlin Noise //

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getSeed() {
		return seed;
	}


	public void generatePerlin() {
		tiles = new int[width * groundHeight]; // tiles is map.width * map.height
		gtiles = new int[width * groundHeight];
		waterlevel = new int[width * groundHeight];
		Perlin noise = new Perlin(); // New Instance of Perlin Noise
		seed = Perlin.RANDOMSEED;
		int freq = 150; // Set up Frequency

		for(int x=0;x<width;x++) { // -------------------V Noise Default: 1//
			float h = (noise.noise((float) x / freq, 0) + 1) / 2; // make noise 0 to 1
			for(int y=0;y<groundHeight;y++) {
				float current = (float) (groundHeight - y) / groundHeight;

				if(current > h) 
					tiles[x+y*width]=2; // Generate of Sky (Top Layer) // Sets up values for Tiles 1 and 2.
				else tiles[x+y*width]=1; // Generation of Dirt (Bottom Layer)
			}
		}
		// Generate Grass Level
		for(int x=0;x<width;x++) { // -------------------V Noise Default: 1//
			float h = (noise.noise((float) x / freq, 0) + 1) / 2; // make noise 0 to 1
			for(int y=0;y<groundHeight;y++) {
				float current = (float) (groundHeight - y) / groundHeight;

				if(current > h) 
					gtiles[x+y*width]=2; // Sets up values for Tiles 1 and 2.
				else gtiles[x+y*width]=1;

			}
		}
		// Generate Water Level
		for(int xw=0;xw<width;xw++) { // -------------------V Noise Default: 1//
			for(int yw=0;yw<groundHeight;yw++) {
				float currentw = (float) (groundHeight - yw) / groundHeight;

				if(currentw > waterheight) 
					waterlevel[xw+yw*width]=2; // Sets up values for Tiles 1 and 2.
				else waterlevel[xw+yw*width]=1;
			}
		}
	}

	// --- Render Map into Tile Grid --- //
	public void generateLandscape(Layer Foreground) {

		for(int yw = 0; yw < Foreground.getLengthY(); yw++) {
			for(int xw = 0; xw < Foreground.getLengthX(); xw++) {
				Foreground.setBlock(xw, yw, new Block((short) 0, (byte) 0, (byte) 0, 0.0f, 0.05f, new Rectangle(xw * Game.TileSize, yw * Game.TileSize, Game.TileSize, Game.TileSize)));
			}
		}

		for (int yw = 0; yw < groundHeight; yw++) {
			for (int xw = 0; xw < width; xw++) {
				if (waterlevel[xw + yw * width] == 1) {
					//int Math1 = yw + gl;
					//Foreground.setBlock(xw, Math1, Blocks.water);
				}
			}
		}
		System.out.println("Generated Water and Air");
		for (int yw = 0; yw < groundHeight; yw++) {
			for (int xw = 0; xw < width; xw++) {
				if (gtiles[xw + yw * width] == 1) {
					int Math1 = yw + (gl - 1);
					Foreground.setBlock(xw, Math1, new Block((short) 3, (byte) 0, (byte) 0, 0.0f, 0.2f, new Rectangle(xw * Game.TileSize, Math1 * Game.TileSize, Game.TileSize, Game.TileSize)));
					//render(t4, xw * size, yw * size + ((size * gl) - size), size, size, 1.0f, 1.0f, 1.0f); // render grass tile
				}
			}
		}
		System.out.println("Generated Pre-Grass Layer");
		for (int yw = 0; yw < groundHeight; yw++) {
			for (int xw = 0; xw < width; xw++) {
				if (tiles[xw + yw * width] == 1) {
					int Math1 = yw + gl;
					Foreground.setBlock(xw, Math1, new Block((short) 4, (byte) 0, (byte) 0, 0.0f, 0.2f, new Rectangle(xw * Game.TileSize, Math1 * Game.TileSize, Game.TileSize, Game.TileSize)));
					//render(t, xw * size, yw * size + (size * gl), size, size, 1.0f, 1.0f, 1.0f); // render dirt tile
				}
			}
		}
		System.out.println("Generated Dirt Layer 1");
		for (int yw = (gl + 50); yw < (Foreground.getLengthY() - 500); yw++) { // yw = stonelevel
			for (int xw = 0; xw < width; xw++) {
				//int Math1 = yw + (Core.Game.gl + 50);
				Foreground.setBlock(xw, yw, new Block((short) 4, (byte) 0, (byte) 0, 0.0f, 0.2f, new Rectangle(xw * Game.TileSize, yw * Game.TileSize, Game.TileSize, Game.TileSize)));
				//render(t5, xw * size, yw * size, size, size, 1.0f, 1.0f, 1.0f); // render grass tile
			}
		}
		System.out.println("Generated Dirt Layer 2");
		for (int yw = (gl + 550); yw < Foreground.getLengthY(); yw++) { // yw = stonelevel
			for (int xw = 0; xw < width; xw++) {
				//int Math1 = yw + (Core.Game.gl + 50);
				Foreground.setBlock(xw, yw, new Block((short) 4, (byte) 0, (byte) 0, 0.0f, 0.2f, new Rectangle(xw * Game.TileSize, yw * Game.TileSize, Game.TileSize, Game.TileSize)));
				//render(t5, xw * size, yw * size, size, size, 1.0f, 1.0f, 1.0f); // render grass tile
			}
		}
		System.out.println("Generated Stone Layer");
		for(int x=0;x<Foreground.getLengthX();x++) {
			for(int y=0;y<Foreground.getLengthY();y++) {
				if(Foreground.getBlock(x, y).id == 6){
					int Math1 = y + 1;
					if(Foreground.getBlock(x, Math1).id == 3) {
						Foreground.setBlock(x, Math1, new Block((short) 5, (byte) 0, (byte) 0, 0.0f, 0.2f, new Rectangle(x * Game.TileSize, Math1 * Game.TileSize, Game.TileSize, Game.TileSize)));
						int Math2 = y + 2;
						Foreground.setBlock(x, Math2, new Block((short) 5, (byte) 0, (byte) 0, 0.0f, 0.2f, new Rectangle(x * Game.TileSize, Math2 * Game.TileSize, Game.TileSize, Game.TileSize)));
					}
				}
			}
		}
		System.out.println("Generated Sand Layer");
	}

}
