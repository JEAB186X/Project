package records;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SaveNote {

	private ArrayList<Note> notes;
	private Scanner scan;
	private String name;
	private String instrument;
	
	public SaveNote(ArrayList<Note> note, String device) {
		notes = note;
		scan = new Scanner(System.in);
		name = "";
		instrument = device;
	}
	
	
	
	private void getName() {
		System.out.println("Please enter record name");
		name = scan.next();
		
	}
	
	public void save() {

		getName();
		int i;
		int j;
		try {
			DataOutputStream stream = new DataOutputStream(
				new BufferedOutputStream(
					new FileOutputStream("records/" + name + ".jm")));
//			numRecords()
			stream.writeInt(1);
			for (i = 0; i < 1; i++) {
				for (j = 0; j < name.length(); j++) {
					stream.writeChar(name.charAt(j));
				}
				stream.writeChar('\0');
				for (j = 0; j < instrument.length(); j++) {
					stream.writeChar(instrument.charAt(j));
				}
				stream.writeChar('\0');
				stream.writeInt(60);
				stream.writeInt(notes.size());
				for (j = 0; j < notes.size(); j++) {
					stream.writeInt(notes.get(j).pitch);
					stream.writeInt(notes.get(j).rhythm);
				}
			}
			stream.close();
		}
		catch (IOException e) {
			System.out.println("2: " + e);
		}
	
		
	}
}
