package records;

public class NewRecordHandler {
	
	public static void main(String[] args) {
		
		AudioFormat format = new AudioFormat(16000, 8, 2, true, true);
		
		DataLine.Info info = new DataLine.info(TargetDataLine.class, format);	
	
	
	    if (!AudioSystem.isLineSupported(info) ) {
			System.out.println("line is not supported");
			
		}	
		
		final TargetDataLine targetDataLine = (TargetDataLine)AudioSystem.getLine(info);
		
		
		
		TargetDataLine.open();
		
		System.out.println("Start recording");

	    TargetDataLine.start();
		
		
		
		
		
		Thread stopper = new Thread(new Runnable() {
			
			public void run() {
				AudioInputStream audioStream = new AudioInputStream(targetDataLine);
				
				
				File waveFile = new File("records/recording.wav");
				
				
				try {
					AudioSystem.write(audioStream, AudioFileFormat.type.WAVE, waveFile);
				} 
				catch (IOexception e) {
					e.printStackTrace();
					
				}	
				
				
				
				
				
			}
			
		});
			
			stopper.start();
			
			Thread.sleep(5000);
			
			targetDataLine.stop();
			
			targetDataLine.close():
				
			System.out.println("Ended sound test");	
				
				
				
			
			
		}
	}
	
	
}