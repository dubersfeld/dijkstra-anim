package com.dub.site.shortestPathsTree;

import java.util.List;

/** This class represents a strongly connected graph */
public class SPTGraph extends Graph {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int[] keys; 
	private boolean finished;
	
	private MinHeap queue;// minimum priority queue

	public SPTGraph(int N) {
		super(N);
		keys = new int[N];
	}
	

	public void displaySPT() {
		for (Vertex v : this.vertices) {
			System.out.println((SPTVertex)v);
		}
	}


	
	public boolean isFinished() {
		return finished;
	}


	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	public int[] getKeys() {
		return keys;
	}


	public void setKeys(int[] keys) {
		this.keys = keys;
	}


	public MinHeap getQueue() {
		return queue;
	}


	public void setQueue(MinHeap queue) {
		this.queue = queue;
	}	
	
	public void initDijkstra() {
		/** initialize graph for Dijkstra algorithm with source index 0 */
		for (int i1 = 1; i1 < N; i1++) {
			keys[i1] = 1000;
		}
		keys[0] = 0;
	
		for (int i1 = 0; i1 < N; i1++) {
			((SPTVertex)vertices[i1]).setD(keys[i1]);
		}
		
		queue = new MinHeap(keys);
		queue.display();
		finished = false;
	
	}// initDijkstra
	
	public void searchStep() {
		
		// EXTRACT-MIN
		int mIndex = queue.extractMin();
		
		SPTVertex u = (SPTVertex)this.vertices[mIndex];
		List<WeightedEdge> conn = u.getAdjacency();
		
		for (int k = 0; k < conn.size(); k++) {
			int w = conn.get(k).getWeight();// weight
			int iv = conn.get(k).getTo();
			// RELAX(u, v, w)
			if (keys[iv] > keys[mIndex] + w) {
				keys[iv] = keys[mIndex] + w;
				queue.decreaseKey(iv, keys[mIndex] + w);
				((SPTVertex)this.vertices[iv]).setColor(VertexColor.GREEN);
				((SPTVertex)this.vertices[iv]).setPredecessor(mIndex);
			}
		}// for
		
		// mIndex is final
		((SPTVertex)this.vertices[mIndex]).setColor(VertexColor.BLUE);
		
		// update vertices 
		for (int i1 = 0; i1 < N; i1++) {
			((SPTVertex)this.vertices[i1]).setD(queue.getKeys()[i1]);
		}
		
		if (queue.isEmpty()) {
			finished = true;
		}
				
	}// searchStep
	
}
