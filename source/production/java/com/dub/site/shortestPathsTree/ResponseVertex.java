package com.dub.site.shortestPathsTree;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseVertex {

	/**
	 *
	 */
	private String name;
	private Integer parent = null;
	private VertexColor color = VertexColor.BLACK;
	private int d = 0;
	private int f = 0;
	private int tree = 0;
	
	public ResponseVertex(DFSVertex source) {
		this.name = source.getName();
		this.parent = source.getParent();
		this.color = source.getColor();
		this.d = source.getD();
		this.f = source.getF();
		this.tree = source.getTree();
	}
	

	public ResponseVertex() {
		super();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
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
	
	public int getTree() {
		return tree;
	}

	public void setTree(int tree) {
		this.tree = tree;
	}

	
	@JsonIgnore
	public String toString() {
		return name + " " + parent + " " + color + " " 
				+ d + "/" + f + " " + tree;
	}

}
