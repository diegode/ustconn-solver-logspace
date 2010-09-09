package misc;

public class Pair<T, S> implements Comparable<Pair<Integer,Integer>> {
	public Pair(T f, S s) { 
		x = f;
		y = s;   
	}

	public T x;
	public S y;
	
	@Override
	public int compareTo(Pair<Integer, Integer> o) {
		if (x == o.x && y == o.y)
			return 0;
		else
			return 1;
	}
	
	public String toString() {
		return x + " " + y;
	}

}