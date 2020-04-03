package audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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

	public void playFile(File file) {
		  try {
		 if (file.isFile() || file.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);	
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			}
			else {
				System.out.println("Couldnt find the file");
			}
     }
     catch ( LineUnavailableException | IOException | UnsupportedAudioFileException e) {
     	e.printStackTrace();
     }
	}
	
	
	//must start playing the current record in the recordhandler class.
	public void startPlayingRecord() {
  		 JButton open = new JButton();
	        JFileChooser fc = new JFileChooser();
	        
	        fc.setCurrentDirectory(new java.io.File("Recorded"));
	        fc.setDialogTitle("Open File");
	        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        
	        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
	            	 File file = fc.getSelectedFile();
	            	 playFile(file);
	        }    
	}
	
	
	
	// must stop playing the current record if it is playing.
	public void stopPlayingRecord() {
		
	 }

	public void removeFile() {
		 JButton open = new JButton();
		 
	        JFileChooser fc = new JFileChooser();
	        fc.setCurrentDirectory(new java.io.File("Recorded"));
	        fc.setDialogTitle("Delete File");
	        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        
	        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
	            File file = fc.getSelectedFile();
				 if (file.isFile() || file.exists()) {
					 JOptionPane.showMessageDialog(null, "Are you sure you want to delete this file!");
					 file.delete();
				}
				else {
					System.out.println("Couldnt delete this file");
				}
	        }    
	}
}