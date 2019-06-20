package com.dub.spring.shortestPathsTree;

import java.io.Serializable;
import java.util.List;


/** Graph has Vertices and Adjacency Lists */
public class Graph implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Vertex[] vertices;
	protected int N;
	
	
	public Graph(int N) {
		this.N = N;
		this.vertices = new Vertex[N];
	}
	
	
	
	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVertices(Vertex[] vertices) {
		this.vertices = vertices;
	}

	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}




	public void display() {// used for debugging only
		for (Vertex v : vertices) {
			System.out.println(v.getName());
		}
		System.out.println();
	}
	
	public void display2() {// used for debugging only
		System.out.println("display2");
		for (int i1 = 0; i1 < N; i1++) {// for each vertex
			System.out.print(vertices[i1].getAdjacency().size() + " ");
			System.out.print(vertices[i1].getName() + " -> ");
			for (int i2 = 0; i2 < vertices[i1].getAdjacency().size(); i2++) {
				int lind = vertices[i1].getAdjacency().get(i2).getTo();
				System.out.print(this.vertices[lind].getName() + " ");
			}
			System.out.println();
		}
		System.out.println();	
	}	
	
	public void displayWeights() {// used for debugging only
		System.out.println("displayWeight");
		for (int i1 = 0; i1 < N; i1++) {// for each vertex
			System.out.print(vertices[i1].getAdjacency().size() + " ");
			System.out.print(vertices[i1].getName() + " -> ");
			for (int k = 0; k < vertices[i1].getAdjacency().size(); k++) {
				int lind = vertices[i1].getAdjacency().get(k).getTo();
				System.out.print("(" + vertices[lind].getName() + " " 
						+ vertices[i1].getAdjacency().get(k).getWeight() + ") ");
			}
			System.out.println();
		}
		System.out.println();	
	}	
	

	public void randomizeWeights() {
		
		/* for debugging only */
	
		/*
		int[] weights = {11,22,33,44,55,
						 66,77,88,99,12,
						 23,34,45,56,67,
						 78,89,13,24,35,
						 46,57,68,79};
		*/
		
		int[][] check = new int[N][N];
		
		for (int i1 = 0; i1 < N; i1++) {       
			for (int i2 = 0; i2 < N; i2++) {
			      check[i1][i2] = 0;    
			}    
		}
		   
		int weight;
		    
		//int count = 0;
		for (int i1 = 0; i1 < N; i1++) {// for each vertex
			Vertex u = this.vertices[i1];
		    List<WeightedEdge> conn = u.getAdjacency();
		    
		    for (int k = 0; k < conn.size(); k++) {
		    	int i2 = conn.get(k).getTo();
		    	//v = this.vertices[i2];
		    	weight = (int)Math.floor(Math.random() * 20) + 2;// range
		    	//weight = weights[count++ % 22];
		    	if (check[i1][i2] == 0) {// directed graph
		    		System.out.println("set weight " + weight + " to edge " 
		    					+ i1 + " " 
		    					+ i2);
		    		conn.get(k).setWeight(weight);
		    		
		    	}// if
		    }// for  
		}// for
				
	}// randomizeWeights
	
	
	
}
