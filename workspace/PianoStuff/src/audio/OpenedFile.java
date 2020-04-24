package audio;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import records.hope;
import records.jmPlayer;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.*;

import java.text.*;

public class OpenedFile extends JFrame {

	private JPanel contentPane;
	private static Boolean start = false;
	private static Boolean stop = false;
	private static Boolean pause = false;
	private static jmPlayer jm ;
	Timer tm;
	JLabel Duration;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File file = new File("records/grace.jm");			
					OpenedFile frame = new OpenedFile(file);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OpenedFile(File file) {
		
	    jm = new jmPlayer(file);
		String word = file.getName();
		jm.showEverything();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 547, 181);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent em) {
				start = true;
				
				hope t = new hope(jm);
				t.start();
				int n = jm.getLength() / 20;
				System.out.println("Seconds " + n);
				start();
			}
			
			
			int seconds = 0;
			Timer time = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					seconds++;
					if (pause) {
						seconds = seconds - 1;
						System.out.println("");
					}
					
					if (jm.getLength() == seconds) {
						pause = true;
					}
					int n = seconds / 20;
					Duration.setText(String.valueOf(n));
				}
			};
			
			public void start() {
				time.scheduleAtFixedRate(task, 1000, 50);
			}	
						
		});
		btnNewButton.setBounds(34, 106, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Pause");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jm.pause();
				pause = true;
			}
		});
		btnNewButton_1.setBounds(156, 106, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton Resume = new JButton("Resume");
		Resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jm.resume();  
				pause = false;
			}
		});
		Resume.setBounds(279, 106, 89, 23);
		contentPane.add(Resume);
		
		JButton Stop = new JButton("Stop");
		Stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				stop = true;
				jm.stopPlaying();
				setVisible(false);
			}
		});
		Stop.setBounds(403, 106, 89, 23);
		contentPane.add(Stop);
		
		JLabel RecordName = new JLabel("Record Name");
		RecordName.setText(word);
		RecordName.setBounds(209, 49, 312, 23);
		contentPane.add(RecordName);
		
		Duration = new JLabel("Duration");
		Duration.setBounds(403, 24, 105, 14);
		contentPane.add(Duration);
		
	}
	
	public static void startPlaying() {
		if (start) {
			jm.startPlaying();
		}
	}
		
}
