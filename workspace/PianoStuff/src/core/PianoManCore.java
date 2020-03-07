package core;

import records.*;
import sun.audio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import audio.AudioHandler;

public class PianoManCore extends JFrame {
	
	protected RecordHandler rh;
	
	protected JToggleButton recordButton;
	protected JToggleButton playbackButton;
	protected JComboBox instrument;
	
	
	public PianoManCore() {
		rh = new RecordHandler();
	}
	
	protected void keyPressed(int index) {
		//Old Code; Replaced
		
		String device = (String) instrument.getSelectedItem();
				
		AudioHandler musicObject = new AudioHandler(); 
		musicObject.playFile(index, device);
		
	}
	
	protected void openPressed() {
		
	}
	
	protected void removePressed() {
		
	}
	
	protected void recordPressed() {
		
	}
	
	protected void playbackPressed() {
		
	}
	
	protected void instrumentSelected() {
		
	}
	
	protected void rhythmButtonPressed(int value) {
		
	}
	
	/*COOL THINGS :D
		JToggleButton.setSelected(boolan);
		JToggleButton.isSelected(boolean);
		JComboBox.getSelectedItem();
	*/
	
}