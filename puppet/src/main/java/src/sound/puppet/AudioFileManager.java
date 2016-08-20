package src.sound.puppet;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import src.sound.puppet.persistent.SoundBit;

public class AudioFileManager implements LineListener {

	/**
    * this flag indicates whether the playback completes or not.
    */
   boolean playCompleted;
   
   public void say(SoundBit bit, String word){
	   
	   if(bit == null){
		   System.err.println("No sound bit exists yet");
		   return;
	   }
	   
	   File audioFile = new File(bit.getFileName());
	   long start = s2ms(bit.getStart());
	   long end  = s2ms(bit.getEnd());
	   
	   System.out.println("Saying: " + word + " File: " + bit.getFileName() + " " 
			   + bit.getStart() + " - " + bit.getEnd());
       try {
           AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
           AudioFormat format = audioStream.getFormat();
           DataLine.Info info = new DataLine.Info(Clip.class, format);
           Clip audioClip = (Clip) AudioSystem.getLine(info);
           audioClip.addLineListener(this);
           audioClip.open(audioStream);
           
           audioClip.setMicrosecondPosition(start);
           audioClip.start(); 
           
           while (!playCompleted) {
               // wait for the playback completes
               try {
            	   if(audioClip.getMicrosecondPosition() > end){
            		   playCompleted = true;
            	   }
            	   else{
            		   Thread.sleep(10);
            	   }
               } catch (InterruptedException ex) {
                   ex.printStackTrace();
               }
           }
            
           audioClip.close();
            
       } catch (Exception e){
    	   e.printStackTrace();
       }
	   
   }
    
   /**
    * Listens to the START and STOP events of the audio line.
    */
   public void update(LineEvent event){
	   LineEvent.Type type = event.getType();
       if (type == LineEvent.Type.START) {
            
       } else if (type == LineEvent.Type.STOP) {
           playCompleted = true;
       }

   }


   
   public long s2ms(double s){
	   return (long) (s * 1_000_000);
   }
}
