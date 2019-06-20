package com.dub.spring.shortestPathsTree;

/** POJO represents an edge for AJAX initialization request */
public class JSONEdge {
	
	private int from;// origin
	private int to;// end

	public JSONEdge(JSONEdge source) {
		this.from = source.from;
		this.to = source.to;
	}
	
	public JSONEdge(int from, int to) {
		this.from = from;
		this.to = to;
	}
	
	public JSONEdge() {
	}
	
	
	
	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}
	
	public String toString() {// for debug only
		return from + ", " + to;
	}

}
