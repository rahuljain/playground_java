package interviewQuestions;

/**
 * Calculate the height of a BST
 * @author Rahul
 *
 */
public class TreeHeight {
	
	public static Integer getTreeHeightRecursive(TreeNode<Integer> treeHead)
	{
		if(treeHead == null)
			return 0;
		return Math.max(getTreeHeightRecursive(treeHead.left), getTreeHeightRecursive(treeHead.right)) + 1;
	}
	
	/**
	 * Check if the tree is balanced.
	 * A balanced tree is defined to be a tree such that the height s of the two subtrees 
	 * of any node never differ by more than one.
	 * 
	 * Runtime: O(N^2)
	 * 
	 * @param root Root of the tree.
	 * @return True if tree is balanced. False otherwise.
	 */
	public static Boolean isTreeBalanced1(TreeNode<Integer> root)
	{
		if(root == null)
			return true;
		
		int heightDiff = Math.abs(getTreeHeightRecursive(root.left) - getTreeHeightRecursive(root.right));

		if(heightDiff > 1)
			return false;
		else
			return isTreeBalanced1(root.left) && isTreeBalanced1(root.right);
	}
	
	public static Integer checkHeight(TreeNode<Integer> root)
	{
		if(root == null)
			return 0;
		
		Integer leftHeight = checkHeight(root.left);
		if(leftHeight == -1)
			return -1;	//not balanced
		
		Integer rightHeight = checkHeight(root.right);
		if(rightHeight == -1)
			return -1;	//not balanced
		
		Integer heightDiff = Math.abs(leftHeight - rightHeight);
		if(heightDiff > 1)
			return -1;	//not balanced
		else 
			return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/**
	 * Check if the tree is balanced.
	 * A balanced tree is defined to be a tree such that the height s of the two subtrees 
	 * of any node never differ by more than one.
	 * 
	 * Runtime: O(N). Space: O(log N)
	 * 
	 * @param root Root of the tree.
	 * @return True if tree is balanced. False otherwise.
	 */
	public static Boolean isTreeBalanced2(TreeNode<Integer> root)
	{
		if(checkHeight(root) < 0)
			return false;
		return true;
	}
}
