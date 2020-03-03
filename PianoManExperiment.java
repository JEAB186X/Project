import sun.audio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author jerem
 * @author arvidg
 */
public class PianoManExperiment extends JFrame {
	
	private InputStream[] iAudio;
	
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
                PianoManExperiment piano = new PianoManExperiment();
				piano.setVisible(true);
				piano.setSize(1300, 700);
            }
        });
    }
	
    /**
     * Creates new form PianoMan
     */
    public PianoManExperiment() {
        JPanel jPanel1 = new JPanel();
		JPanel jPanel;
		JButton b;
		JToggleButton t;
		JComboBox<String> c;
		JTextField f;
		iAudio = new InputStream[20];
		Font font = new Font("Verdana", 1, 24);
		char[] sharpChar = {'C','D','F','G','A'};
		int[] sharpSpace = {100, 200, 100, 100, 200};
		int i;
		int j;
		
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(Color.black);
        jPanel1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 8));
        jPanel1.setLayout(null);
		//Audio:
		for(i = 0; i < 20; i++) {
			try {
				iAudio[i] = new FileInputStream(new File("Notes/Piano/Note" + i + ".wav"));
			}
			catch(IOException ex) {
				System.out.println(ex);
			}
		}
		//Black Keys:
		j = 0;
		for (i = 0; i < 8; i++) {
			b = new JButton();
			b.setBackground(Color.BLACK);
			b.setFont(font);
			b.setForeground(Color.WHITE);
			b.setText(sharpChar[i % 5] + "#");
			b.setVerticalAlignment(SwingConstants.BOTTOM);
			final int index = i + 12;
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					keyPressed(index);
				}
			});
			jPanel1.add(b);
			b.setBounds(120 + j, 310, 82, 210);
			j += sharpSpace[i % 5];
		}
		//White Keys:
		j = 0;
		for (i = 0; i < 12; i++) {
			b = new JButton();
			b.setBackground(Color.WHITE);
			b.setFont(font);
			b.setText((char) ((i + 2) % 7 + 'A') + "");
			b.setVerticalAlignment(SwingConstants.BOTTOM);
			final int index = i;
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					keyPressed(index);
				}
			});
			jPanel1.add(b);
			b.setBounds(50 + j, 310, 108, 360);
			j += 100;
		}
		//Open Button:
		b = new JButton();
		b.setText("Open File");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				openPressed();
			}
		});
		jPanel1.add(b);
		b.setBounds(30, 20, 220, 70);
		//Remove Button:
		b = new JButton();
		b.setText("Remove File");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				removePressed();
			}
		});
		jPanel1.add(b);
		b.setBounds(30, 100, 220, 60);
		//Recrord Button:
		t = new JToggleButton();
		t.setText("Record");
		t.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				recordPressed();
			}
		});
		jPanel1.add(t);
		t.setBounds(30, 170, 220, 50);
		//Change Instrument Button --------------ADD-EVENT-LISTENER-------------
		c = new JComboBox<>();
		c.setModel(new DefaultComboBoxModel<>(
			new String[] { "Piano", "SteelDrums", "Saxaphone", "Trumpet" }
		));
		
		jPanel1.add(c);
		c.setBounds(270, 30, 150, 40);
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
			}
		});
		jPanel.add(f);
		
		jPanel1.add(jPanel);
		jPanel.setBounds(520, 20, 560, 30);
		//Rhythmic Pattern Setter Buttons:
		jPanel = new JPanel();
		JButton wNote = new JButton();
		wNote.setIcon(new Icon(""));
		JButton hNote = new JButton();
		hNote.setIcon(new Icon(""));
		JButton qNote = new JButton();
		qNote.setIcon(new Icon(""));
		JButton eNote = new JButton();
		eNote.setIcon(new Icon(""));
		JButton sNote = new JButton();
		sNote.setIcon(new Icon(""));
		b.setIcon(new ImageIcon(""));
		GroupLayout jPanelLayout = new GroupLayout(jPanel);
		jPanel.setLayout(jPanelLayout);
		jPanelLayout.setHorizontalGroup(
			jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(jPanelLayout.createSequentialGroup()
				.addGap(3, 3, 3)
				.addComponent(b, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(b, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(b, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
				.addComponent(b, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addComponent(b, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
				.addContainerGap())
		);
		jPanelLayout.setVerticalGroup(
			jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
			.addGroup(jPanelLayout.createSequentialGroup()
				.addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(jPanelLayout.createSequentialGroup()
						.addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
							.addGroup(jPanelLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(b, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
							.addGroup(jPanelLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(b, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
							.addGroup(jPanelLayout.createSequentialGroup()
								.addGap(5, 5, 5)
								.addComponent(b, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
							.addGroup(jPanelLayout.createSequentialGroup()
								.addComponent(b, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
						.addGap(0, 0, Short.MAX_VALUE))
					.addComponent(b, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addContainerGap())
		);
		
		b.getAccessibleContext().setAccessibleDescription("");
		
		jPanel1.add(jPanel);
		jPanel.setBounds(520, 50, 560, 110);
		
		jPanel.setBackground(new Color(0, 0, 0));
		
		
		
        getContentPane().add(jPanel1);
        jPanel1.setBounds(-10, -10, 1570, 820);
		
        pack();
		
    }
	
	private void keyPressed(int index) {
		try {
			AudioPlayer.player.start(new AudioStream(iAudio[index]));
			iAudio[index] = new FileInputStream(new File("Notes/Piano/Note" + index + ".wav"));
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
	}
	
	private void openPressed() {
		
	}
	
	private void removePressed() {
		
	}
	
	private void recordPressed() {
		
	}
	
	
	
}
