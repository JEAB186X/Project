package core;

import records.*;


import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;


import java.awt.Color;
import javax.sound.sampled.LineUnavailableException;
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
	
	protected void keyPressed(JPanel x, int index, JCheckBox c, String[] b, JTextField[] t) {
		ah.playFile(index);
		
		
		int i = 0;
		if (c.isSelected()) 
		{
			while (b[i] != null)
			{
				i++;
				if(i > 12)
				{
					break;
				}
			}
			if (i < 13)
			{
				if (index == 0 || index == 7 || index == 11 || index == 16)
				{
					b[i] = "C";
				}
				else if (index == 1 || index == 8 || index == 12 || index == 17)
				{
					b[i] = "D";
				}
				else if (index == 2 || index == 9)
				{
					b[i] = "E";
				}
				else if (index == 3 || index == 10 | index == 13)
				{
					b[i] = "F";
				}
				else if (index == 4 || index == 14)
				{
					b[i] = "G";
				}
				else if (index == 5 || index == 15)
				{
					b[i] = "A";
				}
				else if (index == 6)
				{
					b[i] = "B";
				}
				if (index > 10 && index < 18)
				{
					b[i] += "#";
				}
				t[i] = new JTextField();
				t[i].setText(b[i]);
				x.add(t[i]);
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				pack();
				t[i].setBounds((460 + (55 * i)), 275, 28, 25);
				setSize(screenSize.width,screenSize.height);
			}
		}
		
		
	}
	
	protected void openPressed()  {
		ah.startPlayingRecord();
	}

	
	protected void removePressed() {
		ah.removeFile();
	}
	
	// problem
	protected void recordPressed() {
		 ah.startRecording();
		 	
		 
	}
//	problem
	protected void saveNewRecord() {
//		rh.setsavePressed();
		ah.save();
	}
	
	protected void instrumentSelected() {
		rh.setInstrument(instrument.getSelectedItem().toString());
	}
	
	protected void RemoveRhythm(JPanel x, JPanel[] rhythmNotes) {
		int i = 0;
		while (rhythmNotes[i] != null)
		{
			i++;
			if(i > 12)
			{
				break;
			}
		}
		if (i != 0)
		{
			i--;
			x.remove(rhythmNotes[i]);
			rhythmNotes[i] = null;
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			pack();
			setSize(screenSize.width,screenSize.height);
		}
	}
	
	protected void RemoveNote(JPanel x, String[] b, JTextField [] t) {
		int i = 0;
		while (t[i] != null)
		{
			i++;
			if(i > 12)
			{
				break;
			}
		}
		if (i != 0)
		{
			i--;
			x.remove(t[i]);
			t[i] = null;
			b[i] = null;
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			pack();
			setSize(screenSize.width,screenSize.height);
		}
	}
	
	protected void rhythmButtonPressed(JPanel x, JPanel[] rhythmNotes, int[] rhythms, int value) {
		int i = 0;
		ImageIcon a;
		while (rhythmNotes[i] != null)
		{
			i++;
			if(i > 12)
			{
				break;
			}
		}
		if (i < 13)
		{
			if (value == 1)
			{
				a = new ImageIcon("Notes/Images/clickedWholeNote.jpg");
			}
			else if (value == 2)
			{
				a = new ImageIcon("Notes/Images/clickedHalfNote.png");
			}
			else if (value == 4)
			{
				a = new ImageIcon("Notes/Images/clickedQuarterNote.jpg");
			}
			else if (value == 8)
			{
				a = new ImageIcon("Notes/Images/clickedEigthNew.png");
			}
			else
			{
				a = new ImageIcon("Notes/Images/clickedSixteenthNote.jpg");
			}
			JLabel label = new JLabel(a);
			rhythmNotes[i] = new JPanel();
			rhythmNotes[i].add(label);
			x.add(rhythmNotes[i]);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			pack();
			rhythmNotes[i].setLayout(new GridBagLayout());
			rhythmNotes[i].setBounds((450 + (55 * i)), 165, 50, 100);
			setSize(screenSize.width,screenSize.height);
			rhythms[i] = value;
		}
	}
}	