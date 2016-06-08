package Core;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.PixelFormat;

public class Window {
	
	public Window() {
		try {
			Display.create(new PixelFormat(0, 16, 1));
			Display.setTitle("Rise");
			Mouse.setGrabbed(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		glEnable(GL_TEXTURE_2D);
		glDisable(GL_DEPTH_TEST);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), Display.getHeight(), 0, -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glEnable(GL_COLOR_MATERIAL);
	}

	
	

	// Getters/Setters // ******************************************** //

	public void setResizeable(boolean val) {
		Display.setResizable(val);
	}

	public void setTitle(String title) {
		Display.setTitle(title);
	}

	/*public void setFullscreen(boolean val) {
		try { Display.setFullscreen(val); } catch (LWJGLException e) { e.printStackTrace(); }

	}

	public void setFullscreenBorderless(boolean val) {
		try {
			Display.destroy();
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
			Display.setFullscreen(false);
			Display.create(new PixelFormat(0, 16, 1));
			Display.setTitle("Rise");
			Display.setResizable(false);

			Mouse.setGrabbed(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
