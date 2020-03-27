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
		rh.setInstrument(instrument.getSelectedItem().toString());
	}
	
	protected void rhythmButtonPressed(JPanel[] rhythmNotes, int value) {
		int i = 0;
		JPanel p = new JPanel();
		while (i < 13)
		{
			if (value == 1)
			{
				ImageIcon a = new ImageIcon("Notes/Images/modifiedWholeNote.jpg");
				JLabel label = new JLabel(a);
				p.add(label);
				rhythmNotes[i] = p;
			//	rhythmNotes[i] = rhythmNotes[i].add("Notes/Images/modifiedWholeNote.jpg");
			//	rhythmNotes[i] = new ImageIcon
			}
			if (value == 2)
			{
				ImageIcon a = new ImageIcon("Notes/Images/modifiedWholeNote.jpg");
				JLabel label = new JLabel(a);
				p.add(label);
				rhythmNotes[i] = p;
			}
			if (value == 4)
			{
				ImageIcon a = new ImageIcon("Notes/Images/modifiedWholeNote.jpg");
				JLabel label = new JLabel(a);
				p.add(label);
				rhythmNotes[i] = p;
			}
			if (value == 8)
			{
				ImageIcon a = new ImageIcon("Notes/Images/modifiedWholeNote.jpg");
				JLabel label = new JLabel(a);
				p.add(label);
				rhythmNotes[i] = p;
			}
			if (value == 16)
			{
				ImageIcon a = new ImageIcon("Notes/Images/modifiedWholeNote.jpg");
				JLabel label = new JLabel(a);
				p.add(label);
				rhythmNotes[i] = p;
			}
			i++;
		}
	}
	
	/*COOL THINGS :D
		JToggleButton.setSelected(boolan);
		JToggleButton.isSelected();
		JComboBox.getSelectedItem();
		JComboBox.setSelectedItem();
	*/
	
}