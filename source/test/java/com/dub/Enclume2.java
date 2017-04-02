package com.dub;

import java.util.Arrays;
import java.util.Comparator;

public class Enclume2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Integer[] fin = {31,38,34,33,55,36};
		
		Integer[] indices = {0,1,2,3,4,5};
				

        Arrays.sort(indices, new Comparator<Integer>() {

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
        
        
        for (int i = 0; i < 6; i++) {
        	System.out.println(indices[i]);
        }
        
        	
	}// main

}// class


