package core;

import records.*;
import sun.audio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
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