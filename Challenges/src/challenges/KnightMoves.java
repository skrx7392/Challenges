package challenges;

import java.util.*;

class Node {
	int location;
	int distance;
	public Node(int location) {
		this.location = location;
		this.distance = 0;
	}
	
	public Node(int location, int distance) {
		this.location = location;
		this.distance = distance;
	}
}

public class KnightMoves {
	
	public static int knightMoves(int src, int des) {
		List<Node> myQueue = new ArrayList<Node>();
		int[] distances = new int[64];
		myQueue.add(new Node(src));
		distances[src] = 0;
		while(!myQueue.isEmpty()) {
			Node escapeNode = myQueue.remove(0);
			int loc = escapeNode.location;
			List<Integer> nextSteps = populateNextSteps(loc);
			if(nextSteps.contains(des)) {
				distances[des] = escapeNode.distance+1;
				return escapeNode.distance+1;
			}
			for(int i:nextSteps) {
				if(distances[i]>0 && distances[i]<escapeNode.distance+1) {
					myQueue.add(new Node(i, distances[i]));
				}
				else {
					distances[i] = escapeNode.distance+1;
					myQueue.add(new Node(i, escapeNode.distance+1));
				}
			}
		}
		return 0;
	}
	public static List<Integer> populateNextSteps(int loc) {
		List<Integer> nextSteps = new ArrayList<Integer>();
		int[] ones = new int[] {1,-1};
		int[] twos = new int[] {2,-2};
		for(int i=0; i<ones.length; i++) {
			int temp1 = loc/8+ones[i];
			if(temp1<=7 && temp1>=0) {
				for(int j=0; j<twos.length; j++) {
					int temp2 = loc%8+twos[j];
					if(temp2<=7 && temp2>=0) {
						nextSteps.add(temp1*8 + temp2);
					}
				}
			}
		}
		for(int i=0; i<twos.length; i++) {
			int temp1 = loc/8+twos[i];
			if(temp1<=7 && temp1>=0) {
				for(int j=0; j<ones.length; j++) {
					int temp2 = loc%8+ones[j];
					if(temp2<=7 && temp2>=0) {
						nextSteps.add(temp1*8 + temp2);
					}
				}
			}
		}
		return nextSteps;
	}
}


