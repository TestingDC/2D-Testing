package Blocks;

import java.awt.Rectangle;

public class Block extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	public short id = 0;
	public byte metaid = 0;
	public byte rotation = 0;
	public float lightLevel = 0;
	public float lightBlock = 0.2f;
	public String unknown;
	
	public Block(short id, byte metaid, byte rotation, float lightLevel, float lightBlock, Rectangle box) {
		this.id = id;
		this.metaid = metaid;
		this.rotation = rotation;
		this.lightLevel = lightLevel;
		this.lightBlock = lightBlock;
		this.setBounds(box);
	}
}
