package Listen;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import Blocks.Tile;
import Core.Game;


public class MouseListener {
	
	public static int MouseX,MouseY;
	
	public static void tick() {
		MouseX = Mouse.getX();
		MouseY = Math.abs(Mouse.getY() - Display.getHeight());
	}
	
	public static void render() {
		Tile.cursor.draw(MouseX, MouseY, (float) (10 * Game.Ratio), (float) (10 * Game.Ratio), new Color(255,255,255));
	}
}
