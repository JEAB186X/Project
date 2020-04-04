package core;

import records.*;

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
	private NewRecordHandler Rh;
	
	
	protected JToggleButton recordButton;
	protected JToggleButton playbackButton;
	protected JComboBox instrument;
	
	
	public PianoManCore() {
		rh = new RecordHandler();
		Rh = new NewRecordHandler();
		ah = new AudioHandler(rh, Rh); 
		//Initialize according to the data in the Record Handler.
	}
	
	public void initialize() {
		instrument.setSelectedItem(rh.instrument());
	}
	
	protected void keyPressed(int index) {
		ah.playFile(index);
	}
	
	protected void openPressed()  {
		ah.startPlayingRecord();
	}

	
	protected void removePressed() {
		ah.removeFile();
	}
	
	protected void recordPressed() throws LineUnavailableException {
		 Rh.startRecording();

	}
	
	protected void saveNewRecord() {
		Rh.saving();
		
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