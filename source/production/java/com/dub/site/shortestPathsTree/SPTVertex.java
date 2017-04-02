package com.dub.site.shortestPathsTree;


public class SPTVertex extends Vertex {
	
	// all additional fields needed for Dijkstra algorithm
	private int d;
	private Integer predecessor = null;
	private VertexColor color;
	
	public SPTVertex(Vertex source) {
		super(source);
	}
	
	public SPTVertex(DFSVertex source) {
		super(source);
		predecessor = null;
		color = VertexColor.BLACK;
		d = 0;
	}
	
	
	public VertexColor getColor() {
		return color;
	}

	public void setColor(VertexColor color) {
		this.color = color;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	

	public Integer getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Integer predecessor) {
		this.predecessor = predecessor;
	}

	public String toString() {
		return name + " " + d + " " + predecessor;
	}

}
