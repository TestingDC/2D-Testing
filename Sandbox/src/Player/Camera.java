package Player;

public class Camera {

	public static boolean mouseAttach = false;
	public static int CameraX, CameraY = 0;
	private static float lerp = 0.1f;
	
	public static void updateCam() {
		//CameraX += ((Mouse.getX() - (CameraX + Display.getWidth()/2)) * lerp);
		//CameraY += ((Math.abs(Mouse.getY() - Display.getHeight()) - (CameraY + Display.getWidth()/2)) * lerp);

		//CameraX += ((Core.Character.xLoc - (CameraX + Display.getWidth()/2)) * lerp);
		//CameraY += ((Core.Character.yLoc - (CameraY + Display.getHeight()/2)) * lerp);
	}
	
	public static void resetCam() {
		CameraX = 0; CameraY = 0;
		mouseAttach = false;
	}
	
	public static void moveCam(int x, int y) {
		CameraX = x; CameraY = y;
	}
	
}
