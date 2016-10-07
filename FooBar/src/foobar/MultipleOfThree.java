package foobar;

import java.util.*;

public class MultipleOfThree {
	
	public static int multipleOfThree(int[] input) {
		Arrays.sort(input);
		Queue<Integer> list1 = new PriorityQueue<Integer>();
		Queue<Integer> list2 = new PriorityQueue<Integer>();
		List<Integer> finalList = new ArrayList<Integer>();
		int ans = 0;
		int sumOfDigits = 0;
		for(int i=0; i<input.length; i++) {
			if(input[i]%3==0)
				finalList.add(input[i]);
			else if(input[i]%3==1)
				list1.add(input[i]);
			else
				list2.add(input[i]);
			sumOfDigits+=input[i];
		}
		if(sumOfDigits%3==1) {
			if(!list1.isEmpty())
				list1.remove();
			else {
				if(!list2.isEmpty())
					list2.remove();
				else
					return 0;
				if(!list2.isEmpty())
					list2.remove();
				else
					return 0;
			}
		}
		else if(sumOfDigits%3==2) {
			if(!list2.isEmpty())
				list2.remove();
			else {
				if(!list1.isEmpty())
					list1.remove();
				else
					return 0;
				if(!list1.isEmpty())
					list1.remove();
				else
					return 0;
			}
		}
		finalList.addAll(list1);
		finalList.addAll(list2);
		Collections.sort(finalList);
		for(int i=0; i<finalList.size(); i++) {
			ans += finalList.get(i)*Math.pow(10, i);
		}
		return ans;
	}

}
