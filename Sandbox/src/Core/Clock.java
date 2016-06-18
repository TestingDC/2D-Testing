package Core;

public class Clock extends Thread {
	
	public boolean isRunning = false;
	public final short ticksPerSecond = 25;
	public long totalTicks = 0;
	
	
	
	public Clock() {
		
	}
	
	
	//---------//
	
	public void run() {
		isRunning = true;
		System.out.println("Starting Game Clock #");
		
		while(isRunning) {
			try { 
				Thread.sleep(1000/ticksPerSecond); 
			} catch (InterruptedException e) { 
				System.out.println("Thread was Interrupted Closing.. Thread Name: " + this.getName());
				isRunning = false;
			}
			totalTicks++;
		}
	}
}
