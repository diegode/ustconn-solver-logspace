package misc;

public class Label {
	
	public long v;
	public long i;
	
	public Label(long v, long i) {
		this.v = v;
		this.i = i;
	}
	
	public String toString() {
		return "(" + v + "," + i + ")";
	}

}
