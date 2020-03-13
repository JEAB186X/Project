package audio;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import records.RecordHandler;


public class AudioHandler {
	
	public void playFile(int index, String instrument) {
		
		try {
			
			
			
			File music = new File("/C:/Users/elvis/Documents/Notes/"  + instrument + "/Note" + index + ".wav");
			
			if (music.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);	
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				
			}
			else {
				System.out.println("Couldnt find the file");
				
			}
		}
		catch (Exception ex ) {
			ex.printStackTrace();
		}
	}
	
	//must start playing the current record in the recordhandler class.
	void startPlayingRecord(RecordHandler rh) {







	
	}
	// must stop playing the current record if it is playing.
	void stopPlayingRecord() {
		 







		 
		 
	 }


package miniassignment1;

/**
 * @author elvis kimara
 *
 */
public class S2020Mini1 {
	
	private S2020Mini1() {}
	/**
	 * Returns the number of positions in which the input strings differ.
	 * @param s A string of characters
	 * @param t  A string of characters
	 * @return Returns the number of Differences present 
	 */
	public static int countDifferences(java.lang.String s, java.lang.String t) {
		int a = s.length();
		int b = t.length();
		int count = 0;
		

			for (int i = 0; i < Math.min(t.length(), s.length()); ++i)  {
				
				if (s.charAt(i) != t.charAt(i)) {
					count++;
				}
				
			}

			if (b != a) {
				int excess = b - a;
				count += Math.abs(excess);
			}		
		return count;
	}
	
	/**
	 * Finds the first occurance of the smallest character in the string s and returns its index.
	 * @param s  the string of the word 
	 * @return index, the index of the smallest word
	 */
	public static int findSmallest(java.lang.String s) {
		int index = 0;
		char small = s.charAt(0);
		
		
		for(int i = 1; i < s.length(); ++i) {
			
			char b = s.charAt(i);
			
			if (b < small) {
				index = i;
				small = s.charAt(i);
			}
		}
		
		
		
		
		
		
		
		
		return index;
	}
	
	/**
	 * Returns true if and only if seq is a geometric sequence; that is, 
	 * the values in seq change by a constant multiple.
	 * @param seq
	 * @return
	 */
	public static boolean isGeometric(java.lang.String seq) {

		
		String[] str = seq.split(",");
		
		int [] arr = new int[str.length];
		
		for (int i = 0; i < str.length; ++i) {
			arr[i] = Integer.parseInt(str[i]);
			
		}
		
		int ratio = arr[ 1] / arr[0];
		
		for (int i = 0; i < arr.length - 1; ++i) {
			
			if (arr[i + 1] / arr[i] != ratio) {
				return false;
			}
			
		}
				
		return true;
	}
	

	/**
	 * Returns the index of the start of the next instance of sub in s at or after index start,
	 * or -1 if the substring doesn't occur after the given index.
	 * @param s the string being input 
	 * @param sub the string being checked 
	 * @param start the int index to start from
	 * @return  the int index to be returned 
	 */
	public static int nextIndexOf(java.lang.String s, java.lang.String sub, int start) {
		
		int index = -1;
	
		
		
		int x = 0;
		int y = 0;
		
		
		for (int i = start; i < s.length(); i++) {
			
			if (s.charAt(i) == sub.charAt(y)) {
				x += 1;
				y += 1;
				
				
				if (x == sub.length()) {
					index = i - sub.length() + 1;
					break;
				}
			}
			else {
				x = 0;
				y = 0;
			}
		}
		
		return index;
	}
	
	
	/**
	 * Returns a new string constructed from the input such that all instances of characters not 
	 * next to an equivalent character in the input are removed
	 * @param s A string of user input
	 * @return  a new string containing only words that had doubles 
	 */
	public static java.lang.String	removeSingles(java.lang.String s) {
		

		
		String word = "";

		
		if (s.charAt(0) == s.charAt(1)) {
			word += s.charAt(0);
		}
		
		
		for (int i = 1; i < s.length() - 1; i++) {
			
			
			if (s.charAt(i) == s.charAt(i + 1)) {
				word += s.charAt(i) ;
				
				if (i == s.length() - 2) {
					word += s.charAt(i + 1);
				}
			}
			else {
				
				if (s.charAt(i - 1) == s.charAt(i)) {
					word += s.charAt(i) ;
				}
				
			}
		}

		return word;
	}

	
	
	/**
	 * Given an instance of Random, r, an upper bound, limit, and a sequence length, length, returns the number of
	 * random numbers generated after reading the first ascending (by 1) sequence of numbers of length length.
	 * @param r takes an instance of a random 
	 * @param limit this is the limit that cant be passed
	 * @param length this is the length of the sequence 
	 * @return returns the int in a sequence thats ascending by one 
	
	 *
	 */
	
	
	public static int sequenceOfLength(java.util.Random r, int limit, int length) {
		
		int count = 0;
		int t = r.nextInt(limit);
		int value = 1;
		
		
		while  (count < length) {
			int f = r.nextInt(limit);
			
			if (f == t + 1) {
				value += 1;
				t = f;
				count += 1;
			
				
				
			}
			else {
				value += 1;
				count = 1;
				t = f;
			}
			
		}
		
		return value;
	}
	
	/**
	 * Calculates the square root of d using Newton's Method
	 * @param d Takes in a double of a figure to be square rooted 
	 * @return  returns the square root of the double found using NRM 
	 */
	public static double squareRoot(double d) {
		
		
	    double temp = d / 2;
	    
	    
	    for(double sqrRoot; ; temp = sqrRoot) {
	    	
	    	
	        sqrRoot = temp - (temp * temp - d) / (2 * temp);
	        
	        if (Math.abs(temp - sqrRoot) < 1e-10)   	
	        	return sqrRoot;
	    }
	}
	
	
	public static void main(String[] args) {
		

		String words = "1,3,9,27";
		String name = "kimmara";
		
		
		
		String nam = removeSingles(name);
		System.out.println(nam);
		
		int n = nextIndexOf(name, "i", 0);
		System.out.println(n);
		
		
		boolean result = isGeometric(words);
		System.out.println(result);

		
		int k = findSmallest(name);
		System.out.println(k);
		System.out.println(name.charAt(k));
		
		
		double s = squareRoot(144.0);
		System.out.println(s);
		
		int diff = countDifferences("box", "boxm");
		System.out.println(diff);
	
		//Random g = new Random(12);
		
		
		System.out.println("\n\n\n\n\n\n");
		//int p = sequenceOfLength(g, 2, 2);
		//System.out.println(p);
		//System.out.println(1000);
	}

}

}
	
	
	
	
	
	
