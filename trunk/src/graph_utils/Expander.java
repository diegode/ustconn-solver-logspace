package graph_utils;

import misc.Label;

public abstract class Expander extends Graph {
	
	public Expander(int regularityDegree) {
		super(0,regularityDegree);
	}
	
	abstract public Label rot(long v, int i);
	abstract public long dim();

}
