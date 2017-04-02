package com.dub.site.shortestPathsTree;


/** POJO represents a vertex for AJAX requests */
public class JSONVertex {
	
	/**
	 * 
	 */
	private String name;
	private Integer predecessor = null;
	private VertexColor color = VertexColor.BLACK;
	private int d = 0;
	private int f = 0;
	

	public JSONVertex() {
		
	}
	
	public JSONVertex(DFSVertex source) {
		this.name = source.getName();
		this.predecessor = source.getParent();
		this.color = source.getColor();
		this.d = source.getD();
		this.f = source.getF();
	}
	
	public JSONVertex(SPTVertex source) {
		this.name = source.getName();
		this.predecessor = source.getPredecessor();
		this.color = source.getColor();
		this.d = source.getD();
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
	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Integer predecessor) {
		this.predecessor = predecessor;
	}
	

}
