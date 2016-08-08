package Blocks;

import java.awt.Rectangle;

import org.newdawn.slick.Color;

import Player.Camera;

public class Block extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	public short id = 0;
	public byte metaid = 0;
	public byte rotation = 0;
	public float lightLevel = 0.0f;
	public float lightBlock = 0.2f;
	
	public Block(short id, byte metaid, byte rotation, float lightLevel, float lightBlock, Rectangle box) {
		this.id = id;
		this.metaid = metaid;
		this.rotation = rotation;
		this.lightLevel = lightLevel;
		this.lightBlock = lightBlock;
		//this.setBounds(box);
	}
	
	public void render(int x, int y, byte width, byte height) {
		//Color Color = new Color(lightLevel, lightLevel, lightLevel);
		Color Color = new Color(255,255,255);
		Tile.voidBlock.draw((x + Camera.CameraX), (y + Camera.CameraY), width, height, Color);
		//Tile.Textures.get(id).getSubImage((int) (rotation % 7), (int) Math.floor(rotation / 7), 32, 32).draw((x + Camera.CameraX), (y + Camera.CameraY), width, height, Color);
		//                                                                                      ^ Tile texture Size.
	}
}
