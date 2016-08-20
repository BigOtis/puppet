package src.sound.puppet.persistent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

	private String name;
	private HashMap<String, Word> vocabulary;
	
	public Person(){
		
	}
	
	public Person(String name){
		this.name = name;
		this.vocabulary = new HashMap<String, Word>();
	}
	
	public String getVocab(){
		String words = "";
		for(String key : vocabulary.keySet()){
			words += key + " ";
		}
		return words;	
	}
	
	public void addBit(String word, SoundBit bit){
		if(!vocabulary.containsKey(word)){
			Word nw = new Word(word);
			vocabulary.put(word, nw);
		}
		vocabulary.get(word).addBit(bit);
	}
	
	public void addWord(Word word){
		vocabulary.put(word.getWord(), word);
	}
	
	public SoundBit getRandomBit(String str){
		Random rand = new Random(10);
		Word word = getWord(str);
		if(word == null) 
			return null;
		ArrayList<SoundBit> bits = word.getBytes();
		return bits.get(rand.nextInt(bits.size()));
	}
	
	public Word getWord(String word){
		return vocabulary.get(word);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public HashMap<String, Word> getVocabulary() {
		return vocabulary;
	}

	public void setVocabulary(HashMap<String, Word>vocabulary) {
		this.vocabulary = vocabulary;
	}

		
}
