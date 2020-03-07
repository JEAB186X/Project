package audio;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;


public class AudioHandler {
	
	public void playFile(int index, String instrument) {
		
		try {
			
			File music = new File("Notes/"  + instrument + "/Note" + index + ".wav");
			
			if (music.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);	
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				
			}
			else {
				System.out.println("Couldnt find the file");
				
			}
		}
		catch (Exception ex ) {
			ex.printStackTrace();
		}
	}
}
	
	
	
	
	
	
