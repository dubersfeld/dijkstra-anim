package com.dub.site.shortestPathsTree;


/** This class implements a minimum priority queue */

public class MinHeap {

	int[] keys;
	int[] indices;
	int length;
	int heapSize;
	
	public MinHeap(int[] keys) {
		this.keys = new int[keys.length];
		this.indices = new int[keys.length];
		this.length = keys.length;
		this.heapSize = keys.length; 
		for (int i = 0; i < keys.length; i++) {    
			indices[i] = i;
		}
		for (int i = 0; i < keys.length; i++) {
	        this.keys[i] = keys[i];
		}
	}

	public int[] getKeys() {
		return keys;
	}

	public void setKeys(int[] keys) {
		this.keys = keys;
	}

	public int[] getIndices() {
		return indices;
	}

	public void setIndices(int[] indices) {
		this.indices = indices;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getHeapSize() {
		return heapSize;
	}

	public void setHeapSize(int heapSize) {
		this.heapSize = heapSize;
	}
	
	// helper methods
	public int left(int i) {
		return 2 * i + 1;
	}
	
	public int right(int i) {
		return 2 * i + 2;
	}
	
	public int parent(int i) {
		return Math.floorDiv(i-1, 2);
	}
	
	public void swap(int i, int j) {
		int temp = indices[i];
		indices[i] = indices[j];
		indices[j] = temp;
	}
	 
	public void minHeapify(int i) {// non recursive  
		int j = i;
	    int smallest = i;

	    while (true) {
	    	int l = this.left(j);
	        int r = this.right(j);     

	        if ( (l < heapSize) && (keys[indices[l]] < keys[indices[j]]) ) {
	        	smallest = l;
	        } else {
	        	smallest = j;
	        }

	        if ( (r < heapSize) && ( keys[indices[r]] < keys[indices[smallest]]) ) {
	        	smallest = r;
	        }
	    
	        if (smallest == j) {
	        	break; 
	        } else {// one more iteration
	        	swap(j, smallest);
	        	j = smallest;
	        }  
	    }// while    
	}// minHeapify
	
	public void build() {      
		for (int i = Math.floorDiv(length, 2) - 1; i >= 0; i--) {
			minHeapify(i);
		}// for
	}// build
	
	public int extractMin() {  
		int hmin = indices[0];  
		indices[0] = indices[heapSize-1];
		heapSize--;	      
		build();          
		return hmin;      
	} 
	
	public void decreaseKey(int ind, int newkey) {     
		if (this.keys[ind] < newkey) {
			return;
		}
		keys[ind] = newkey;// change happens in original array
		int i = indexOf(indices, ind);
		while (i > 0 && this.keys[this.indices[this.parent(i)]] > this.keys[this.indices[i]]) {
	        this.swap(i, this.parent(i));
	        i = this.parent(i);
		}// while    
	}

	public void display() {// used for Debugging only     
	      String msg = "keys: ";
	      for (int i = 0; i < heapSize; i++) {
	        msg += (this.keys[this.indices[i]] + " ");
	      }
	      System.out.println(msg);    
	}// display
	
	public void displayAll() {// used for Debugging only     
	      String msg = "keys: ";
	      for (int i = 0; i < length; i++) {
	        msg += (this.keys[i] + " ");
	      }
	      System.out.println(msg);    
	}// display
	
	// helper method
	private int indexOf(int[] ar, int ind) {
		int k = 0;
		for (k = 0; k < ar.length; k++) {
			if (ar[k] == ind) {
				break;
			}
		}// for
		if (k < ar.length) {
			return k;
		} else {
			return -1;
		}
	}// indexOf
	
	public boolean isInQueue(int index) {
		int k = 0;
		for (k = 0; k < heapSize; k++) {
			if (indices[k] == index) {
				break;
			}
		}// for
		if (k < heapSize) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean isEmpty() {
		return (heapSize == 0);
	}
	
	
	
}
