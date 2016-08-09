package World;

public class Chunk {
	byte size;
	int x, y = 0;
	
	public Chunk(byte size, int x, int y) {
		this.size = size;
		this.x = x;
		this.y = y;
	}
}

// TODO Replace the Chunk System entirely with a new one where all blocks are just on one array and can be loaded and unloaded from the single array using Layer Class.
// Do this instead of hundreds of little arrays because little arrays require to much calculation to swtich between chunks and world. Branch moved to Sandbox-Branch2