import java.awt.event.*;
import javax.swing.*;

public class Main {
	
	public static void main(String args[]) {
		int i;
		JFrame f = new JFrame("Project Name");
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		//Key: a button with a frequency and instructions.
		Key k1 = new Key(":D", 1);
		k1.setBounds(50, 100, 95, 30);
		f.add(k1);
		Key k2 = new Key(":D", 2);
		k2.setBounds(50, 150, 95, 30);
		f.add(k2);
		f.setSize(800, 400);
		f.setLayout(null);
		f.setVisible(true);
	}
	
}