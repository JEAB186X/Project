package core;

import records.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import audio.AudioHandler;

public class PianoManCore extends JFrame {
	
	private RecordHandler rh;
	private AudioHandler ah;
	
	protected JToggleButton recordButton;
	protected JToggleButton playbackButton;
	protected JComboBox instrument;
	
	
	public PianoManCore() {
		rh = new RecordHandler();
		ah = new AudioHandler(rh); 
		//Initialize according to the data in the Record Handler.
	}
	
	public void initialize() {
		instrument.setSelectedItem(rh.instrument());
	}
	
	protected void keyPressed(int index) {
		ah.playFile(index);
	}
	
	protected void openPressed()  {
		 JButton open = new JButton();
	        JFileChooser fc = new JFileChooser();
	        
	        fc.setCurrentDirectory(new java.io.File("Recorded"));
	        fc.setDialogTitle("Open File");
	        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        
	        
	        if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
	            
	        	
	        	   
	            try {
	            	 File file = fc.getSelectedFile();
	            	

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
	}
	
	protected void removePressed() {
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
	
	protected void recordPressed() {

	}
	
	protected void playbackPressed() {
		
	}
	
	protected void instrumentSelected() {
		rh.setInstrument(instrument.getSelectedItem().toString());
	}
	
	protected void rhythmButtonPressed(JPanel[] rhythmNotes, int[] rhythms, int value) {
		int i = 0;
		ImageIcon a;
		while (rhythmNotes[i] != null)
		{
			i++;
		}
		if (i < 13)
		{
			if (value == 1)
			{
				a = new ImageIcon("Notes/Images/modifiedWholeNote.jpg");
			//	rhythmNotes[i] = rhythmNotes[i].add("Notes/Images/modifiedWholeNote.jpg");
			//	rhythmNotes[i] = new ImageIcon
			}
			else if (value == 2)
			{
				a = new ImageIcon("Notes/Images/modifiedHalfNote.jpg");
			}
			else if (value == 4)
			{
				a = new ImageIcon("Notes/Images/modifiedQuarterNote.jpg");
			}
			else if (value == 8)
			{
				a = new ImageIcon("Notes/Images/modifiedeigthNew.jpg");
			}
			else
			{
				a = new ImageIcon("Notes/Images/modifiedSixteenthNote.jpg");
			}
			JLabel label = new JLabel(a);
			label.setLayout(new BorderLayout());
			rhythmNotes[i] = new JPanel();
			rhythmNotes[i].add(label);
			rhythmNotes[i].setVisible(true);
			rhythms[i] = value;
		}
	}
	
	/*COOL THINGS :D
		JToggleButton.setSelected(boolan);
		JToggleButton.isSelected();
		JComboBox.getSelectedItem();
		JComboBox.setSelectedItem();
	*/
	
}