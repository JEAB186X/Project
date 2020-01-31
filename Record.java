public class Record {
	
	private int length;
	private int[] pitch;
	private int[] rhythm;
	
	private boolean recording;
	private boolean recalling;
	private boolean loaded;
	
	private String[] names;
	private int nameIndex;
	private int index;
	
	private int i;
	private int j;
	private int k;
	
	public Record() {
		length = 8;
		pitch = new int[8];
		rhythm = new int[8];
		
		index;
		
		recording = false;
		recalling = false;
		loaded = false;
	}
	
	public void startRecord() {
		index = 0;
		recording = true;
	}
	
	//returns whether or not there is more
	public boolean add(int p) {
		if (index == length) {
			return false;
		}
		else if (recording) {
			pitch[index] = p;
			rhythm[index] = r;
			index += 1;
			if (index == length) {
				loaded = true;
				recording = false;
			}
			return true;
		}
		return false;
	}
	
	public void startRecall() {
		
	}
	
	public int next() {
		
	}
	
	
	
	public void save() {
		
	}
	
	public void saveNew() {
		
	}
	
	public void load() {
		
	}