package records;

public class hope extends Thread {
	
	private jmPlayer play;
	
	public hope(jmPlayer play) {
		this.play = play;
	}
		
	public void run() {
		synchronized(play) {
			play.startPlaying();
		}
	}
}
