package src.sound.puppet.persistent;

import java.util.ArrayList;

public class Word {

	private String word;
	private ArrayList<SoundBit> bits;
	
	public Word(){
		
	}
	
	public Word(String word){
		this.word = word;
		this.bits = new ArrayList<SoundBit>();
	}
	
	public void addBit(SoundBit bit){
		if(!bits.contains(bit)){
			bits.add(bit);
		}
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArrayList<SoundBit> getBytes() {
		return bits;
	}

	public void setBytes(ArrayList<SoundBit> bytes) {
		this.bits = bytes;
	}
	
	
}
