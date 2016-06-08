package Core;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;


public class Game {
	
	Window window = new Window();
	
	
	
	public static void main(String Args[]) {
		new Game();
	}
	
	
	public Game() {
		System.out.println("### Launching...");
		loop();
	}
	
	private void loop() {
		while(!Display.isCloseRequested()) {
			//if(Display.wasResized()) { }

			glClear(GL_COLOR_BUFFER_BIT);
			tick();
			render();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	// ------------------------------------------------------------------------- //
	
	
	private void tick() {
		
	}
	
	private void render() {
		
	}
	
	
	
}
