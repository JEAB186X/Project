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
	}
	
	public Record() {
		length = 8;
		pitch = new int[8];
		rhythm = new int[8];
		
		file = new File("Records.jm");
		
		nameList = new ArrayList<String>();
		getNames();
		index = 0;
		
		recording = false;
		recalling = false;
		loaded = false;
	}
	
	public void getNames() {
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
	
	public boolean existName(String name) {
		return false;
	}
	
	public int getNameIndex() {
		return 0;
	}
	
	public void newSave() {
		
	}
	
	public void save() {
		
	}
	
	public void load() {
		
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