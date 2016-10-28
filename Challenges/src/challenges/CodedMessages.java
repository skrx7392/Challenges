package challenges;

import java.util.*;

public class CodedMessages {
	
	public static int[] codedMessages(int[] list, int total) {
		int startIndex = 0;
		int sum=0;
		int iter = 0;
		while(sum<total) {
			if(startIndex+iter>=list.length) {
				return new int[] {-1, -1};
			}
			sum += list[startIndex+iter];
			if(sum==total) {
				return new int[] {startIndex, startIndex + iter};
			}
			else if(sum>total) {
				iter = 0;
				startIndex++;
				sum = 0;
			}
			else {
				iter++;
			}
		}
		
		return new int[] {-1, -1};
	}

}
