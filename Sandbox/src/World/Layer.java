package World;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Blocks.Block;
import Core.Game;

public class Layer {
	
	private Chunk[][] Chunks;
	private Block[][] Blocks;
	private byte chunkSize;
	private String name = "";
	
	public Layer(String name, byte chunkSize, int x_size, int y_size) {
		this.name = name;
		this.chunkSize = chunkSize;
		this.Chunks = new Chunk[(int) Math.floor(x_size/chunkSize)][(int) Math.floor(y_size/chunkSize)];
		this.Blocks = new Block[x_size][y_size];
		
		for(int x = 0; x < Chunks.length; x++) {
			for(int y = 0; y < Chunks[0].length; y++) {
				Chunks[x][y] = new Chunk(Game.ChunkSize, x, y);
			}
		}
		
		fillLayer(new Block((short) -1, (byte) 0, (byte) 0, 0.0f, 0.0f, null));
	}
	
	public void fillLayer(Block a) {
		for(int x = 0; x < Blocks.length; x++) {
			for(int y = 0; y < Blocks[0].length; y++) {
				Blocks[x][y] = a;
				//a.setBounds(x, y, width, height);
			}
		}
	}
	
	public void fillChunk(int ChunkX, int ChunkY, Block a) {
		for(int l = (ChunkX * Game.ChunkSize); l < (ChunkX + Game.ChunkSize); l++) {
			for(int w = (ChunkY * Game.ChunkSize); w < (ChunkY + Game.ChunkSize); w++) {
				Blocks[l][w] = a;
			}
		}
	}
	
	public void setBlock(int x, int y, Block a) {
		Blocks[x][y] = a;
	}
	
	public Block getBlock(int x, int y) {
		return Blocks[x][y];
	}
	
	public void renderBlock(int x, int y) {
		if(isBlockLoaded(x,y)) {
			Blocks[x][y].render(x * Game.TileSize, y * Game.TileSize, Game.TileSize, Game.TileSize);
		}
	}
	
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
				saveChunk(fileName, x, y);
			}
		}
	}
	
	public void unloadLayer() {
		for(int x = 0;x<Blocks.length;x++) {
			for(int y = 0;y<Blocks[0].length;y++) {
				Blocks[x][y] = null;
			}
		}
	}
	
	public void loadLayer(String fileName) {
		for(int x = 0;x<Chunks.length;x++) {
			for(int y = 0;y<Chunks[0].length;y++) {
				loadChunk(fileName, x, y);
			}
		}
	}
	
	public boolean isChunkLoaded(int ChunkX, int ChunkY) {
		int x = ChunkX * Game.ChunkSize;
		int y = ChunkY * Game.ChunkSize;
		
		if(Blocks[x][y] == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean isBlockLoaded(int x, int y) {
		if(Blocks[x][y] == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void loadChunk(String fileName, int x, int y) {
		try {
	        FileInputStream fis = new FileInputStream(fileName + x + "_" + y + ".chunk");
	        ObjectInputStream in = new ObjectInputStream(fis);
	        for(int l = (x * Game.ChunkSize); l < ((x * Game.ChunkSize) + Game.ChunkSize); l++) {
				for(int w = (y * Game.ChunkSize); w < ((y * Game.ChunkSize) + Game.ChunkSize); w++) {
					Blocks[l][w] = (Block)in.readObject();
				}
			}
	        in.close();
	        fis.close();
	      } catch (Exception e) {
	    	  System.out.println("ERROR: Problem Loading Chunk: " + fileName);
	          System.out.println(e);
	      }
	}
	
	public void unloadChunk(String fileName, int x, int y) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName + x + "_" + y + ".chunk");
	        ObjectOutputStream out = new ObjectOutputStream(fos);
	        
			for(int l = (x * Game.ChunkSize); l < ((x * Game.ChunkSize) + Game.ChunkSize); l++) {
				for(int w = (y * Game.ChunkSize); w < ((y * Game.ChunkSize) + Game.ChunkSize); w++) {
					out.writeObject(Blocks[l][w]);
					Blocks[l][w] = null;
				}
			}
			
	        out.flush();
	        out.close();
	        fos.close();
	      } catch (IOException e) {
	          System.out.println("ERROR: Problem Saving Chunk: " + fileName);
	          System.out.println(e);
	      }
	}
	
	public void saveChunk(String fileName, int x, int y) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName + x + "_" + y + ".chunk");
	        ObjectOutputStream out = new ObjectOutputStream(fos);
	        
			for(int l = (x * Game.ChunkSize); l < ((x * Game.ChunkSize) + Game.ChunkSize); l++) {
				for(int w = (y * Game.ChunkSize); w < ((y * Game.ChunkSize) + Game.ChunkSize); w++) {
					out.writeObject(Blocks[l][w]);
				}
			}
	        out.flush();
	        out.close();
	        fos.close();
	      } catch (IOException e) {
	          System.out.println("ERROR: Problem Saving Chunk: " + fileName);
	          System.out.println(e);
	      }
	}
	
	
}
