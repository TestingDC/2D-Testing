package Core;

import org.lwjgl.Sys;

public class DeltaTime {
	
	private boolean paused = false;
	private long lastFrame, timePassed;
	private float delta = 0, gameSpeed = 1;
	
	public DeltaTime() {
		lastFrame = getTime();
	}
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return (int) (delta * 0.01);
	}
	
	public float Delta() {
		if(paused) {
			return 0;
		} else {
			return delta * gameSpeed;
		}
	}
	
	public float getTimePassed() {
		return timePassed;
	}
	
	public float gameSpeed() {
		return gameSpeed;
	}
	
	public void update() {
		delta = getDelta();
		timePassed += delta;
	}
}
