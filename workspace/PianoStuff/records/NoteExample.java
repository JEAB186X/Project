package records;

public class Note {
	
	public int pitch;
	public int rhythm;
	
	public Note() {
		pitch = 1;
		rhythm = 4;
	}
	
	public Note(int p, int r) {
		pitch = p;
		rhythm = r;
	}
	
	public void set(Note n) {
		pitch = n.pitch;
		rhythm = n.rhythm;
	}
	
	public void set(int p, int r) {
		pitch = p;
		rhythm = r;
	}
	
}