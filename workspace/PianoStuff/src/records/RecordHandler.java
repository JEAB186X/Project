package records;

import java.io.*;
import java.util.ArrayList;

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
 */

public class RecordHandler {
	
	private ArrayList<String> nameList;
	private ArrayList<String> instList;
	private ArrayList<Integer> tempoList;
	private ArrayList<Note[]> notes;
	
	private int saveIndex;
	private int recordIndex;
	private int recallIndex;
	
	private boolean recording;
	private boolean recalling;
	
	/**
	 * For Testing purposes. Please remove only once the project is complete.
	 * 
	 * @param args[] The arguments that can be passed into the program when prompted to run.
	 */
	public static void main(String args[]) {
		//Instance A Record Handler
		RecordHandler rh = new RecordHandler();
		int i;
		int j;
		
		//rh.addRecord("Default");
		//rh.changeRecord("New");
		//rh.removeRecord("New");
		
		/*
		rh.startRecording();
		i = 0;
		while (rh.isRecording()) {
			rh.addPitch(i);
			i++;
		}
		*/
		
		//rh.save();
		
		//Display Record Information
		System.out.println("Current Record: " + rh.name());
		for (i = 0; i < rh.numRecords(); i++) {
			rh.changeRecord(rh.name(i));
			System.out.println("Record Name: " + rh.name());
			System.out.println("Instrument: " + rh.instrument());
			System.out.println("Tempo: " + rh.tempo());
			rh.startRecalling();
			j = 0;
			while (rh.isRecalling()) {
				System.out.println(j + " : " + rh.nextPitch() + " : " + rh.thisRhythm());
				j++;
			}
		}
		
	}
	
	/**
	 * When constructed, all records are loaded from the Records.jm file.
	 */
	public RecordHandler() {
		nameList = new ArrayList<String>();
		instList = new ArrayList<String>();
		tempoList = new ArrayList<Integer>();
		notes = new ArrayList<Note[]>();
		load();
		saveIndex = 0;
		
		recording = false;
		recalling = false;
	}
	
	private void load() {
		try {
			DataInputStream stream = new DataInputStream(
				new FileInputStream("src/records/Records.jm"));
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
	 * This saves all records to the Records.jm file.
	 */
	public void save() {
		int i;
		int j;
		try {
			DataOutputStream stream = new DataOutputStream(
				new BufferedOutputStream(
					new FileOutputStream("records/Records.jm")));
			stream.writeInt(numRecords());
			for (i = 0; i < numRecords(); i++) {
				for (j = 0; j < name(i).length(); j++) {
					stream.writeChar(name(i).charAt(j));
				}
				stream.writeChar('\0');
				for (j = 0; j < instrument(i).length(); j++) {
					stream.writeChar(instrument(i).charAt(j));
				}
				stream.writeChar('\0');
				stream.writeInt(tempo(i));
				stream.writeInt(length(i));
				for (j = 0; j < length(i); j++) {
					stream.writeInt(notes.get(i)[j].pitch);
					stream.writeInt(notes.get(i)[j].rhythm);
				}
			}
			stream.close();
		}
		catch (IOException e) {
			System.out.println("2: " + e);
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
	}
	
	/**
	 * Removes a record. This change is not saved in the records.jm file until save() is called.
	 * @param name The name of the record that will be removed.
	 */
	public void removeRecord(String name) {
		int i;
		i = getIndex(name);
		if (i == -1) {
			System.out.println("Error: Record DNE");
			return;
		}
		nameList.remove(i);
		instList.remove(i);
		tempoList.remove(i);
		notes.remove(i);
		if (saveIndex >= i) {
			saveIndex -= 1;
		}
	}
	
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
	 * Returns the instrument of a record at a given index.
	 * 
	 * @param index The index of a record
	 * @return The instrument that the given record uses.
	 */
	private String instrument(int index) {
		return instList.get(index);
	}
	
	/**
	 * Returns the instrument the current record uses.
	 * 
	 * @return The instrument of the current record.
	 */
	public String instrument() {
		if (numRecords() == 0) {
			return "Piano";
		}
		return instList.get(saveIndex);
	}
	
	/**
	 * Returns the tempo of a given record given its index.
	 * 
	 * @param index The index of the subject record.
	 * @return The tempo of an index.
	 */
	private int tempo(int index) {
		return tempoList.get(index);
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
		for (i = i; i < length; i++) {
			n[i] = new Note();
		}
		notes.set(saveIndex, n);
	}
	
	/**
	 * Sets the pitch of a note at a given index, within the current record.
	 * 
	 * @param index The index of a note.
	 * @param p The pitch the note at the given index will have.
	 */
	public void setPitch(int index, int p) {
		if (numRecords() == 0) {
			return;
		}
		notes.get(saveIndex)[index].pitch = p;
	}
	
	/**
	 * Sets the rhythm of a note at a given index, within the current record.
	 * 
	 * @param index The index of a note.
	 * @param r The rhythm the note at the given index will have.
	 */
	public void setRhythm(int index, int r) {
		if (numRecords() == 0) {
			return;
		}
		notes.get(saveIndex)[index].rhythm = r;
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
		for (i = i; i < length; i++) {
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
	 * Begins recording.
	 * Recording allows for the pitch of each note in the current record to be set from beginning to end.
	 */
	public void startRecording() {
		if (numRecords() == 0) {
			return;
		}
		recordIndex = -1;
		recording = true;
	}
	
	/**
	 * If recording, sets the pitch of the next note in the current record.
	 * If the end of the record is reached, stops recording.
	 * 
	 * @param p The pitch that may be added to the current record.
	 */
	public void addPitch(int p) {
		if (!recording) {
			return;
		}
		recordIndex += 1;
		setPitch(recordIndex, p);
		if (recordIndex == length()) {
			recording = false;
		}
	}
	
	/**
	 * Returns if the RecordHandler is curretnly recording.
	 * 
	 * @return True if currently recording; else, False.
	 */
	public boolean isRecording() {
		if (recordIndex == length() - 1) {
			recording = false;
		}
		return recording;
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
	
	public void haltRecall() {
		
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