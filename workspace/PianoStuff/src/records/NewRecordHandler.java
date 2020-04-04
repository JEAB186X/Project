package records;

import java.io.File;
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

public class NewRecordHandler {

	private TargetDataLine line;

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

    	Scanner scan = new Scanner(System.in);

    	JOptionPane.showMessageDialog(null, "Enter the file name");

    	String filename = scan.next();

    	scan.close();
    	return filename;

    }

	public void startRecording() throws LineUnavailableException {

		AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);	

	    if (isSupported(info) ) {
	    	System.out.println("Started");
		}	
              line  = (TargetDataLine) AudioSystem.getLine(info);

		try {
			line.open();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String name = "kimara";

		line.start();

	Thread stopper = new Thread(new Runnable() {

			public void run() {
				AudioInputStream audioStream = new AudioInputStream(line);

				File waveFile = new File("Recorded/" + name + ".wav");

				try {

					AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, waveFile);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}	
			}
		});

	    	JOptionPane.showMessageDialog(null, "Press Okay to Start Recording!");
			stopper.start();

		}


	public void saving() {

		line.stop();
		line.close();
		line.flush();
		System.out.println("Ended sound test");
		
        //String h = fileName();
		
		//File old = new File("Recorded/kimara.wav");
		
		//File fresh = new File("Recorded/" + h + ".wav");
		
		//old.renameTo(fresh);
		
		//System.out.print("filed saved as" + fresh.getName());
			
			
		
		
		
	}

	

	

}

	

	