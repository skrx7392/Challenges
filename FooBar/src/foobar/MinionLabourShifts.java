package foobar;

import java.util.*;

public class MinionLabourShifts {
	
	public static int[] minionLabourShifts (int[] data, int n) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for(int i:data) {
			if(!hm.containsKey(i)) {
				hm.put(i, 1);
			}
			else {
				int count = hm.get(i);
				count++;
				hm.remove(i);
				hm.put(i, count);
			}
		}
		List<Integer> returnList = new ArrayList<Integer>();
		for(int i:data) {
			if(hm.get(i)<=n) {
				returnList.add(i);
			}
		}
		int[] retData = new int[returnList.size()];
		for(int i=0; i<returnList.size(); i++) {
			retData[i] = returnList.get(i);
		}
		return retData;
	}

}
