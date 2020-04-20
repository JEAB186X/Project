package audio;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import records.NewRecordHandler;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import records.*;

public class AudioHandler {
	
	private RecordHandler rh;
//	private NewRecordHandler Nh;
	private Scanner scan;
	private  boolean so;
	private ArrayList<Note> note;
	private static boolean savePressed;
	private static boolean recordPressed;
	private int i = 0, w = 0;
	
	public static void main(String[] args) {
		RecordHandler rh = new RecordHandler();
		NewRecordHandler Nh = new NewRecordHandler();
		AudioHandler audio = new AudioHandler(rh, Nh);
		
//		audio.removeRecord();
		audio.startRecording();
		audio.save();
		audio.showEverything();
		audio.playEverything();

	}
//  TODO   Ask kamini about how to use jframe buttons silmultaneously 
//  TODO   Learn git in eclipse
//  TODO   Learn netbeans in eclipse 
//	TODO   Learn merging and related

	
	public AudioHandler(RecordHandler rh, NewRecordHandler Nh) {
		this.rh = rh;
//		this.Nh = Nh;
		scan = new Scanner(System.in);
		so = false;
		note = new ArrayList<Note>();
		savePressed = false;
		recordPressed = false;
		i = 0;
	}
	
	static int seconds = 0;
	Timer time = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
			seconds++;
//			System.out.println(seconds);
//			Duration.setText(String.valueOf(seconds));
		}
	};
	
	public void start() {
		time.scheduleAtFixedRate(task, 1000, 50);
	}
	

	
	
	public void playFile(int index) {
		try {
			// this directory might change depending mine starts with "C:/Users/elvis/Documents/Notes"  + "/Note" + index + ".wav"
			File music = new File("Notes/"  + rh.instrument()  + "/Note" + index + ".wav");
			
			if (music.exists()) {
				
				if (recordPressed && !savePressed) {
//					System.out.println("record pressed");
					
					int num = seconds - w;
					if (num == 0) {
						num = 1;
					}
					Note n = new Note(index, num);
					w = seconds;
					note.add(n);
				}
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
	
	public void playjmFile(File file) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					File file = new File("records/lal.jm");			
					OpenedFile frame = new OpenedFile(file);
					frame.setVisible(true);
//					OpenedFile.startPlaying();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
//		TODO Add JFrame
	}
	
	public void playFile(File file) {
		  try {
		 if (file.isFile() || file.exists()) {
			 
			 if (so) {
				 playjmFile(file);
			 }
			 else {
				    AudioInputStream audioInput = AudioSystem.getAudioInputStream(file);	
					Clip clip = AudioSystem.getClip();
					clip.open(audioInput);
					clip.start();
				 
			 }
				
			}
			else {
				System.out.println("Couldnt find the file");
			}
     }
     catch ( LineUnavailableException | IOException | UnsupportedAudioFileException e) {
     	e.printStackTrace();
     }
	}
	
	public void showEverything() {
		int j, i;
		//System.out.println("Current Record: " + name());
		for (i = 1; i < numRecords(); i++) {
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

	public void playEverything() {
		int i;
		
		System.out.println("\n\n\n\n" + "Current Record: " + name());
		for (i = 1; i < numRecords(); i++) {
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
					Thread.sleep(num * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
			System.out.println("\n\n\n");
		}
		
	}
	//must start playing the current record in the recordhandler class.

	public void startPlayingRecord() {
  		 JButton open = new JButton();
	        JFileChooser fc = new JFileChooser();
	        
	        fc.setCurrentDirectory(new java.io.File("records"));
	        fc.setDialogTitle("Open File");
	        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        
	        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
	            	 File file = fc.getSelectedFile();
	            	 String name = file.getAbsolutePath();
	            	
					if (name.charAt(name.length() -1) == 'v') {
	            		 so = false;
	            	 }
	            	 else if (name.charAt(name.length() -1) == 'm') {
	            		 so = true;
	            	 }
	            	 playFile(file);
	        }    
	}
	
	
	
	public void startRecording()  {
		 JOptionPane.showMessageDialog(null, "Recording starts the instant you start playing and ends when save pressed!");
		recordPressed = true;
		start();
		
	}
	
	public void pauseRecord() {
		
	 }
	
	public void save() {
		savePressed = true;
		
		 if (savePressed) {
			
			SaveNote s = new SaveNote(note, rh.instrument());
			s.save();
			JOptionPane.showMessageDialog(null,"File Saved!");
			note.clear();
			savePressed = false;
			recordPressed = false;
			
		}
	}

	public boolean isRecording() {
		return rh.isRecording();
	}
	
	public void removeFile() {
		 JButton open = new JButton();
		 
	        JFileChooser fc = new JFileChooser();
	        fc.setCurrentDirectory(new java.io.File("records"));
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
//	    TODO	where to add the J-asker record name thing
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
//		TODO where ill need a j asker to get the length
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
		rh.addPitch(p);
	}
	
   private void changeRecord(String name) {
		rh.changeRecord(name);
	}	

	private String name() {
		return rh.name();
	}


	private String instrument() {
		return rh.instrument();
	}


	private int nextPitch() {
		return rh.nextPitch();
	}


	private void startRecalling() {
		rh.startRecalling();
		
	}
}