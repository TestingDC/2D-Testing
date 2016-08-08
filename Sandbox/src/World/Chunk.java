package World;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Blocks.Block;
import Core.Game;

public class Chunk {
	
	byte size;
	int x, y = 0;
	
	public Chunk(byte size, int x, int y) {
		this.size = size;
		this.x = x;
		this.y = y;
	}
	
	
	public void save(String fileName, int x, int y) {
		try {
	         FileOutputStream fos = new FileOutputStream(fileName + x + "_" + y + ".chunk");
	         ObjectOutputStream out = new ObjectOutputStream(fos);
	         out.writeObject(blocks);
	         out.flush();
	         out.close();
	      } catch (IOException e) {
	          System.out.println("ERROR: Problem Saving Chunk: " + fileName);
	          System.out.println(e);
	      }
	}
	
	public void load(String fileName, int x, int y) {
		try {
	        FileInputStream fis = new FileInputStream(fileName + x + "_" + y + ".chunk");
	        ObjectInputStream in = new ObjectInputStream(fis);
	        blocks = (Block[][])in.readObject();
	        in.close();
	      } catch (Exception e) {
	    	  System.out.println("ERROR: Problem Loading Chunk: " + fileName);
	          System.out.println(e);
	      }
	}
}

// TODO Replace the Chunk System entirely with a new one where all blocks are just on one array and can be loaded and unloaded from the single array using Layer Class.
// Do this instead of hundreds of little arrays because little arrays require to much calculation to swtich between chunks and world. Branch moved to Sandbox-Branch2