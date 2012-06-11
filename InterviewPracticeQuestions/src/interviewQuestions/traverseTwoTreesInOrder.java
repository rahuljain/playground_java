package interviewQuestions;

public class traverseTwoTreesInOrder {

	void traverse(node *t1, node *t2) {
	    //
	    if(t2 == 0) {
	        if(t1 == 0)
	            return;
	        traverse(t1->left, 0);
	        printf("%d ", t1->val);
	        traverse(t1->right, 0);
	        return;
	    }
	    //
	    if(t1 == 0) {
	        traverse(0, t2->left);
	        printf("%d ", t2->val);
	        traverse(0, t2->right);
	        return;
	    }
	    //
	    traverse(t1->left, t2->left);
	    if(t1->val >= t2->val) {
	        printf("%d ", t2->val);
	        node *s = t1->left;
	        t1->left = 0;
	        traverse(t1, t2->right);
	        t1->left = s;
	    } else {
	        printf("%d ", t1->val);
	        node *s = t2->left;
	        t2->left = 0;
	        traverse(t1->right, t2);
	        t2->left = s;
	    }
	}



	void destroy_tree(node *root) {
	    //
	    if(root == 0)
	        return;
	    destroy_tree(root->left);
	    destroy_tree(root->right);
	    delete root;
	}


}
