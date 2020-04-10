package core;

import records.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
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
	
	protected void rhythmButtonPressed(JPanel x, JPanel[] rhythmNotes, int[] rhythms, int value) {
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
				a = new ImageIcon("Notes/Images/modifiedHalfNote.png");
			}
			else if (value == 4)
			{
				a = new ImageIcon("Notes/Images/modifiedQuarterNote.jpg");
			}
			else if (value == 8)
			{
				a = new ImageIcon("Notes/Images/modifiedeigthNew.png");
			}
			else
			{
				a = new ImageIcon("Notes/Images/modifiedSixteenthNote.jpg");
			}
			JLabel label = new JLabel(a);
			rhythmNotes[i] = new JPanel();
			rhythmNotes[i].setLayout(new GridBagLayout());
			rhythmNotes[i].setBounds((450 + (50 * i)), 165, 50, 100);
			rhythmNotes[i].setBackground(Color.BLACK);
			rhythmNotes[i].setVisible(true);
			rhythmNotes[i].add(label);
			x.add(rhythmNotes[i]);
			pack();
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