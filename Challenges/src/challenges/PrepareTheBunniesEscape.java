package challenges;

import java.util.*;

class EscapeNode {
	int x, y;
	int pathCount;
	boolean wallRemoved;
	public EscapeNode(int x, int y, int pathCount, boolean wallRemoved) {
		this.x = x;
		this.y = y;
		this.pathCount = pathCount;
		this.wallRemoved = wallRemoved;
	}
}

class NodeDetails {
	int pathCount;
	boolean wallRemoved;
	public NodeDetails(int pathCount, boolean wallRemoved) {
		this.pathCount = pathCount;
		this.wallRemoved = wallRemoved;
	}
}

public class PrepareTheBunniesEscape {
	public int prepareTheBunniesEscape(int[][] matrix) {
		Queue<EscapeNode> queue = new LinkedList<EscapeNode>();
		EscapeNode root = new EscapeNode(0,0,1,false);
		queue.add(root);
		int[] vals = new int[] {1, -1};
		HashMap<Integer, NodeDetails[]> hm = new HashMap<Integer, NodeDetails[]>();
		hm.put(0, new NodeDetails[]{new NodeDetails(0, false), new NodeDetails(Integer.MAX_VALUE, true)});
		while(!queue.isEmpty()) {
			EscapeNode node = queue.remove();
			for(int i:vals) {
				if(node.x+i==matrix.length-1) {
					if(node.y==matrix[0].length-1) {
						return node.pathCount+1;
					}
				}
				if(node.y+i==matrix[0].length-1) {
					if(node.x==matrix.length-1) {
						return node.pathCount+1;
					}
				}
				try {
					if(!(node.x+i<0 || node.x+i>=matrix.length)) {
						if(matrix[node.x+i][node.y]==0) {
							if(!hm.containsKey((node.x+i)*matrix[0].length+node.y)){
								EscapeNode nextNode = new EscapeNode(node.x+i, node.y, node.pathCount+1, node.wallRemoved);
								NodeDetails[] detailsArr = new NodeDetails[2];
								if(nextNode.wallRemoved) {
									detailsArr[0] = new NodeDetails(Integer.MAX_VALUE, false);
									detailsArr[1] = new NodeDetails(nextNode.pathCount, nextNode.wallRemoved);
								}
								else {
									detailsArr[0] = new NodeDetails(nextNode.pathCount, nextNode.wallRemoved);
									detailsArr[1] = new NodeDetails(Integer.MAX_VALUE, true);
								}
								hm.put((node.x+i)*matrix[0].length+node.y, detailsArr);
								queue.add(nextNode);
							}
							else {
								EscapeNode nextNode = new EscapeNode(node.x+i, node.y, node.pathCount+1, node.wallRemoved);
								NodeDetails[] detailsArr = hm.get((node.x+i)*matrix[0].length+node.y);
								if(nextNode.wallRemoved) {
									if(detailsArr[1].pathCount>nextNode.pathCount) {
										detailsArr[1].pathCount=nextNode.pathCount;
										queue.add(nextNode);
										hm.remove((node.x+i)*matrix[0].length+node.y);
										hm.put((node.x+i)*matrix[0].length+node.y, detailsArr);
									}
								}
								else {
									if(detailsArr[0].pathCount>nextNode.pathCount) {
										detailsArr[0].pathCount=nextNode.pathCount;
										queue.add(nextNode);
										hm.remove((node.x+i)*matrix[0].length+node.y);
										hm.put((node.x+i)*matrix[0].length+node.y, detailsArr);
									}
								}
							}
						}
						else if(matrix[node.x+i][node.y]==1 && node.wallRemoved==false) {
							if(!hm.containsKey((node.x+i)*matrix[0].length+node.y)) {
								EscapeNode nextNode = new EscapeNode(node.x+i, node.y, node.pathCount+1, true);
								NodeDetails[] detailsArr = new NodeDetails[] {null, new NodeDetails(nextNode.pathCount, nextNode.wallRemoved)};
								queue.add(nextNode);
								hm.put((node.x+i)*matrix[0].length+node.y, detailsArr);
							}
							else {
								EscapeNode nextNode = new EscapeNode(node.x+i, node.y, node.pathCount+1, true);
								NodeDetails[] detailsArr = hm.get((node.x+i)*matrix[0].length+node.y);
								if(detailsArr[1].pathCount>nextNode.pathCount) {
									detailsArr[1].pathCount=nextNode.pathCount;
									hm.remove((node.x+i)*matrix[0].length+node.y);
									hm.put((node.x+i)*matrix[0].length+node.y, detailsArr);
									queue.add(nextNode);
								}
							}
						}
					}
					if(!(node.y+i<0 || node.y+i>=matrix[0].length)) {
						if(matrix[node.x][node.y+i]==0) {
							if(!hm.containsKey((node.x)*matrix[0].length+node.y+i)){
								EscapeNode nextNode = new EscapeNode(node.x, node.y+i, node.pathCount+1, node.wallRemoved);
								NodeDetails[] detailsArr = new NodeDetails[2];
								if(nextNode.wallRemoved) {
									detailsArr[0] = new NodeDetails(Integer.MAX_VALUE, false);
									detailsArr[1] = new NodeDetails(nextNode.pathCount, nextNode.wallRemoved);
								}
								else {
									detailsArr[0] = new NodeDetails(nextNode.pathCount, nextNode.wallRemoved);
									detailsArr[1] = new NodeDetails(Integer.MAX_VALUE, true);
								}
								hm.put((node.x)*matrix[0].length+node.y+i, detailsArr);
								queue.add(nextNode);
							}
							else {
								EscapeNode nextNode = new EscapeNode(node.x, node.y+i, node.pathCount+1, node.wallRemoved);
								NodeDetails[] detailsArr = hm.get((node.x)*matrix[0].length+node.y+i);
								if(nextNode.wallRemoved) {
									if(detailsArr[1].pathCount>nextNode.pathCount) {
										detailsArr[1].pathCount=nextNode.pathCount;
										queue.add(nextNode);
										hm.remove((node.x)*matrix[0].length+node.y+i);
										hm.put((node.x)*matrix[0].length+node.y+i, detailsArr);
									}
								}
								else {
									if(detailsArr[0].pathCount>nextNode.pathCount) {
										detailsArr[0].pathCount=nextNode.pathCount;
										queue.add(nextNode);
										hm.remove((node.x)*matrix[0].length+node.y+i);
										hm.put((node.x)*matrix[0].length+node.y+i, detailsArr);
									}
								}
							}
						}
						else if(matrix[node.x][node.y+i]==1 && node.wallRemoved==false) {
							if(!hm.containsKey((node.x)*matrix[0].length+node.y+i)) {
								EscapeNode nextNode = new EscapeNode(node.x, node.y+i, node.pathCount+1, true);
								NodeDetails[] detailsArr = new NodeDetails[] {null, new NodeDetails(nextNode.pathCount, nextNode.wallRemoved)};
								queue.add(nextNode);
								hm.put((node.x)*matrix[0].length+node.y+i, detailsArr);
							}
							else {
								EscapeNode nextNode = new EscapeNode(node.x+i, node.y, node.pathCount+1, true);
								NodeDetails[] detailsArr = hm.get((node.x+i)*matrix[0].length+node.y);
								if(detailsArr[1].pathCount>nextNode.pathCount) {
									detailsArr[1].pathCount=nextNode.pathCount;
									queue.add(nextNode);
									hm.remove((node.x)*matrix[0].length+node.y+i);
									hm.put((node.x)*matrix[0].length+node.y+i, detailsArr);
								}
							}
						}
					}
				}
				catch(Exception ex) {
					
				}
			}	
		}
		return 0;
	}
}
