package Core;

import Records.*;
import sun.audio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class PianoManCore extends JFrame {
	
	protected RecordHandler rh;
	
	protected JToggleButton recordButton;
	protected JToggleButton playbackButton;
	protected JComboBox instrument;
	
	public PianoManCore() {
		rh = new RecordHandler();
	}
	
	protected void keyPressed(int index) {
		//Old Code; Please Replace
		try {AudioPlayer.player.start(new AudioStream(new FileInputStream(new File(
		"Notes/"  + instrument.getSelectedItem() + "/Note" + index + ".wav"))));}
		catch(IOException ex) {ex.printStackTrace();}
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