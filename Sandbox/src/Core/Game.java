package Core;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;


public class Game {
	
	final Window window = new Window();
	final Clock gameClock = new Clock();
	final DeltaTime time = new DeltaTime();	
	
	public static void main(String Args[]) {
		new Game();
	}
	
	public Game() {
		System.out.println("### Launching... ###");
		window.setTitle("Testing");
		loop();
	}
	
	private void loop() {
		gameClock.start();
		
		while(!Display.isCloseRequested()) {
			//if(Display.wasResized()) { }

			glClear(GL_COLOR_BUFFER_BIT);
			tick();
			render();
			
			Display.update();
			Display.sync(60);
		}
		gameClock.interrupt();
		Display.destroy();
	}
	
	private void tick() {
		time.update();
	}
	
	private void render() {
		
	}
}
