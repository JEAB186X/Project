package records;


	

import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * This class loads, saves, and manages all records.
 * <p>
 * Overview of a record:
 * <p>
 * Each record consists of a name, length, and a set of notes, each with a pitch and rhythm.
 * The length corrosponds to how many notes are in a record.
 * The value of the pitch corrosponds to the frequency of a note: 1 is the lowest value, 13 is the highest value.
 * The "rhythm" is an inverse measure of durration: 4 means it the note is a quarter note, 1 means it is a whole note, etc.
 * 
 * @author Arvid Gustafson : arvidg@iastate.edu
 * @author Elvis Kimara: ekimara@iastate.edu
 */



//pausse
// stop

public class jmPlayer {
	
	private ArrayList<String> nameList;
	private ArrayList<String> instList;
	private ArrayList<Integer> tempoList;
	private ArrayList<Note[]> notes;
	
	private int saveIndex;
	private int recallIndex;
	
	private boolean recalling;
	
	/**
	 * For Testing purposes. Please remove only once the project is complete.
	 * 
	 * @param args[] The arguments that can be passed into the program when prompted to run.
	 */
	public static void main(String args[]) {

		File file = new File("records/kim.jm");
		jmPlayer jm = new jmPlayer(file);
		
		jm.showEverything();
		jm.startPlaying();

	}
	
	/**
	 * When constructed, all records are loaded from the Records.jm file.
	 */
	public jmPlayer(File name) {
		nameList = new ArrayList<String>();
		instList = new ArrayList<String>();
		tempoList = new ArrayList<Integer>();
		notes = new ArrayList<Note[]>();
		load(name);
		saveIndex = 0;
		recalling = false;
	}
	public void showEverything() {
		int j, i;
		//System.out.println("Current Record: " + name());
		for (i = 1; i < numRecords(); i++) {
			changeRecord(name(i));
			System.out.println("Record Name: " + name());
			System.out.println("Instrument: " + instrument());
			System.out.println("Tempo: " + tempo());
			startRecalling();
			j = 0;
			while (isRecalling()) {
				System.out.println(j + " : " + nextPitch() + " : " + 	thisRhythm());
				j++;
			}
			System.out.println("\n\n\n");
		}
	}
	
