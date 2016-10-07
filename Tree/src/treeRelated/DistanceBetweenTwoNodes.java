package treeRelated;

public class DistanceBetweenTwoNodes {
	
	public int findDistance(TreeNode node, TreeNode n1, TreeNode n2) {
		int x=PathLength(node, n1) - 1;
		int y=PathLength(node, n2) - 1;
		LCA lca = new LCA();
		TreeNode lcaData = lca.getLCA(node, n1, n2);
		int lcaDistance = PathLength(node, lcaData) - 1;
		return (x+y)-2*lcaDistance;
	}
	
	public int PathLength(TreeNode node, TreeNode n1) {
		if(node!=null) {
			int x=0; 
			if((node.data==n1.data) || (x=PathLength(node.left, n1)) > 0 || (x=PathLength(node.right, n1)) >0) {
				return x+1;
			}
		}
		return 0;
	}
}
