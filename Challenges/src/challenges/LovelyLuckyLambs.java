package challenges;

import java.util.*;

public class LovelyLuckyLambs {
	
	public static int lovelyLuckyLambs(int lambs) {
		int sum = 0;
		int iterMin = 0;
		while(sum<=lambs) {
			sum += (int)Math.pow(2, iterMin);
			iterMin++;
		}
		sum = 0;
		int iterMax = 0;
		ArrayList<Integer> maxList = new ArrayList<Integer>();
		while(sum<=lambs) {
			if(iterMax==0 || iterMax==1) {
				maxList.add(1);
				iterMax++;
			}
			else{
				maxList.add(maxList.get(iterMax-1) + maxList.get(iterMax-2));
				iterMax++;
			}
			sum+=maxList.get(iterMax-1);
		}
		return iterMax-iterMin;
	}

}
