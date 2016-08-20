package src.sound.puppet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.ibm.watson.developer_cloud.speech_to_text.v1.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechAlternative;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechTimestamp;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.Transcript;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeDelegate;

import src.sound.puppet.persistent.Person;
import src.sound.puppet.persistent.SoundBit;
import src.sound.puppet.persistent.Word;
import src.sound.puppet.persistent.WordDataBaseManager;

/**
 * Hello world!
 *
 */
public class PuppetMaster 
{
    public static void main( String[] args )
    {	
    	String fileName = "obama.wav";
    	String name = "Nicole";
    	
    	PuppetMaster master = new PuppetMaster();
    	//master.transcribeWords(fileName, name);
    	master.printVocab(name);
    	
    	master.say(name, "nineteen");
    	master.say(name, "trillion");
    	master.say(name, "stamps");
    	master.say(name, "for");
    	master.say(name, "president");
    	master.say(name, "obama");
    	//master.say(name, "one");
    	//master.say(name, "million");
    	//master.say(name, "obama");
    	
    }
    
    public void printVocab(String name){
    	WordDataBaseManager mgr = new WordDataBaseManager();
    	Person p = mgr.loadPerson(name);
    	System.out.println(p.getVocab());
    }
    
    public void say(String personName, String word){
    	
    	WordDataBaseManager mgr = new WordDataBaseManager();
    	AudioFileManager afmgr = new AudioFileManager();
    	
    	Person p = mgr.loadPerson(personName);
    	afmgr.say(p.getRandomBit(word), word);
    	
    	
    }
    
    public void transcribeWords(String fileName, String name){
    	
    	WordDataBaseManager mgr = new WordDataBaseManager();
    	Person person = mgr.loadPerson(name);
    	if(person == null){
    		person = new Person(name);
    	}
    	
        SpeechToText service = new SpeechToText();
        service.setUsernameAndPassword("8bba08ef-ca90-4339-ae6f-36c6f3553ec6", "dJKQDaJ52HSX");

       	RecognizeOptions options = new RecognizeOptions().contentType("audio/wav")
       			.timestamps(true).wordAlternativesThreshold(0.9).continuous(true);
       	

       	for(Transcript script : service.recognize(new File(fileName), options).getResults()){
	       	System.out.println("Recieved Script: " + script);
	       	SpeechAlternative alt = script.getAlternatives().get(0);
	       	List<SpeechTimestamp> timeStamps = alt.getTimestamps();
	       	
	       	for(SpeechTimestamp ts : timeStamps){
	       		SoundBit bit = new SoundBit(fileName, ts.getStartTime(), ts.getEndTime());
	       		person.addBit(ts.getWord().toLowerCase(), bit);
	       	}
       	}
       	
       	mgr.savePerson(person);
    }
}
