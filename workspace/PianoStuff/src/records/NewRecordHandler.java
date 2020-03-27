package records;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

public class NewRecordHandler {
	
	public static void main(String[] args) throws LineUnavailableException {
		
		AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
		
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);	
	
	
	    if (!AudioSystem.isLineSupported(info) ) {
			System.out.println("line is not supported");
			
		}	
		
	//	final TargetDataLine targetDataLine = null;
		
	    final TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(info);
		
	
		
		
		try {
			targetDataLine.open();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("Start recording");

	    targetDataLine.start();
		
		
	//    final targetDataLine;
		
		
		Thread stopper = new Thread(new Runnable() {
			
			public void run() {
				AudioInputStream audioStream = new AudioInputStream(targetDataLine);
				
				
				File waveFile = new File("records/recording.wav");
				
				
				try {
					AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, waveFile);
				} 
				catch (IOException e) {
					e.printStackTrace();
					
				}	
				
				
				
				
				
			}
			
		});
			
			stopper.start();
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			targetDataLine.stop();
			
			
			
			targetDataLine.close();
				
			System.out.println("Ended sound test");	
				
				
				
			
			
		}
	}
	
	
