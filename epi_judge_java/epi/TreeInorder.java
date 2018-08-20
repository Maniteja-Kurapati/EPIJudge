package epi;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class TreeInorder {
    @EpiTest(testDataFile = "tree_inorder.tsv")

    public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {

	Deque<BinaryTreeNode<Integer>> stack = new ArrayDeque<>();

	List<Integer> traversal = new ArrayList<>();

	BinaryTreeNode<Integer> current = tree;

	while (current != null || !stack.isEmpty()) {
	    if (current != null) {
		stack.addFirst(current);

		current = current.left;
	    } else {
		current = stack.removeFirst();

		traversal.add(current.data);

		current = current.right;
	    }

	}

	return traversal;
    }

    public static void main(String[] args) {
	System.exit(GenericTest.runFromAnnotations(args, "TreeInorder.java", new Object() {
	}.getClass().getEnclosingClass()).ordinal());
    }
}
