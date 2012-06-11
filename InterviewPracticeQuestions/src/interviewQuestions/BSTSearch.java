package interviewQuestions;
/**
 * Given a BST, write recursive and iterative functions to search for a Key.
 * @author Rahul
 *
 */
public class BSTSearch
{
	
	public TreeNode<Integer> RecursiveSearchBST(TreeNode<Integer> BST_headNode, Integer searchVal)
	{
		if(BST_headNode.key.equals(searchVal) || BST_headNode == null)
		{
			return BST_headNode;
		}
		if(searchVal < BST_headNode.key)
		{
			return RecursiveSearchBST(BST_headNode.left, searchVal);
		}
		else
		{
			return RecursiveSearchBST(BST_headNode.right, searchVal);
		}
	}
	
	public TreeNode<Integer> IterativeSearchBST(TreeNode<Integer> BST_headNode, Integer searchVal)
	{
		TreeNode<Integer> currentNode = BST_headNode;
		while(currentNode != null && currentNode.key != searchVal)
		{
			if(searchVal < currentNode.key)
				currentNode = currentNode.left;
			else 
				currentNode = currentNode.right;
		}
		return currentNode;
	}
}
