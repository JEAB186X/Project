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
		JButton b;
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
