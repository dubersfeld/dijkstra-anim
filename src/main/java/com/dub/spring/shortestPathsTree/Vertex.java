package com.dub.spring.shortestPathsTree;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/** Vertex has a name and an adjacency list of vertices */
public class Vertex {

	/**
	 * 
	 */
	protected String name = "";   
		
	@JsonIgnore
	protected List<WeightedEdge> adjacency;// all adjacent vertices, unused in AJAX response
	
	public Vertex() {
		adjacency = new ArrayList<WeightedEdge>();
	}
	
	public Vertex(Vertex source) {
		this.name = source.name;
		this.adjacency = new ArrayList<WeightedEdge>();
		for (int i = 0; i < source.adjacency.size(); i++) {
			this.adjacency.add(source.adjacency.get(i));
		}
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<WeightedEdge> getAdjacency() {
		return adjacency;
	}

	public void setAdjacency(List<WeightedEdge> adjacency) {
		this.adjacency = adjacency;
	}

	// helper function that return adjacency index of a vertex index
	public Integer getAdjIndex(int index) {
		int n = this.adjacency.size();
		int k = 0;
		for (k = 0; k < n; k++) {
			if (this.adjacency.get(k).getTo() == index) {
				break;
			}
		}// for
		if (k < n) {
				return k;
		} else {
				return null;
		}
	}
	
}
