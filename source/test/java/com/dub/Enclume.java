package com.dub;

import java.util.Arrays;
import java.util.Comparator;

public class Enclume {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Integer[] fin = {1,2,3,4,5,6};
		

        Arrays.sort(fin, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 < o2) {
					return 1;
				} else if (o1 > o2) {
					return -1;
				} else {
					return 0;
				}     
			}
        });		
        
        
        for (int i = 0; i < 6; i++) {
        	System.out.println(fin[i]);
        }
        
        	
	}// main

}// class


