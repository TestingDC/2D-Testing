package Core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;

import Blocks.Block;
import Blocks.Tile;
import Listen.MouseListener;
import World.Layer;
import World.World;


public class Game {
	
	public final static String gameFolder = "C:/Users/" + System.getProperty("user.name") + "/Documents/My Games/Game";
	public final static String saveFolderName = "Saves";
	public final static byte ChunkSize = 64;
	public final static byte TileSize = 16;
	public static double Ratio;
	
	final Window window = new Window();
	final Clock gameClock = new Clock();
	final DeltaTime time = new DeltaTime();
	public static World name;
	
	public static short DisplayXChk;
	public static short DisplayYChk;
	public static short DisplayXBlk;
	public static short DisplayYBlk;
	
	long lastFrame;
	int fps;
	long lastFPS;
	
	public static void main(String Args[]) {
		new Game();
	}
	
	public Game() {
		System.out.println("### Launching ###");
		Tile.load();
		Ratio = Display.getWidth() / Display.getHeight();
		window.setTitle("Testing");
	
		DisplayXChk = (short) (Math.ceil((Display.getWidth()/Game.TileSize) / Game.ChunkSize));
		DisplayYChk = (short) (Math.ceil((Display.getHeight()/Game.TileSize) / Game.ChunkSize));
		DisplayXBlk = (short) Math.ceil((Display.getWidth()/Game.TileSize));
		DisplayYBlk = (short) Math.ceil((Display.getHeight()/Game.TileSize));
		
		loop();
	}
	
	private void loop() {
		//gameClock.start();
		
		name = new World("newWorld", new Layer("Foreground", ChunkSize, 500, 500), new Layer("Background", ChunkSize, 500, 500));
		//name.WorldGenerate("Foreground");
		name.fillLayer("Foreground", new Block((short) 1, (byte) 0, (byte) 0, 0.0f, 0.0f, null));
		name.fillLayer("Background", new Block((short) 1, (byte) 0, (byte) 0, 0.0f, 0.0f, null));
		
		//name.WorldSave(name.worldLayers);
		//name.fillLayer("Foreground", new Block((short) 1, (byte) 0, (byte) 0, 0.0f, 0.0f, null));
		
		name.getLayer("Foreground").unloadLayer();
		name.getLayer("Background").unloadLayer();
		
		//name.LoadChunk("newWorld", 0, 0);
		//name.WorldLoad("newWorld");
		name.UpdateChunks();
		
		lastFPS = getTime();
		
		while(!Display.isCloseRequested()) {
			//if(Display.wasResized()) { }
			glClear(GL_COLOR_BUFFER_BIT);
			
			tick();
			render();
			
			updateFPS();
			
			Display.update();
			Display.sync(60);
		}
		//gameClock.interrupt();
		Display.destroy();
	}
	
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle("FPS: " + fps); 
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}

	
	private void tick() {
		time.update();
		MouseListener.tick();
		//name.UpdateChunks();
	}
	
	private void render() {
		name.WorldRender();
		MouseListener.render();
	}
}
