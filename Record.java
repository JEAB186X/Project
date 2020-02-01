import java.io.*;
import java.util.ArrayList;

public class Record {
	
	private ArrayList<String> nameList;
	private ArrayList<Integer> length;
	private ArrayList<int[]> pitch;
	private ArrayList<int[]> rhythm;
	
	private int saveIndex;
	private int index;
	
	private boolean recording;
	private boolean recalling;
	
	private String str;
	private int i;
	private int j;
	private int k;
	
	//For Testing Purposes:
	public static void main(String args[]) {
		Record r = new Record();
		for (int i = 0; i < r.nameList.size(); i++) {
			System.out.println(r.nameList.get(i));
			System.out.println(r.length.get(i));
			for (int j = 0; j < r.length.get(i); j++) {
				System.out.println(j + " : " + r.pitch.get(i)[j] + " : " + r.rhythm.get(i)[j]);
			}
		}
	}
	
	public Record() {
		nameList = new ArrayList<String>();
		length = new ArrayList<Integer>();
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
				nameList.add(str);
				length.add(stream.readInt());
				pitch.add(new int[length.get(i)]);
				rhythm.add(new int[length.get(i)]);
				for (j = 0; j < length.get(i); j++) {
					pitch.get(i)[j] = stream.readInt();
					rhythm.get(i)[j] = stream.readInt();
				}
			}
			stream.close();
		}
		catch (IOException e) {
			System.out.println("Darn.");
		}
	}
	
	public void save() {
		try {
			DataOutputStream stream = new DataOutputStream(
				new BufferedOutputStream(
					new FileOutputStream("Records.jm")));
			stream.writeInt(nameList.size());
			for (i = 0; i < nameList.size(); i++) {
				for (j = 0; j < nameList.get(i).length(); j++) {
					stream.writeChar(nameList.get(i).charAt(j));
				}
				stream.writeChar('\0');
				stream.writeInt(length.get(i));
				for (j = 0; j < length.get(i); j++) {
					stream.writeInt(pitch.get(i)[j]);
					stream.writeInt(rhythm.get(i)[j]);
				}
			}
			stream.close();
		}
		catch (IOException e) {
			System.out.println("Darn.");
		}
	}
	
	public void addSave(String name) {
		saveIndex = nameList.size();
		nameList.add(name);
		length.add(8);
		pitch.add(new int[8]);
		rhythm.add(new int[8]);
		setLength(8);
	}
	
	public void changeSave(String name) {
		saveIndex = getIndex(name);
		if (saveIndex == -1) {
			saveIndex = 0;
			System.out.println("Erorr: Save DNE");
		}
	}
	
	public void deleteSave(String name) {
		i = getIndex(name);
		if (i == -1) {
			System.out.println("Error: Save DNE");
			return;
		}
		nameList.remove(i);
		length.remove(i);
		pitch.remove(i);
		rhythm.remove(i);
		if (saveIndex >= i) {
			saveIndex -= 1;
		}
	}
	
	public void setLength(int l) {
		length.set(saveIndex, l);
		pitch.set(saveIndex, new int[l]);
		rhythm.set(saveIndex, new int[l]);
		for (i = 0; i < l; i++) {
			pitch.get(saveIndex)[i] =  1;
			rhythm.get(saveIndex)[i] =  4;
		}
	}
	
	private int getIndex(String name) {
		for (i = 0; i < nameList.size(); i++) {
			if (name.equals(nameList.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	public void startRecord() {
		
	}
	
	public void add(int p) {
		
	}
	
	public void startRecall() {
		
	}
	
	public int nextPitch() {
		return 0;
	}
	
}