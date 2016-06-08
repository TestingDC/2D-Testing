package Blocks;

import java.awt.Rectangle;

public class Block {
	
	public int id = 0;
	public int metaid = 0;
	public int rotation = 0;
	public String unknown;
	
	public Block(int id, int metaid, int rotation) {
		this.id = id;
		this.metaid = metaid;
		this.rotation = rotation;
	}
	
	public void setBounds(Rectangle rectangle) {
		setBounds(rectangle);
	}
	
	public void render() {
		// Soon. TODO
	}	
}
