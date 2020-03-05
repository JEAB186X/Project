package Core;

import sun.audio.*;
import java.io.*;
import javax.swing.JFrame;


public class PianoManCore extends JFrame {
	
	private InputStream[] iAudio;
	
	public PianoManCore() {
		iAudio = new InputStream[20];
		int i;
		//Audio:
		for(i = 0; i < 20; i++) {
			try {
				iAudio[i] = new FileInputStream(new File("Notes/Piano/Note" + i + ".wav"));
			}
			catch(IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	protected void keyPressed(int index) {
		try {
			AudioPlayer.player.start(new AudioStream(iAudio[index]));
			iAudio[index] = new FileInputStream(new File("Notes/Piano/Note" + index + ".wav"));
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
	}
	
	protected void openPressed() {
		
	}
	
	protected void removePressed() {
		
	}
	
	protected void recordPressed() {
		
	}
	
	protected void playbackPressed() {
		
	}
	
}