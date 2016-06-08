package World;

import Blocks.Block;

public class Layer {
	private Block[][] Layer;
	private String name = "";
	
	public Layer(String name, int x_size, int y_size) {
		this.name = name;
		this.Layer = new Block[x_size][y_size];
	}
	
	public void fillLayer(Block a) {
		for(int x=0;x<Layer.length;x++) {
			for(int y=0;y<Layer[0].length;y++) {
				Layer[x][y] = a;
			}
		}
	}
	
	public void setBlock(int x, int y, Block a) {
		Layer[x][y] = a;
	}
	
	public Block getBlock(int x, int y) {
		return Layer[x][y];
	}
	
	public int getBlockId(int x, int y) {
		return Layer[x][y].id;
	}
	
	public int getLengthX() {
		return Layer.length;
	}
	
	public int getLengthY() {
		return Layer[0].length;
	}
	
	public String getName() {
		return name;
	}
	
	public void saveLayer(String fileName) {
		//Soon// TODO
	}
	
	public void loadLayer(String fileName) {
		//Soon// TODO
	}
	
}
