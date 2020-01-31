import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Record {
	
	private ArrayList<String> nameList;
	private ArrayList<int> length;
	private ArrayList<int[]> pitch;
	private ArrayList<int[]> rhythm;
	
	private boolean recording;
	private boolean recalling;
	
	private File file;
	
	private int saveIndex;
	private int index;
	
	private Scanner in;
	private String str;
	private int i;
	private int j;
	private int k;
	
	//For Testing Purposes:
	public static void main(String args[]) {
		Record r = new Record();
		System.out.println(r.length);
		for (int i = 0; i < 8; i++) {
			System.out.println(i + " : " + r.pitch.get(r.saveIndex)[i] + " : " + r.rhythm.get(r.saveIndex)[i]);
		}
	}
	
	public Record() {
		file = new File("Records.jm");
		nameList = new ArrayList<String>();
		length = new ArrayList<int>();
		pitch = new ArrayList<int[]>();
		rhythm = new ArrayList<int[]>();
		nameList.add("Default");
		length.add(8);
		pitch.add(new int[8]);
		rhythm.add(new int[8]);
		saveIndex = 0;
		//load();
		for (i = 0; i < 8; i++) {
			pitch.get(saveIndex)[i] = 1;
			rhythm.get(saveIndex)[i] = 4;
		}
		
		recording = false;
		recalling = false;
	}
	
	public int getNameIndex(String name) {
		return 0;
	}
	
	public void load() {
		
	}
	
	public void save() {
		
	}
	
	public void addSave(String name) {
		
	}
	
	public void deleteSave(String name) {
		
	}
	
	public void startRecord() {
		
	}
	
	//returns whether or not there is more
	public boolean add(int p) {
		return false;
	}
	
	public void startRecall() {
		
	}
	
	public int next() {
		return 0;
	}
	
}