	public void startPlaying() {
        int i;
		
		System.out.println("\n\n\n\n" + "Current Record: " + name());
		for (i = 1; i < numRecords(); i++) {
			changeRecord(name(i));
			System.out.println("Record Name: " + name());
			System.out.println("Instrument: " + instrument());
			System.out.println("Tempo: " + tempo());
			startRecalling();

			while (isRecalling()) {
				//System.out.println(j + " : " + bh.nextPitch() + " : " + 	bh.thisRhythm());
				
				playFile(nextPitch());
				
				try {
					 int num = thisRhythm();
					Thread.sleep((num -3) * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
			System.out.println("\n\n\n");
		}
		
	}
	
	public void playFile(int index) {
		try {
			// this directory might change depending mine starts with /C:/Users/elvis/Documents/_________rh.instrument() 
			File music = new File("Notes/"  + instrument()  + "/Note" + index + ".wav");
			
			if (music.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);	
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
			}
			else {
				System.out.println("Couldnt find the file");
			}
		}
		catch (Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	private void load(File word) {
		try {
			DataInputStream stream = new DataInputStream(
				new FileInputStream(word));
			int i;
			int j;
			String name;
			int numRecords = stream.readInt();
			for (i = 0; i < numRecords; i++) {
				name = "";
				j = stream.readChar();
				while (j != '\0') {
					name = name + (char) j;
					j = stream.readChar();
				}
				addRecord(name);
				name = "";
				j = stream.readChar();
				while (j != '\0') {
					name = name + (char) j;
					j = stream.readChar();
				}
				setInstrument(name);
				setTempo(stream.readInt());
				setLength(i, stream.readInt());
				for (j = 0; j < length(i); j++) {
					notes.get(i)[j].set(stream.readInt(), stream.readInt());
				}
			}
			stream.close();
		}
		catch (IOException e) {
			System.out.println("1: ");
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a record. The record is not saved in the Records.jm file until save() is called.
	 * 
	 * @param name The name of the record that will be added.
	 */
	public void addRecord(String name) {
		if (getIndex(name) != -1) {
			System.out.println("Error: record already exists");
			return;
		}
		saveIndex = numRecords();
		nameList.add(name);
		instList.add("Piano");
		tempoList.add(60);
		notes.add(null);
		setLength(8);
	}
	
	/**
	 * Changes the current record to a different one.
	 * 
	 * @param name The name of the record that will be the current record.
	 */

	public void changeRecord(String name) {
		saveIndex = getIndex(name);
		if (saveIndex == -1) {
			saveIndex = 0;
			System.out.println("Erorr: Record DNE");
		}
		else {
		}
	}
	
	/**
	 * Removes a record. This change is not saved in the records.jm file until save() is called.
	 * @param name The name of the record that will be removed.
	 */

	
	
	/**
	 * Returns the total number of records there are.
	 * 
	 * @return The number of records there currently are.
	 */
	
	public int numRecords() {
		return nameList.size();
	}
	
	/**
	 * Returns the name of a record at a given index.
	 * Helpful for displaying the names of all records.
	 * 
	 * @param index The index of a record.
	 * @return The name of the record at the given index.
	 */
	public String name(int index) {
		return nameList.get(index);
	}
	
	/**
	 * Returns the name of the current record.
	 * 
	 * @return The name of the current record.
	 */
	public String name() {
		if (numRecords() == 0) {
			return "(none)";
		}
		return nameList.get(saveIndex);
	}
	

	
	/**
	 * Returns the instrument the current record uses.
	 * 
	 * @return The instrument of the current record.
	 */
	
	public String instrument() {
		if (numRecords() == 0) {
			return "(none)";
		}
		return instList.get(saveIndex);
	}
	

	/**
	 * Returns the tempo of a given record given its index.
	 * 
	 * @return The tempo of an index.
	 */
	public int tempo() {
		if (numRecords() == 0) {
			return -1;
		}
		return tempoList.get(saveIndex);
	}
	
	/**
	 * Returns the length of the current record.
	 * Basically, it returns how many notes are in the current record.
	 * 
	 * @return The length of the current record.
	 */
	public int length() {
		if (notes.get(saveIndex) == null) {
			return 0;
		}
		return notes.get(saveIndex).length;
	}
	
	/**
	 * Returns the pitch of a note at a given index, within the current record.
	 * 
	 * @param index The index of a note.
	 * @return The pitch of the note at the given index.
	 */
	public int getPitch(int index) {
		if (numRecords() == 0) {
			return -1;
		}
		return notes.get(saveIndex)[index].pitch;
	}
	
	/**
	 * Returns the rhythm of a note at a given index, within the current record.
	 * 
	 * @param index The index of a note.
	 * @return The rhythm of the note at the givan index.
	 */
	public int getRhythm(int index) {
		if (numRecords() == 0) {
			return -1;
		}
		return notes.get(saveIndex)[index].rhythm;
	}
	
	/**
	 * Sets the instrument of the current record.
	 * 
	 * @param instrument The instrument the current record will use.
	 */
	public void setInstrument(String instrument) {
		instList.set(saveIndex, instrument);
	}
	
	/**
	 * Sets teh tempo of the current record.
	 * 
	 * @param tempo The tempo the current record will have.
	 */
	public void setTempo(int tempo) {
		tempoList.set(saveIndex, tempo);
	}
	
	/**
	 * Sets the length of the current record.
	 * Basically, it sets how many notes the record will have.
	 * The length can not be less than 1.
	 * 
	 * @param length The length that the current record will have.
	 */
	public void setLength(int length) {
		if (length < 1) {
			return;
		}
		int i;
		int l = Math.min(length, length(saveIndex));
		Note[] n = new Note[length];
		for (i = 0; i < l; i++) {
			n[i] = notes.get(saveIndex)[i];
		}
		for (i = 0; i < length; i++) {
			n[i] = new Note();
		}
		notes.set(saveIndex, n);
	}
	


	
	private void setLength(int index, int length) {
		if (numRecords() == 0) {
			return;
		}
		if (length < 1) {
			return;
		}
		int i;
		int l = Math.min(length, length(index));
		Note[] n = new Note[length];
		for (i = 0; i < l; i++) {
			n[i] = notes.get(index)[i];
		}
		for (i = 0; i < length; i++) {
			n[i] = new Note();;
		}
		notes.set(index, n);
	}
	
	private int length(int index) {
		if (notes.get(index) == null) {
			return 0;
		}
		return notes.get(index).length;
	}
	
	private int getIndex(String name) {
		int i;
		for (i = 0; i < nameList.size(); i++) {
			if (name.equals(nameList.get(i))) {
				return i;
			}
		}
		return -1;
	}
	

	

	
	/**
	 * Begins Recalling.
	 * "Recalling" allows for the rhythm and pitch of each note in the current record to be returned from beginning to end.
	 */
	public void startRecalling() {
		if (numRecords() == 0) {
			return;
		}
		recallIndex = -1;
		recalling = true;
	}
	
	/**
	 * Returns weather there is another note to recall.
	 * 
	 * @return True if there is another note to recall; else, False.
	 */
	public boolean hasNext() {
		return recallIndex < length() - 1;
	}
	
	/**
	 * If recalling, returns the pitch of the next note in the current record.
	 * If the end of the record has been reched, ends recording.
	 * If the next pitch is not returned, returns -1.
	 * 
	 * @return The next pitch in the record.
	 */
	public int nextPitch() {
		if (!recalling) {
			return -1;
		}
		recallIndex += 1;
		if (recallIndex == length()) {
			recalling = false;
			return -1;
		}
		return getPitch(recallIndex);
	}
	
	/**
	 * If recalling, returns the rhythm of this note in the current record.
	 * Else: returns -1.
	 * 
	 * @return The rhythm of this note.
	 */
	public int thisRhythm() {
		if (!recalling) {
			return -1;
		}
		return getRhythm(recallIndex);
	}
	
	/**
	 * Returns whether or not the RecordHandler is recalling.
	 * 
	 * @return True if recalling; else, False.
	 */
	public boolean isRecalling() {
		if (recallIndex == length() - 1) {
			recalling = false;
		}
		return recalling;
	}
	
}