package audio;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import records.RecordHandler;


public class AudioHandler {
	
	RecordHandler rh;
	
	public AudioHandler(RecordHandler rh) {
		this.rh = rh;
	}
	
	public void playFile(int index) {
		try {
			// this directory might change depending mine starts with /C:/Users/elvis/Documents/_________rh.instrument() 
			File music = new File("Notes/"  + rh.instrument()  + "/Note" + index + ".wav");
			
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

	//must start playing the current record in the recordhandler class.
	void startPlayingRecord(RecordHandler rh) {

	}
	// must stop playing the current record if it is playing.
	void stopPlayingRecord() {
		
	 }

}