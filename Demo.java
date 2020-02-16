import java.io.*;

public class Demo {
	
	private Note[] notes;
	
	private int recallIndex;
	
	private boolean recalling;
	
	public static void main(String args[]) {
		/*inputs data
		try {
			DataOutputStream stream = new DataOutputStream(
				new BufferedOutputStream(
					new FileOutputStream("Demo.jm")));
			stream.writeInt(7);
			
			stream.writeInt(10);
			stream.writeInt(1);
			
			stream.writeInt(8);
			stream.writeInt(1);
			
			stream.writeInt(6);
			stream.writeInt(1);
			
			stream.writeInt(8);
			stream.writeInt(1);
			
			stream.writeInt(10);
			stream.writeInt(1);
			
			stream.writeInt(10);
			stream.writeInt(1);
			
			stream.writeInt(10);
			stream.writeInt(2);
			
			stream.close();
		}
		catch (IOException e) {
			System.out.println(e);
		}*/
		
		/*retrieves data*/
		Demo demo = new Demo();
		demo.startRecalling();
		int i = 0;
		while (demo.isRecalling()) {
			System.out.println(i + " : " + demo.nextPitch() + " : " + demo.thisRhythm());
		}
		
		
	}
	
	public Demo() {
		load("Demo.jm");
		recallIndex = 0;
		recalling = false;
	}
	
	private void load(String file) {
		try {
			DataInputStream stream = new DataInputStream(
				new FileInputStream(file));
			int i;
			notes = new Note[stream.readInt()];
			for (i = 0; i < notes.length; i++) {
				notes[i] = new Note(stream.readInt(), stream.readInt());
			}
		}
		catch(IOException e) {
			System.out.println("1: " + e);
		}
	}
	
	public int getPitch(int index) {
		return notes[index].pitch;
	}
	
	public int getRhythm(int index) {
		return notes[index].rhythm;
	}
	
	public void startRecalling() {
		recallIndex = -1;
		recalling = true;
	}
	
	public int nextPitch() {
		if (!recalling) {
			return -1;
		}
		recallIndex += 1;
		if (recallIndex == notes.length) {
			recalling = false;
			return -1;
		}
		return notes[recallIndex].pitch;
	}
	
	public int thisRhythm() {
		if (!recalling) {
			return -1;
		}
		return notes[recallIndex].rhythm;
	}
	
	public boolean isRecalling() {
		if (recallIndex == notes.length - 1) {
			recalling = false;
		}
		return recalling;
	}
	
}