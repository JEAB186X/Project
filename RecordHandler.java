import java.io.*;
import java.util.ArrayList;

/**
 * This class loads, saves, and manages all records.
 * <p>
 * Overview of a record:
 * <p>
 * Each record consists of a name, length, and a pitch array and rhythm array.
 * The length corrosponds to how many notes are in a record.
 * Each element of the pitch and rhythm arrays corrospond to a same note.
 * The pitch and rhythm elements that have the same index corrospond to the same array.
 * The value of the pitch corrosponds to the frequency of a note: 1 is the lowest value, 13 is the highest value.
 * The "rhythm" is an inverse measure of durration: 4 means it the note is a quarter note, 1 means it is a whole note, etc.
 * 
 * @author Arvid Gustafson : arvidg@iastate.edu
 */

public class RecordHandler {
	
	private ArrayList<String> nameList;
	private ArrayList<int[]> pitch;
	private ArrayList<int[]> rhythm;
	
	private int saveIndex;
	private int recordIndex;
	private int recallIndex;
	
	private boolean recording;
	private boolean recalling;
	
	private String str;
	private int i;
	private int j;
	private int k;
	private int l;
	
	/**
	 * For Testing purposes. Please remove only once the project is complete.
	 * 
	 * @param args[] The arguments that can be passed into the program when prompted to run.
	 */
	public static void main(String args[]) {
		//Instance A Record Handler
		RecordHandler r = new RecordHandler();
		
		//r.removeRecord("New");
		//r.addRecord("New");
		//r.changeRecord("New");
		//r.save();
		
		//Display Record Information
		System.out.println("Current Record: " + r.name());
		for (int i = 0; i < r.numRecords(); i++) {
			r.changeRecord(r.nameList.get(i));
			System.out.println(r.name(i));
			System.out.println(r.length());
			for (int j = 0; j < r.length(); j++) {
				System.out.println(j + " : " + r.pitch.get(i)[j] + " : " + r.rhythm.get(i)[j]);
			}
		}
	}
	
	/**
	 * When constructed, all records are loaded from the Records.jm file.
	 * There will always be a "Default" record.
	 * The "Default" record will always start as the current record.
	 */
	public RecordHandler() {
		nameList = new ArrayList<String>();
		pitch = new ArrayList<int[]>();
		rhythm = new ArrayList<int[]>();
		load();
		saveIndex = 0;
		
		recording = false;
		recalling = false;
	}
	
	private void load() {
		try {
			DataInputStream stream = new DataInputStream(
				new FileInputStream("Records.jm"));
			k = stream.readInt();
			for (i = 0; i < k; i++) {
				str = "";
				j = stream.readChar();
				while (j != '\0') {
					str = str + (char) j;
					j = stream.readChar();
				}
				addRecord(str);
				setLength(i, stream.readInt());
				for (j = 0; j < length(i); j++) {
					pitch.get(i)[j] = stream.readInt();
					rhythm.get(i)[j] = stream.readInt();
				}
			}
			stream.close();
		}
		catch (IOException e) {
			System.out.println("1: " + e);
		}
	}
	
	/**
	 * This saves all records to the Records.jm file.
	 */
	public void save() {
		try {
			DataOutputStream stream = new DataOutputStream(
				new BufferedOutputStream(
					new FileOutputStream("Records.jm")));
			stream.writeInt(numRecords());
			for (i = 0; i < numRecords(); i++) {
				for (j = 0; j < name(i).length(); j++) {
					stream.writeChar(name(i).charAt(j));
				}
				stream.writeChar('\0');
				stream.writeInt(length(i));
				for (j = 0; j < length(i); j++) {
					stream.writeInt(pitch.get(i)[j]);
					stream.writeInt(rhythm.get(i)[j]);
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
		saveIndex = numRecords();
		nameList.add(name);
		pitch.add(null);
		rhythm.add(null);
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
			System.out.println("Erorr: Save DNE");
		}
	}
	
	/**
	 * Removes a record. This change is not saved in the records.jm file until save() is called.
	 * @param name The name of the record that will be removed.
	 */
	public void removeRecord(String name) {
		i = getIndex(name);
		if (i == -1) {
			System.out.println("Error: Save DNE");
			return;
		}
		if (i == 0) {
			System.out.println("Error: Illegal Operation");
			return;
		}
		nameList.remove(i);
		pitch.remove(i);
		rhythm.remove(i);
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
		return nameList.get(saveIndex);
	}
	
	/**
	 * Returns the length of the current record.
	 * Basically, it returns how many notes are in the current record.
	 * 
	 * @return The length of the current record.
	 */
	public int length() {
		return rhythm.get(saveIndex).length;
	}
	
	/**
	 * Returns the pitch of a note at a given index, within the current record.
	 * 
	 * @param index The index of a note.
	 * @return The pitch of the note at the given index.
	 */
	public int getPitch(int index) {
		return pitch.get(saveIndex)[index];
	}
	
	/**
	 * Returns the rhythm of a note at a given index, within the current record.
	 * 
	 * @param index The index of a note.
	 * @return The rhythm of the note at the givan index.
	 */
	public int getRhythm(int index) {
		return rhythm.get(saveIndex)[index];
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
		pitch.set(saveIndex, new int[length]);
		rhythm.set(saveIndex, new int[length]);
		for (l = 0; l < length; l++) {
			setPitch(l, 1);
			setRhythm(l, 4);
		}
	}
	
	/**
	 * Sets the pitch of a note at a given index, within the current record.
	 * 
	 * @param index The index of a note.
	 * @param p The pitch the note at the given index will have.
	 */
	public void setPitch(int index, int p) {
		pitch.get(saveIndex)[index] = p;
	}
	
	/**
	 * Sets the rhythm of a note at a given index, within the current record.
	 * 
	 * @param index The index of a note.
	 * @param r The rhythm the note at the given index will have.
	 */
	public void setRhythm(int index, int r) {
		rhythm.get(saveIndex)[index] = r;
	}
	
	private void setLength(int index, int length) {
		if (length < 1) {
			return;
		}
		pitch.set(index, new int[length]);
		rhythm.set(index, new int[length]);
		for (l = 0; l < l; l++) {
			pitch.get(index)[l] =  1;
			rhythm.get(index)[l] =  4;
		}
	}
	
	private int length(int index) {
		return rhythm.get(index).length;
	}
	
	private int getIndex(String name) {
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
		recordIndex = 0;
		recording = true;
	}
	
	/**
	 * If recording, sets the pitch of the next note in the current record.
	 * If the end of the record is reached, stops recording.
	 * 
	 * @param p The pitch that may be added to the current record.
	 */
	public void addPitch(int p) {
		if (recording) {
			addPitch(p);
			recordIndex += 1;
		}
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
		return recording;
	}
	
	/**
	 * Begins Recalling.
	 * "Recalling" allows for the rhythm and pitch of each note in the current record to be returned from beginning to end.
	 */
	public void startRecalling() {
		recallIndex = 0;
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
		if (recalling) {
			recallIndex += 1;
		}
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
		if (recalling) {
			return getRhythm(recallIndex);
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Returns whether or not the RecordHandler is recalling.
	 * 
	 * @return True if recalling; else, False.
	 */
	public boolean isRecalling() {
		return recalling;
	}
	
}