import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Record {
	
	private int length;
	private int[] pitch;
	private int[] rhythm;
	
	private boolean recording;
	private boolean recalling;
	private boolean loaded;
	
	private File file;
	
	private ArrayList<String> nameList;
	private int nameIndex;
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
		for (int i = 0; i < r.length; i++) {
			System.out.println(i + " : " + r.pitch[i] + " : " + r.rhythm[i]);
		}
	}
	
	public Record() {
		file = new File("Records.jm");
		nameList = new ArrayList<String>();
		getNameList();
		nameIndex = 0;
		load();
		
		recording = false;
		recalling = false;
		loaded = false;
	}
	
	public void getNameList() {
		try {
			in = new Scanner(file);
			while (in.hasNextLine()) {
				str = in.nextLine();
				if (str.charAt(0) == ':') {
					nameList.add(str.substring(1, str.length()));
				}
			}
			in.close();
		}
		catch(java.io.FileNotFoundException e) {
			System.out.println("Darn");
		}
	}
	
	public int getNameIndex(String name) {
		for (i = 0; i < nameList.size(); i++) {
			if (name.equals(nameList.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	public void load() {
		try {
			in = new Scanner(file);
			for (i = 0; i < nameIndex; i++) {
				in.nextLine();
				in.nextLine();
			}
			in.nextLine();
			str = in.nextLine();
			in.close();
		}
		catch (java.io.FileNotFoundException e) {
			System.out.println("Darn");
		}
		length = 0;
		j = 1;
		for (i = k; str.charAt(k) - '0' < 10; k++);
		for (i = k - 1; i >= 0; i--) {
			length += (str.charAt(i) - '0') * j;
			j *= 10;
		}
		pitch = new int[length];
		rhythm = new int[length];
		for (i = 0; i < length; i++) {
			pitch[i] = str.charAt(k+i*2) - 'A';
			rhythm[i] = str.charAt(k+i*2+1) - 'A';
		}
	}
	
	public void newSave() {
		
	}
	
	public void save() {
		
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