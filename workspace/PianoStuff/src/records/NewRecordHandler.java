package records;

import java.io.File;
//import main.PianoMan;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;

// throws LineUnavailableException
public class NewRecordHandler {

	public static void main(String[] args) throws LineUnavailableException  {
		NewRecordHandler Rh = new NewRecordHandler();
		Rh.startRecording( );	
	}
	
	public NewRecordHandler() {
	
	}
	
	public static boolean isSupported(DataLine.Info info) {
		  if (AudioSystem.isLineSupported(info) ) {
				return true;
			}	
		return false;
	}
	
    public static String fileName() {
    	
    	System.out.println("Enter the file name");
    	
    	Scanner scan = new Scanner(System.in);
    	String filename = scan.next();
    	
    	
    	scan.close();
    	return filename;
    }
	
	public void startRecording() throws LineUnavailableException {
       
		AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);	
	
	    if (isSupported(info) ) {
	    	System.out.println("line is supported");
		}	
		
	    final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
		
		try {
			targetDataLine.open();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Start recording");

	    targetDataLine.start();
		
		
		Thread stopper = new Thread(new Runnable() {
			
			public void run() {
				AudioInputStream audioStream = new AudioInputStream(targetDataLine);
				
				
				File waveFile = new File("Recorded/" + fileName() + ".wav");
				
				
				try {
					AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, waveFile);
				} 
				catch (IOException e) {
					e.printStackTrace();
					
				}	
				
			}
			
		});
			
			stopper.start();
			
			JOptionPane.showMessageDialog(null, "Recording, Press Okay to Stop!");
			
			targetDataLine.stop();
			
			targetDataLine.close();
				
			System.out.println("Ended sound test");	
				
		}
	
	}
	
	
