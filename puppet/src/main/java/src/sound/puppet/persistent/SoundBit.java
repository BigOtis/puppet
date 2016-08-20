package src.sound.puppet.persistent;

public class SoundBit {

	private String fileName;
	private double start;
	private double end;
	
	public SoundBit(){
		
	}
	
	public SoundBit(String fileName, double start, double end){
		this.fileName = fileName;
		this.start = start;
		this.end = end;
	}
	
	@Override
	public boolean equals(Object that){
		if(that instanceof SoundBit){
			SoundBit thatBit = (SoundBit) that;
			return thatBit.getStart() == this.getStart() 
					&& thatBit.getEnd() == this.getEnd()
					&& thatBit.getFileName() == this.getFileName();
		}
		
		return false;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public double getStart() {
		return start;
	}
	public void setStart(double start) {
		this.start = start;
	}
	public double getEnd() {
		return end;
	}
	public void setEnd(double end) {
		this.end = end;
	}
	
}
