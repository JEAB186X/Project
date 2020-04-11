package audio;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import records.NewRecordHandler;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import records.RecordHandler;


public class AudioHandler {
	
	private RecordHandler rh;
	private NewRecordHandler Nh;
	private Scanner scan;
	private static int in;
	
	public static void main(String[] args) {
		RecordHandler rh = new RecordHandler();
		NewRecordHandler Nh = new NewRecordHandler();
		AudioHandler audio = new AudioHandler(rh, Nh);
		
		
		int i = 0;
		int j = 0;
		
		
	//	audio.removeRecord();
		
		
		/*
		
		audio.addRecord();

		audio.setLength();
		
		
		
		audio.startRecording();
		
		if(audio.isRecording()) {
			System.out.println("recording Started");
		}
		else {
			System.out.println("Didnt start recording");
		}
		while (audio.isRecording()) {
			//audio.setRhythm(i, in);
			
			audio.addPitch(in);
		
	
		}
	
	
		System.out.println("Stoppped recording");
		
		
		*/
		//audio.save();

		audio.showEverything();
		
		audio.playEverything();
		
		//audio.startPlayingRecord();
		//audio.playFile("Records.jm");
		
		
	}
	public void showEverything() {
		int j, i;
		//System.out.println("Current Record: " + name());
		for (i = 0; i < numRecords(); i++) {
			changeRecord(rh.name(i));
			System.out.println("Record Name: " + name());
			System.out.println("Instrument: " + instrument());
			System.out.println("Tempo: " + tempo());
			startRecalling();
			j = 0;
			while (rh.isRecalling()) {
				System.out.println(j + " : " + nextPitch() + " : " + 	thisRhythm());
				j++;
			}
			System.out.println("\n\n\n");
		}
	}
	public void playCurrent() {
		
	}
	public void playEverything() {
		int i;
		
		System.out.println("\n\n\n\n" + "Current Record: " + name());
		for (i = 0; i < numRecords(); i++) {
			changeRecord(rh.name(i));
			System.out.println("Record Name: " + name());
			System.out.println("Instrument: " + instrument());
			System.out.println("Tempo: " + tempo());
			startRecalling();

			while (rh.isRecalling()) {
				//System.out.println(j + " : " + bh.nextPitch() + " : " + 	bh.thisRhythm());
				
				playFile(nextPitch());
				
				try {
					final int num = thisRhythm();
					Thread.sleep(num);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			System.out.println("\n\n\n");
		}
		
	}
	
	private void changeRecord(String name) {
		// TODO Auto-generated method stub
		
		rh.changeRecord(name);
		
	}	


	private String name() {
		// TODO Auto-generated method stub
		return rh.name();
	}


	private String instrument() {
		// TODO Auto-generated method stub
		return rh.instrument();
	}


	private int nextPitch() {
		// TODO Auto-generated method stub
		return rh.nextPitch();
	}


	private void startRecalling() {
		// TODO Auto-generated method stub
		rh.startRecalling();
		
	}


	public AudioHandler(RecordHandler rh, NewRecordHandler Nh) {
		this.rh = rh;
		this.Nh = Nh;
		scan = new Scanner(System.in);
		in = 0;
	}
	
	public void playFile(int index) {
		in = index;
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
	
	//useful for the old code only
	public void playFile(String index) {
		try {
			// this directory might change depending mine starts with / /C:/Users/elvis/Documents/ Notes/" + "Piano"  + "/" + index
			File music = new File("records/" + index);
			
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
	/*
	 * Actual
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
	
	*/
	
	public void startPlayingRecord() {
		//rh.load();
		
		
		
	}
	
	
	
	//public void startRecording() throws LineUnavailableException {
		//Nh.startRecording();
	//}
	
	public void startRecording()  {
		rh.startRecording();
	}

	// must stop playing the current record if it is playing.
	public void pauseRecord() {
		
	 }
	
	public void save() {
		rh.save();
	}

	public boolean isRecording() {
		return rh.isRecording();
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
	
	public void addRecord() {
		System.out.println("Enter Record name");
		String recordName = scan.next();
		
		rh.addRecord(recordName);
		
	}
	
	public void changeRecord() {
		System.out.println("Enter Another record name");
		String recordName = scan.next();
		
		rh.changeRecord(recordName);
		
	}
	
	
	public boolean isRecalling() {
		
		return rh.isRecalling();
	}
	
	public int thisRhythm() {
		return rh.thisRhythm();
	}
	
	public int getRhythm(int index) {
		return rh.getRhythm(index);
		
	}
	public int tempo() {
		return rh.tempo();
	}
	
	public void setTempo() {

		System.out.println("Enter Required tempo");
		int p = scan.nextInt();
		rh.setTempo(p);
	}
	
	public void setRhythm(int nam, int num) {

		rh.setRhythm(nam, num);
	}
	public void setPitch(int nam, int num) {

		rh.setPitch(nam, num);
	}
	
	
	public void setLength() {

		System.out.println("Enter set length");
		int p = scan.nextInt();
		rh.setLength(p);
	}
	
	public void setInstrument(String device) {

		rh.setInstrument(device);
	}
	
	public void removeRecord() {
		
		System.out.println("Enter record to be removed");
		String recordName = scan.next();
		

		rh.removeRecord(recordName);
	}
	
	public int numRecords() {

		return rh.numRecords();
	}
	
	public void addPitch(int p) {

	//	System.out.println("Enter Required pitch");
		//int p = scan.nextInt();
		
		rh.addPitch(p);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}