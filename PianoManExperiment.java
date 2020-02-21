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
	
    private JPanel jPanel1;
	
	private JButton[] bKeys;
	private JButton[] wKeys;
	
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
        jPanel1 = new javax.swing.JPanel();
		bKeys = new JButton[8];
		wKeys = new JButton[12];
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
		
		//Buttons:
		j = 0;
		for (i = 0; i < 8; i++) {
			bKeys[i] = new JButton();
			bKeys[i].setBackground(Color.BLACK);
			bKeys[i].setFont(font);
			bKeys[i].setForeground(Color.WHITE);
			bKeys[i].setText(sharpChar[i % 5] + "#");
			bKeys[i].setVerticalAlignment(SwingConstants.BOTTOM);
			final int index = i + 12;
			bKeys[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					keyPressed(index);
				}
			});
			jPanel1.add(bKeys[i]);
			bKeys[i].setBounds(120 + j, 310, 82, 210);
			j += sharpSpace[i % 5];
		}
		j = 0;
		for (i = 0; i < 12; i++) {
			wKeys[i] = new JButton();
			wKeys[i].setBackground(Color.WHITE);
			wKeys[i].setFont(font);
			wKeys[i].setText((char) ((i + 2) % 7 + 'A') + "");
			wKeys[i].setVerticalAlignment(SwingConstants.BOTTOM);
			final int index = i;
			wKeys[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					keyPressed(index);
				}
			});
			jPanel1.add(wKeys[i]);
			wKeys[i].setBounds(50 + j, 310, 108, 360);
			j += 100;
		}
		
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
	
}
