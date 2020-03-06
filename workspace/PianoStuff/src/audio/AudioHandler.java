package audio;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;


public class AudioHandler {
	
	public static void main(String args[]) {
		
		
		
		
		int index = 2;
		
		
		AudioHandler musicObject = new AudioHandler(); 
		musicObject.playFile(index);
		
		
		
		
		
		
		
		
	}
	protected JComboBox instrument;

	public void playFile(int index) {
		
		try {
			
			
			File music = new File("Notes/"  + instrument.getSelectedItem() + "/Note" + index + ".wav");
			
			
			if (music.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
				
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				
				
				JOptionPane.showMessageDialog(null, "play okay to end the play");
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
