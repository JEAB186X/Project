import java.awt.event.*;
import javax.swing.*;

public class Key extends JButton {
	
	private int frequency;
	
	public Key(String name, int f) {
		super(name);
		frequency = f;
		//Put Event Stuff Here!!!
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("My frequency is: " + frequency);
			}
		});
	}
	
}