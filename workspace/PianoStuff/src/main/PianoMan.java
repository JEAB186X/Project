package main;

import core.PianoManCore;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;
/**
 *
 * @author jerem
 * @author arvidg
 * @author elvis
 * @author brandon
 */
public class PianoMan extends PianoManCore {
	
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
		catch (Exception ex) {
			System.out.println(ex);
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                PianoMan piano = new PianoMan();
				piano.setVisible(true);
				piano.setSize(1200, 700);
            }
        });
    }
	
    /**
     * Creates new form PianoMan
     */
    
  //Notes:
  	JPanel rhythmNotes[] = new JPanel[13];
  	int rhythms[] = new int[13];
  	String notesRhythm[] = new String[13];
  	JTextField box[] = new JTextField[13];
  	int notes[] = new int[13];
    
    @SuppressWarnings("unchecked")
    public PianoMan() {
        JPanel jPanelMain = new JPanel();
		JPanel jPanel;
		JPanel jp;
		JButton b;
		JCheckBox c;
		JTextField f;
		Font font = new Font("Verdana", 1, 24);
		char[] sharpChar = {'C','D','F','G','A'};
		int[] sharpSpace = {100, 200, 100, 100, 200};
		int i;
		int j;
		
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanelMain.setBackground(Color.black);
        jPanelMain.setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
        jPanelMain.setLayout(null);
		
      //Notes Under Rhythm
      	c = new JCheckBox("Notes Under Rhythms");
      	c.setForeground(Color.WHITE);
      	/*	c.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent evt) {
      				
      			NotesPressed(jPanelMain, rhythms);
      		}});*/
      	jPanelMain.add(c);
      	c.setBounds(270, 195, 150, 60);
        
		//Black Keys:
		j = 0;
		for (i = 0; i < 7; i++) {
			b = new JButton();
			b.setBackground(Color.BLACK);
			b.setFont(font);
			b.setForeground(Color.WHITE);
			b.setText(sharpChar[i % 5] + "#");
			b.setVerticalAlignment(SwingConstants.BOTTOM);
			final int index = i + 11;
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					keyPressed(jPanelMain, index, c, notesRhythm, box, notes);
			}});
			jPanelMain.add(b);
			b.setBounds(120 + j, 310, 82, 210);
			j += sharpSpace[i % 5];
		}
		
		//White Keys:
		j = 0;
		for (i = 0; i < 11; i++) {
			b = new JButton();
			b.setBackground(Color.WHITE);
			b.setFont(font);
			b.setText((char) ((i + 2) % 7 + 'A') + "");
			b.setVerticalAlignment(SwingConstants.BOTTOM);
			final int index = i;
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					keyPressed(jPanelMain, index, c, notesRhythm, box, notes);
			}});
			jPanelMain.add(b);
			b.setBounds(50 + j, 310, 108, 360);
			j += 100;
		}
		
		//Open Button:
		b = new JButton();
		b.setText("Open File");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				openPressed();
		}});
		jPanelMain.add(b);
		b.setBounds(30, 20, 220, 70);
		
		JLabel Duration = new JLabel("T");
		Duration.setBounds(1060, 5, 1060, 70);
		jPanelMain.add(Duration);
		
		//Remove Button:
		b = new JButton();
		b.setText("Remove File");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removePressed();
		}});
		jPanelMain.add(b);
		b.setBounds(30, 100, 220, 60);
		
		//Save Button:
		b = new JButton();
		b.setText("Save");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				saveNewRecord();
			}});
		jPanelMain.add(b);
		b.setBounds(270, 75, 150, 60);
		
		//Remove Rhythms Button:
		b = new JButton();
		b.setText("Remove Rhythm");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				RemoveRhythm(jPanelMain, rhythmNotes, rhythms);
			}});
		jPanelMain.add(b);
		b.setBounds(270, 140, 150, 30);
		
		//Remove Note Button:
		b = new JButton();
		b.setText("Remove Note");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					
				RemoveNote(jPanelMain, notesRhythm, box);
			}});
		jPanelMain.add(b);
		b.setBounds(270, 175, 150, 30);
		
		//Play Selected Notes/Rhythms
		b = new JButton();
		b.setText("Play Selected Notes/Rhythms");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
					
				try {
					PlaybackSelected(notes, rhythms);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}});
		jPanelMain.add(b);
		b.setBounds(245, 240, 200, 60);
		
		//Record Toggle Button:
		recordButton = new JToggleButton();
		recordButton.setText("Record");
		recordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				recordPressed();
				start();
		}
			int seconds = 0;
			Timer time = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					seconds++;
					int n = seconds / 20;
					Duration.setText(String.valueOf(n));
				}
			};
			
			public void start() {
				time.scheduleAtFixedRate(task, 1000, 50);
			}
		});
		jPanelMain.add(recordButton);
		recordButton.setBounds(30, 170, 220, 50);
		
		//Change Instrument Combo Button
		instrument = new JComboBox<>();
		instrument.setModel(new DefaultComboBoxModel<>(
			new String[] { "Piano", "SteelDrums"}
		));
		instrument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				instrumentSelected();
		}});
		jPanelMain.add(instrument);
		instrument.setBounds(270, 30, 150, 40);
		
		//jPanel and Text Fields:
		jPanel = new JPanel();
		jPanel.setBackground(new Color(204, 204, 204));
		f = new JTextField();
		f.setBackground(new Color(204, 204, 204));
		f.setText("Enter up to 13");
		f.setBorder(null);
		jPanel.add(f);
		f = new JTextField();
		f.setBackground(new Color(204, 204, 204));
		f.setText("Notes");
		f.setBorder(null);
		f.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.out.println("Think of a cool method name");
		}});
		jPanel.add(f);
		jPanelMain.add(jPanel);
		jPanel.setBounds(520, 20, 560, 30);
		
		//Note Buttons:
		jPanel = new JPanel();
		
		//End:	
        getContentPane().add(jPanelMain);
        jPanelMain.setBounds(-10, -10, 1570, 820);
		
        pack();
        
		//Rhythmic Pattern Setter Buttons:
		jPanel = new JPanel();

		JButton wNote = new JButton();wNote.setIcon(new ImageIcon("Notes/Images/modifiedWholeNote.jpg"));wNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(jPanelMain, rhythmNotes, rhythms, 1);}});
		JButton hNote = new JButton();hNote.setIcon(new ImageIcon("Notes/Images/modifiedHalfnote.png"));hNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(jPanelMain, rhythmNotes, rhythms, 2);}});
		JButton qNote = new JButton();qNote.setIcon(new ImageIcon("Notes/Images/modifiedQuarterNote.jpg"));qNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(jPanelMain, rhythmNotes, rhythms, 4);}});
		JButton eNote = new JButton();eNote.setIcon(new ImageIcon("Notes/Images/modifiedeigthNew.png"));eNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(jPanelMain, rhythmNotes, rhythms, 8);}});
		JButton sNote = new JButton();sNote.setIcon(new ImageIcon("Notes/Images/modifiedSixteenthNote.jpg"));sNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(jPanelMain, rhythmNotes, rhythms, 16);}});
		GroupLayout jPanelLayout = new GroupLayout(jPanel);jPanel.setLayout(jPanelLayout);//do not question
		jPanelLayout.setHorizontalGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGap(3, 3, 3).addComponent(wNote, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(hNote, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(qNote, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(eNote, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(sNote, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		jPanelLayout.setVerticalGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addComponent(wNote, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addComponent(hNote, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)).addGroup(jPanelLayout.createSequentialGroup().addGap(5, 5, 5).addComponent(qNote, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addComponent(eNote, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))).addGap(0, 0, Short.MAX_VALUE)).addComponent(sNote, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
		sNote.getAccessibleContext().setAccessibleDescription("");
		
		jPanelMain.add(jPanel);jPanel.setBounds(520, 50, 560, 110);

		//initialize();
    }
	
}
