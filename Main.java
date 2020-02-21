import Records.*;

public class Main {
	
	public static void main(String args[]) {
		RecordHandler rh = new RecordHandler();
		int i;
		int j;
		
		//Prints all records in the Record Handler
		System.out.println("Current Record: " + rh.name());
		for (i = 0; i < rh.numRecords(); i++) {
			rh.changeRecord(rh.name(i));
			System.out.println("Record Name: " + rh.name());
			rh.startRecalling();
			j = 0;
			while (rh.isRecalling()) {
				System.out.println(j + " : " + rh.nextPitch() + " : " + rh.thisRhythm());
				j++;
			}
		}
	}
	
}