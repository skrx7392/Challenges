package foobar;

import java.util.*;

public class UncommonElement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] list1 = new int[] {4,5,6,7,8};
		int[] list2 = new int[] {4,5,6,7};
		int[] list3 = new int[] {4,5,6,7,1};
		int i = uncommonID(list1, list2);
		System.out.println(i);
		int j = MultipleOfThree.multipleOfThree(list3);
		System.out.println(j);
		int k = KnightMoves.knightMoves(1,46);
		System.out.println(k);
	}
	
	public static int uncommonID(int[] list1, int[] list2) {
		SortedSet<Integer> myList = new TreeSet<Integer>();
		boolean firstIsGreater = list1.length>list2.length ? true : false;
		if(!firstIsGreater) {
			for(int i : list1) {
				if(!myList.contains(i)) {
					myList.add(i);
				}
			}
			for(int i:list2) {
				if(!myList.contains(i)) {
					return i;
				}
			}
		}
		else {
			for(int i:list2) {
				if(!myList.contains(i)) {
					myList.add(i);
				}
			}
			for(int i:list1) {
				if(!myList.contains(i)) {
					return i;
				}
			}
		}
		return 0;
	}

}
