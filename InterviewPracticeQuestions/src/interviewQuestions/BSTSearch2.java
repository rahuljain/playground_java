package interviewQuestions;

/**
 * Given a BST T and a key K, write a method that searches for the first entry larger than K.
 * @author Rahul
 *
 */
public class BSTSearch2 
{
	/**
	 * 
	 * @param T Head of the BST
	 * @param K Find first entry that has key larger than K.
	 * @return Node that has the first entry larger than K or null of no such node exists.
	 */
	public TreeNode<Integer> IterativeSearchBST(TreeNode<Integer> T, Integer K)
	{
		TreeNode<Integer> currentNode = T;
		TreeNode<Integer> result = null;
		while(currentNode != null)
		{
			if(K < currentNode.key)
			{
				result = currentNode;
				currentNode = currentNode.left;
			}
			else
				currentNode = currentNode.right;
		}
		return result;
	}
}
