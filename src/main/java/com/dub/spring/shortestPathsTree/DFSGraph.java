package com.dub.spring.shortestPathsTree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DFSGraph extends Graph implements Serializable {
	
	/**
	 * This subclass implements the Strongly Connected Algorithm.
	 * Once the graph is built the method Search is called a first time with natural indices sequence 
	 * to compute the f time stamp for all vertices.
	 * Then the indices are reordered according to decreasing d and search is called a second time
	 * on the transposed graph to find strongly connected components.  
	 */
	private static final long serialVersionUID = 1L;

	private Stack<Integer> stack;

	
	private Integer index = 0;// main search loop current index
	private int lastFound = 0;
	
	private int time = 0;
	
	private int treeNumber = 0;
	
	Integer[] indices;
	
	
	public DFSGraph(int N) {
		super(N);
		stack = new SimpleStack<>();
	}
	

	// deep copy
	public DFSGraph(DFSGraph source) {
		super(source.N);
		this.stack = new SimpleStack<>();
	 		
		int i1 = 0;
		for (Vertex vertex : source.getVertices()) {
			DFSVertex dfsVertex = (DFSVertex)vertex;
			DFSVertex v = new DFSVertex(dfsVertex);// deep copy
			this.getVertices()[i1++] = v;
		}
	}

	
	
	public Integer[] getIndices() {
		return indices;
	}

	public void setIndices(Integer[] indices) {
		this.indices = indices;
	}

	/** return a new graph transposed from the original */
	public DFSGraph transpose() {
		DFSGraph tGraph = new DFSGraph(this.N);
		
		int i1 = 0;
		for (Vertex vertex : this.getVertices()) {
			DFSVertex dfsVertex = (DFSVertex)vertex;
			DFSVertex v = new DFSVertex(dfsVertex);// deep copy
			v.getAdjacency().clear();// needed
			tGraph.getVertices()[i1++] = v;
		}
		
		for (i1 = 0; i1 < this.N; i1++) {// for each vertex
			Vertex vertex = this.vertices[i1];// original
			List<WeightedEdge> conn = vertex.getAdjacency();
			for (int k = 0; k < conn.size(); k++) {// for each original edge
				WeightedEdge tEdge = new WeightedEdge(i1, conn.get(k).getWeight());
				tGraph.vertices[conn.get(k).getTo()].getAdjacency().add(tEdge);
			}
		}
		
		// reset all vertices
		for (Vertex v : tGraph.vertices) {
			DFSVertex dfsv = (DFSVertex)v;
			dfsv.setColor(VertexColor.BLACK);
			dfsv.setD(0);
			dfsv.setF(0);
			dfsv.setTree(0);
			dfsv.setParent(null);
		}
		
		tGraph.indices = new Integer[this.N];
		for (int i = 0; i < this.N; i++) {
			tGraph.indices[i] = this.indices[i];
		}
	
		return tGraph;
	}
	
	
	public Stack<Integer> getStack() {
		return stack;
	}

	public void setStack(Stack<Integer> stack) {
		this.stack = stack;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
	
	public SPTGraph getLargestSCC() {
	
		/** DFS on this with natural indices order 
		 * then DFS on this.transpose() with indices 
		 * ordered by decreasing f of this obtained by the first DFS
		 * */
		StepResult result1 = this.search(false);// first DFS
			
		Integer[] fin = new Integer[this.N];
		
		for (int i = 0; i < this.N; i++) {
			fin[i] = result1.getVertices()[i].getF();
		}
			
		DFSGraph tGraph = this.transpose();// build transposed graph
		
		// reorder indices of tGraph
		tGraph.reorder(fin);
		
		tGraph.search(true);// second DFS
		
	    // find largest SCC
	    
	    int maxTreeNum = 0; 
		
		for (int i = 0; i < tGraph.getN(); i++) {// for each vertex
	
			DFSVertex dfsv = (DFSVertex)tGraph.getVertices()[i];
			
			if (dfsv.getTree() > maxTreeNum) {
				maxTreeNum = dfsv.getTree();
			}
		}// for
	    
		System.out.println("maxTreeNum " + maxTreeNum);
		
		int[] treeSizes = new int[maxTreeNum + 1];
		
		for (int i = 0; i < maxTreeNum; i++) {
		      treeSizes[i] = 0;
		}
			    
		for (int i = 0; i < N; i++) {
			treeSizes[((DFSVertex)tGraph.getVertices()[i]).getTree()]++;
		}// for
				 	
		// find largest tree index
	    int indMax = 0;
	    for (int i = 0; i < maxTreeNum; i++) {
	    	if (treeSizes[i] > treeSizes[indMax]) {
	    		indMax = i;
	    	}
	    }
	    
	    System.out.println("largest component index: " + indMax);
	    System.out.println("largest component size: " + treeSizes[indMax]);
		int compSize = treeSizes[indMax];
		
		// now build a new strongly connected graph with new numbering
		SPTGraph comp = new SPTGraph(compSize);
		
		// helper arrays
		Integer[] oldToNew = new Integer[N];  
					    
		Integer newInd = 0;
					   
		for (int i = 0; i < N; i++) {
			// select only vertices that belong to the largest component
			DFSVertex dfsv = (DFSVertex)tGraph.getVertices()[i];
			if (dfsv.getTree() == indMax) {// vertex selected
				oldToNew[i] = newInd++;
			} else {
				oldToNew[i] = null;
			}
		}// for
		
		System.out.println("oldToNew");
		for (int i = 0; i < N; i++) {
			System.out.println(i + " " +  oldToNew[i]);
		}
		
		int indComp = 0;
		
		
		for (int i1 = 0; i1 < N; i1++) {// for each original vertex
	          
			// select only vertices that belong to the largest component
			DFSVertex dfsvOld = (DFSVertex)this.getVertices()[i1];
			DFSVertex dfsvTrans = (DFSVertex)tGraph.getVertices()[i1];
			      	 	
			SPTVertex mstv = new SPTVertex(dfsvOld);
			mstv.setAdjacency(new ArrayList<WeightedEdge>());// initialize with empty list
			  
			System.out.println("tree " + dfsvTrans.getTree() + " " + indMax);
			
			
			if (dfsvTrans.getTree() == indMax) {// transposed graph
				comp.getVertices()[indComp++] = mstv;
			            
				for (int k = 0; k < dfsvOld.getAdjacency().size(); k++) {
					if (oldToNew[dfsvOld.getAdjacency().get(k).getTo()] != null) {
						mstv.getAdjacency().add(
			            			new WeightedEdge(oldToNew[dfsvOld.getAdjacency().get(k).getTo()]));
					}
				}
				
			}// if
			 
		}// for
		
		return comp;
		
	}// getLargestSCC
	
		
	/** main search method */
	public StepResult search(boolean second) {
		System.out.println("search begin with index " + index);
				
		index = indices[0];
				
		StepResult result = null;
				
		boolean fin = false;
				
		while (!fin) { 
			result = searchStep(second);
				
			if (result.getStatus().equals(StepResult.Status.FINISHED)) {
				fin = true;
			}
					
		}// while
				
		System.out.println("search completed");
		return result;
	}// search
	

	
	public StepResult searchStep(boolean second) {
		/** one vertex is visited at each step  
		 */
	
		ResponseVertex[] stepVertices = new ResponseVertex[this.N];
		
		StepResult result = new StepResult();// empty container
		result.setStatus(StepResult.Status.STEP);// default
		
		DFSVertex u = (DFSVertex)vertices[index];
		
		// begin with coloring
		if (u.getColor().equals(VertexColor.BLACK)) {// vertex has just been discovered
			u.setColor(VertexColor.GREEN);// visited
			time++;
			u.setD(time);
		}
			
		List<WeightedEdge> conn = u.getAdjacency();// present vertex successors 
		
	    Integer first = null;// first successor index if present
	    boolean finish = false;
	    
	    if (conn.isEmpty() || (first = this.findNotVisitedAndMark(conn, index)) == null) {
	    	finish = true;
	    }
	       
	    if (!finish) {// prepare to descend
	    
	        ((DFSVertex)vertices[first]).setParent(index);// only change here
	             
	        stack.push(index);// push present vertex before descending 	
	        index = first;// save u for the next step
	        
	    } else {// finish present vertex
	    	u.setColor(VertexColor.BLUE);
	    	time++;
	    	u.setF(time);
	    	if (second) {
	    		u.setTree(treeNumber);
	    	}
	    	if (!stack.isEmpty()) {
	    		index = stack.pop(); 
	    	} else {
	    		index = this.findNotVisited();// can be null
	    		if (index == null) {
	    			result.setStatus(StepResult.Status.FINISHED);
	    		} else if (second) {
	    			// start a new tree
	    			treeNumber++;
	    		}	
	    	}
	    }
		
	    // prepare Ajax response 
	    for (int i = 0; i < this.N;i++) {
	    	stepVertices[i] = new ResponseVertex((DFSVertex)vertices[i]);
	    }
		    
	    result.setVertices(stepVertices);
		
		return result;
	        
	}// searchStep
	
	
	// helper methods
		
	public Integer findNotVisited() {
	
		int nind = 0;
		DFSVertex v = null;
		for (nind = this.lastFound + 1; nind < N; nind++) {
			v = (DFSVertex)vertices[this.indices[nind]];// only this changed
			if (v.getColor().equals(VertexColor.BLACK)) {
				break;
			}
		}
	
		if (nind < N) {
			this.lastFound = nind;// save as initial value for next lookup 
			return indices[nind];// next index
		} else {
			return null;
		}
				
	}// findNotVisited
	
		
	public Integer findNotVisitedAndMark(List<WeightedEdge> list, int from) {

		// successor look up		
		int nind = 0;
		DFSVertex v = null;
		
		for (nind = 0; nind < list.size(); nind++) {
			int to = list.get(nind).getTo();
			v = (DFSVertex)vertices[to];
				
			if (v.getColor().equals(VertexColor.BLACK)) {
				break;
			}
		}
		if (nind < list.size()) {
			return list.get(nind).getTo();
		} else {
			return null;
		}
		
	}// findNotVisited
	
	private void reorder(Integer[] fin) {
		
		/** reordering indices */
		Arrays.sort(this.indices, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (fin[o1] < fin[o2]) {
					return 1;
				} else if (fin[o1] > fin[o2]) {
					return -1;
				} else {
					return 0;
				}     
			}
		});
	}// reorder
	
	
	// used for debugging only
	public void displayIndices() {
		String display = "";
		
		for (int i = 0; i < this.N; i++) {
			display += indices[i] + " ";
		}
		System.out.println(display);
	}
	
	public void displayDFS() {
		for (Vertex v : this.vertices) {
			System.out.println((DFSVertex)v);
		}
	}	
	
	public void displaySPT() {
		for (Vertex v : this.vertices) {
			System.out.println((SPTVertex)v);
		}
	}	
}
