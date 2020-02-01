import java.io.*;
import java.util.ArrayList;

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
	
	//For Testing Purposes:
	public static void main(String args[]) {
		//Instance A Record
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
	
	public RecordHandler() {
		nameList = new ArrayList<String>();
		pitch = new ArrayList<int[]>();
		rhythm = new ArrayList<int[]>();
		load();
		saveIndex = 0;
		
		recording = false;
		recalling = false;
	}
	
	public void load() {
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
	
	public void addRecord(String name) {
		saveIndex = numRecords();
		nameList.add(name);
		pitch.add(null);
		rhythm.add(null);
		setLength(8);
	}
	
	public void changeRecord(String name) {
		saveIndex = getIndex(name);
		if (saveIndex == -1) {
			saveIndex = 0;
			System.out.println("Erorr: Save DNE");
		}
	}
	
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
	
	public int numRecords() {
		return nameList.size();
	}
	
	public String name(int index) {
		return nameList.get(index);
	}
	
	public String name() {
		return nameList.get(saveIndex);
	}
	
	public int length() {
		return rhythm.get(saveIndex).length;
	}
	
	public int getPitch(int index) {
		return pitch.get(saveIndex)[index];
	}
	
	public int getRhythm(int index) {
		return rhythm.get(saveIndex)[index];
	}
	
	public void setLength(int length) {
		pitch.set(saveIndex, new int[length]);
		rhythm.set(saveIndex, new int[length]);
		for (l = 0; l < length; l++) {
			setPitch(l, 1);
			setRhythm(l, 4);
		}
	}
	
	public void setPitch(int index, int p) {
		pitch.get(saveIndex)[index] = p;
	}
	
	public void setRhythm(int index, int r) {
		rhythm.get(saveIndex)[index] = r;
	}
	
	private void setLength(int index, int length) {
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
	
	public void startRecording() {
		recordIndex = 0;
		recording = true;
	}
	
	public void addPitch(int p) {
		if (recording) {
			addPitch(p);
			recordIndex += 1;
		}
		if (recordIndex == length()) {
			recording = false;
		}
	}
	
	public boolean isRecording() {
		return recording;
	}
	
	public void startRecalling() {
		recallIndex = 0;
		recalling = true;
	}
	
	public boolean hasNext() {
		return recallIndex < length() - 1;
	}
	
	public int nextPitch() {
		if (recalling) {
			recallIndex += 1;
		}
		if (recallIndex == length()) {
			return getPitch(recallIndex - 1);
		}
		else {
			return -1;
		}
	}
	
	public int thisRhythm() {
		if (recalling) {
			return getRhythm(recallIndex);
		}
		else {
			return -1;
		}
	}
	
	public boolean isRecalling() {
		return recalling;
	}
	
}