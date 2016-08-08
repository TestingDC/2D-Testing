package World;

import Blocks.Block;
import Core.Game;

public class Layer {
	
	private Chunk[][] Chunks;
	private byte chunkSize;
	private String name = "";
	
	public Layer(String name, byte chunkSize, int x_size, int y_size) {
		this.name = name;
		this.chunkSize = chunkSize;
		this.Chunks = new Chunk[(int) Math.floor(x_size/chunkSize)][(int) Math.floor(y_size/chunkSize)];
		
		for(int x = 0; x < Chunks.length; x++) {
			for(int y = 0; y < Chunks[0].length; y++) {
				Chunks[x][y] = new Chunk(Game.ChunkSize, x, y);
			}
		}
		fillLayer(new Block((short) -1, (byte) 0, (byte) 0, 0.0f, 0.0f, null));
	}
	
	public void fillLayer(Block a) {
		for(int x = 0; x < Chunks.length; x++) {
			for(int y = 0; y < Chunks[0].length; y++) {
				for(int blockX = 0; blockX < Chunks[x][y].blocks.length; blockX++) {
					for(int blockY = 0; blockY < Chunks[x][y].blocks[0].length; blockY++) {
						Chunks[x][y].blocks[blockX][blockY] = a;
						//a.setBounds(x, y, width, height);
					}
				}
			}
		}
	}
	
	public void fillChunk(int ChunkX, int ChunkY, Block a) {
		Chunk c = Chunks[ChunkX][ChunkY];
		
		for(int blockX = 0; blockX < c.blocks.length; blockX++) {
			for(int blockY = 0; blockY < c.blocks[0].length; blockY++) {
				c.blocks[blockX][blockY] = a;
			}
		}
	}
	
	public void setBlock(int x, int y, Block a) {
		int ChunkX = (int) Math.floor(x/chunkSize);
		int ChunkY = (int) Math.floor(y/chunkSize);
		byte BlockX = (byte) (((x/chunkSize) - ChunkX) * chunkSize);
		byte BlockY = (byte) (((y/chunkSize) - ChunkY) * chunkSize);
		
		if(Chunks[ChunkX][ChunkY] == null) {
			return;
		}
		
		Chunks[ChunkX][ChunkY].blocks[BlockX][BlockY] = a;
	}
	
	public Block getBlock(int x, int y) {
		int ChunkX = (int) Math.floor(x/chunkSize);
		int ChunkY = (int) Math.floor(y/chunkSize);
		byte BlockX = (byte) (((x/chunkSize) - ChunkX) * chunkSize);
		byte BlockY = (byte) (((y/chunkSize) - ChunkY) * chunkSize);
		
		if(Chunks[ChunkX][ChunkY] == null) {
			return null;
		}
		
		return Chunks[ChunkX][ChunkY].blocks[BlockX][BlockY];
	}
	
	public void renderBlock(int x, int y) {
		int ChunkX = (int) Math.floor(x/chunkSize);
		int ChunkY = (int) Math.floor(y/chunkSize);
		byte BlockX = (byte) (((x/chunkSize) - ChunkX) * chunkSize);
		byte BlockY = (byte) (((y/chunkSize) - ChunkY) * chunkSize);
		
		//if(Chunks[ChunkX][ChunkY] == null) {
			//return;
		//}
		
		Chunks[ChunkX][ChunkY].blocks[BlockX][BlockY].render(x * Game.TileSize, y * Game.TileSize, Game.TileSize, Game.TileSize);
	}
	
	//public void renderChunk(int ChunkX, int ChunkY) {
		//Chunks[ChunkX][ChunkY].renderBlocks();
	//}
	
	public int getLengthX() {
		return Chunks.length * chunkSize;
	}
	
	public int getLengthY() {
		return Chunks[0].length * chunkSize;
	}
	
	public String getName() {
		return name;
	}
	
	public void saveLayer(String fileName) {
		for(int x = 0;x<Chunks.length;x++) {
			for(int y = 0;y<Chunks[0].length;y++) {
				Chunks[x][y].save(fileName, x, y);
			}
		}
	}
	
	public void unloadLayer() {
		for(int x = 0;x<Chunks.length;x++) {
			for(int y = 0;y<Chunks[0].length;y++) {
				Chunks[x][y].blocks = null;
			}
		}
	}
	
	public void loadLayer(String fileName) {
		for(int x = 0;x<Chunks.length;x++) {
			for(int y = 0;y<Chunks[0].length;y++) {
				Chunks[x][y].load(fileName, x, y);
			}
		}
	}
	
	public boolean isChunkLoaded(int x, int y) {
		if(Chunks[x][y].blocks == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void loadChunk(String fileName, int x, int y) {
		Chunks[x][y].load(fileName, x, y);
	}
	
	public void unloadChunk(String fileName, int x, int y) {
		System.out.println("Saving Chunk on Layer: " + name + "," + x + "," + y);
		Chunks[x][y].save(fileName, x, y);
		Chunks[x][y].blocks = null;
	}
}
