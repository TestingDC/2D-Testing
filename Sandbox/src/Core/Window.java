package Core;

import static org.lwjgl.opengl.GL11.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

public class Window {

	public Window() {
		@SuppressWarnings("unused")
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		@SuppressWarnings("unused")
		DisplayMode displayMode = null;
		DisplayMode[] modes = null;

		try {
			modes = Display.getAvailableDisplayModes();
		} catch (LWJGLException e1) {
			e1.printStackTrace();
		}

		for (int i = 0; i < modes.length; i++) {
			int width = 0; int height = 0;
			
			if (modes[i].getWidth() == width && modes[i].getHeight() == height && modes[i].isFullscreenCapable()) {
				displayMode = modes[i];
			}
		}

		// Set up Display //
		try {
			Display.create(new PixelFormat(0, 16, 1));
			Display.setTitle("Rise");
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		reload();
		Mouse.setGrabbed(true); // Lock Mouse into Screen
	}


	// Getters/Setters // ******************************************** //

	public void reload() {
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

	public void setResizeable(boolean val) {
		Display.setResizable(val);
	}

	public void setTitle(String title) {
		Display.setTitle(title);
	}

	public void setFullscreen(boolean val) {
		try { Display.setFullscreen(val); reload(); } catch (LWJGLException e) {e.printStackTrace();}

	}

	public void setFullscreenBorderless(boolean val) {
		try {
			Display.destroy();
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
			Display.setFullscreen(false);
			Display.create(new PixelFormat(0, 16, 1));
			Display.setTitle("Rise");
			Display.setResizable(false);

			reload();
			Mouse.setGrabbed(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
