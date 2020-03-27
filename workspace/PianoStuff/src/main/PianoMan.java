package main;

import core.PianoManCore;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author jerem
 * @author arvidg
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
    @SuppressWarnings("unchecked")
    public PianoMan() {
        JPanel jPanelMain = new JPanel();
		JPanel jPanel;
		JPanel jp;
		JButton b;
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
					keyPressed(index);
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
					keyPressed(index);
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
		
		//Remove Button:
		b = new JButton();
		b.setText("Remove File");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removePressed();
		}});
		jPanelMain.add(b);
		b.setBounds(30, 100, 220, 60);
		
		//Recrord Toggle Button:
		recordButton = new JToggleButton();
		recordButton.setText("Record");
		recordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				recordPressed();
		}});
		jPanelMain.add(recordButton);
		recordButton.setBounds(30, 170, 220, 50);
		
		//Playback Toggle Button:
		playbackButton = new JToggleButton();
		playbackButton.setText("Playback");
		playbackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				playbackPressed();
		}});
		jPanelMain.add(playbackButton);
		playbackButton.setBounds(30, 230, 220, 60);
		
		//Change Instrument Combo Button
		instrument = new JComboBox<>();
		instrument.setModel(new DefaultComboBoxModel<>(
			new String[] { "Piano", "SteelDrums", "Saxaphone", "Trumpet" }
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
		jPanel.setBackground(new Color(0, 0, 0));
		//Notes:
		JPanel rhythmNotes[] = new JPanel[13];
	/*	for (i = 0; i < 13; i++) {
			rhythmNotes[i] = new JPanel();
			rhythmNotes[i].setBackground(new Color(0, 0, 0));
			rhythmNotes[i].setPreferredSize(new Dimension(50, 100));
			jPanel.add(rhythmNotes[i]);
		}*/
		jPanelMain.add(jPanel);
		jPanel.setBounds(430, 180, 730, 110);
		
		//End:	
        getContentPane().add(jPanelMain);
        jPanelMain.setBounds(-10, -10, 1570, 820);
		
        pack();
		
		//Rhythmic Pattern Setter Buttons:
		jPanel = new JPanel();
		rhythmButtonPressed(rhythmNotes, 1);
		JButton wNote = new JButton();wNote.setIcon(new ImageIcon("Notes/Images/modifiedWholeNote.jpg"));wNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(rhythmNotes, 1);}});
		System.out.println(rhythmNotes[0]);
		JButton hNote = new JButton();hNote.setIcon(new ImageIcon("Notes/Images/modifiedHalfnote.png"));hNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(rhythmNotes, 2);}});
		JButton qNote = new JButton();qNote.setIcon(new ImageIcon("Notes/Images/modifiedQuarterNote.jpg"));qNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(rhythmNotes, 4);}});
		JButton eNote = new JButton();eNote.setIcon(new ImageIcon("Notes/Images/modifiedeigthNew.png"));eNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(rhythmNotes, 8);}});
		JButton sNote = new JButton();sNote.setIcon(new ImageIcon("Notes/Images/modifiedSixteenthNote.jpg"));sNote.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent evt){rhythmButtonPressed(rhythmNotes, 16);}});
		GroupLayout jPanelLayout = new GroupLayout(jPanel);jPanel.setLayout(jPanelLayout);//do not question
		jPanelLayout.setHorizontalGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGap(3, 3, 3).addComponent(wNote, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(qNote, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(eNote, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(sNote, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(hNote, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE).addContainerGap()));
		jPanelLayout.setVerticalGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addComponent(eNote, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addComponent(qNote, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)).addGroup(jPanelLayout.createSequentialGroup().addGap(5, 5, 5).addComponent(wNote, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addComponent(sNote, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))).addGap(0, 0, Short.MAX_VALUE)).addComponent(hNote, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap()));
		sNote.getAccessibleContext().setAccessibleDescription("");
		
		jPanelMain.add(jPanel);jPanel.setBounds(520, 50, 560, 110);
		/*	Seems that the display of the rhythmic patterns should be next.
			They can be found in the file labeled "buffer.txt"
			They don't appear to be serving any purpose as is.*/
    }
	
}
