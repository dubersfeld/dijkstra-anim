package com.dub.site.shortestPathsTree;


public class JSONSnapshot {

	/** An array of vertices */
	private JSONVertex[] vertices;
	
	/** An array of adjacency lists*/
	private JSONAdjacency[] adjacencies;
	
	private int N;
	
	public JSONSnapshot(int N) {
		vertices = new JSONVertex[N];
		adjacencies = new JSONAdjacency[N];
		this.N = N;
	}
	
	
	public JSONVertex[] getVertices() {
		return vertices;
	}
	public void setVertices(JSONVertex[] vertices) {
		this.vertices = vertices;
	}
	public JSONAdjacency[] getAdjacencies() {
		return adjacencies;
	}
	public void setAdjacencies(JSONAdjacency[] adjacencies) {
		this.adjacencies = adjacencies;
	}
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	
	
	
}
