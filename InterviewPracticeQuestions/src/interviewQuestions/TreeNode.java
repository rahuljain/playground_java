package interviewQuestions;

public class TreeNode<T> {
	
	public T key;
	public TreeNode<T> left;
	public TreeNode<T> right;
	
	public TreeNode()
	{
		key = null;
		left = null;
		right = null;
	}
	
	public TreeNode(T k)
	{
		key = k;
		left = null;
		right = null;
	}
	
	public TreeNode(T k, TreeNode<T> leftNode, TreeNode<T> rightNode)
	{
		key = k;
		left = leftNode;
		right = rightNode;
	}

}
